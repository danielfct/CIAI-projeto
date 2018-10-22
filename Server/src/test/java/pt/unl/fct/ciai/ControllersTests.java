package pt.unl.fct.ciai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pt.unl.fct.ciai.company.Company;
import pt.unl.fct.ciai.company.CompaniesRepository;
import pt.unl.fct.ciai.contact.Contact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllersTests {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private CompaniesRepository companies;
	private MockMvc mockMvc;
	private ObjectMapper mapper;

	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.mapper = new ObjectMapper();

		// Data Fixture
		companies.deleteAll();
		Company ecma = new Company().name("ecma").email("ecma@ecma.pt").address("lisboa");
		companies.save(ecma);
		Company fct = new Company().name("fct").email("fct@fct.unl.pt").address("almada");
		companies.save(fct);
	}

	private List<Company> getCompanies() throws Exception {
		final MvcResult result = this.mockMvc.perform(get("/companies"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andReturn();
		String listAsString = result.getResponse().getContentAsString();
		return new ObjectMapper().readValue(listAsString, new TypeReference<List<Company>>(){});
	}

	@Test
	public void testGetCompanies() throws Exception {
		List<Company> companies = this.getCompanies();
		List<String> companyNames = companies.stream().map(Company::getName).collect(Collectors.toList());
		Assert.assertTrue(companyNames.contains("ecma"));
		Assert.assertTrue(companyNames.contains("fct"));
	}

	@Test
	public void testAddContact() throws Exception {
		List<Company> companies = this.getCompanies();
		long firstId = companies.get(0).getId();
		// Add contact
		Contact contact = new Contact().name("NewContact");
		String json = mapper.writeValueAsString(contact);
		this.mockMvc.perform(post("/companies/" + firstId + "/contacts")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json))
		.andExpect(status().isOk());
		// Get contacts
		this.mockMvc.perform(get("/companies/" + firstId + "/contacts"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].name", is("NewContact")));
	}

	@Test
	public void testAddRemoveContacts() throws Exception {
		List<Company> companies = getCompanies();
		long firstCompanyId = companies.get(0).getId();
		// Add contact
		Contact contact = new Contact().name("NewContact");
		String json = mapper.writeValueAsString(contact);
		this.mockMvc.perform(post("/companies/" + firstCompanyId + "/contacts")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json))
		.andExpect(status().isOk())
		.andReturn();
		// Get contacts and expect not empty list
		final MvcResult result = this.mockMvc.perform(get("/companies/" + firstCompanyId + "/contacts"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(greaterThan(0))))
				.andReturn();
		String list = result.getResponse().getContentAsString();
		List<Contact> contacts = mapper.readValue(list,new TypeReference<List<Contact>>(){});
		// Delete contact
		long firstContactId = contacts.get(0).getId();
		this.mockMvc.perform(delete("/contacts/" + firstContactId))
		.andExpect(status().isOk());
		// Get contacts and expect empty list
		this.mockMvc.perform(get("/companies/" + firstCompanyId + "/contacts/"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", hasSize(0)));
	}
}
