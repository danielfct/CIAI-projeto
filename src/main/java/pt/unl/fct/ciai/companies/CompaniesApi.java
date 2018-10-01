/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package pt.unl.fct.ciai.companies;

import pt.unl.fct.ciai.companies.Company;
import pt.unl.fct.ciai.contacts.Contact;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-27T18:31:48.842Z")

@Api(value = "companies", description = "the companies API")
public interface CompaniesApi {

	@ApiOperation(value = "Add a new partner company to the collection", nickname = "addCompany", notes = "", tags={ "Companies", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Company added"),
			@ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/companies",
	consumes = { "application/json" },
	method = RequestMethod.POST)
	void addCompany(@ApiParam(value = "Company object that needs to be added to the collection" ,required=true )  @Valid @RequestBody Company company);


	@ApiOperation(value = "Create a contact of company {id} described in the payload", nickname = "addCompanyContact", notes = "", tags={ "Companies", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Company's contact added"),
			@ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/companies/{id}/contacts",
	consumes = { "application/json" },
	method = RequestMethod.POST)
	void addCompanyContact(@ApiParam(value = "Identifier of the company to create a new contact",required=true) @PathVariable("id") Long id,@Valid @RequestBody Contact contact);


	@ApiOperation(value = "Removes the company with {id}", nickname = "deleteCompany", notes = "", tags={ "Companies", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Removed company"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Company not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/companies/{id}",
	method = RequestMethod.DELETE)
	void deleteCompany(@ApiParam(value = "Identifier of the company to be deleted",required=true) @PathVariable("id") Long id);


	@ApiOperation(value = "List all the companies", nickname = "getCompanies", notes = "", response = Company.class, responseContainer = "List", tags={ "Companies", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "successful operation", response = Company.class, responseContainer = "List") })
	@RequestMapping(value = "/companies",
	produces = { "application/json" }, 
	method = RequestMethod.GET)
	Iterable<Company> getCompanies(@ApiParam(value = "Filter companies by name, description, or address") @Valid @RequestParam(value = "search", required = false) String search);


	@ApiOperation(value = "Get a company identified by {id}", nickname = "getCompany", notes = "", response = Object.class, tags={ "Companies", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "successful operation", response = Object.class) })
	@RequestMapping(value = "/companies/{id}",
	produces = { "application/json" }, 
	method = RequestMethod.GET)
	Company getCompany(@ApiParam(value = "Identifier of the company to be shown",required=true) @PathVariable("id") Long id,@ApiParam(value = "Filter companies by name, description, or address") @Valid @RequestParam(value = "search", required = false) String search);


	@ApiOperation(value = "List all the contacts of a company", nickname = "getCompanyContacts", notes = "", response = Object.class, tags={ "Companies", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successful operation", response = Object.class) })
	@RequestMapping(value = "/companies/{id}/contacts",
	produces = { "application/json" }, 
	method = RequestMethod.GET)
	Iterable<Contact> getCompanyContacts(@ApiParam(value = "Identifier of the company to show all contacts",required=true) @PathVariable("id") Long id,@ApiParam(value = "Filter company's contacts") @Valid @RequestParam(value = "search", required = false) String search);


	@ApiOperation(value = "Update an existing company with {id} as identifier", nickname = "updateCompany", notes = "", tags={ "Companies", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Updated company"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Company not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/companies/{id}",
	consumes = { "application/json" },
	method = RequestMethod.PUT)
	void updateCompany(@ApiParam(value = "Identifier of the company to be updated",required=true) @PathVariable("id") Long id,@ApiParam(value = "Company object that needs to be updated in the collection" ,required=true )  @Valid @RequestBody Company company);

}
