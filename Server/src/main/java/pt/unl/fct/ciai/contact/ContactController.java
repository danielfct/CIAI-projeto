package pt.unl.fct.ciai.contact;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.unl.fct.ciai.exceptions.BadRequestException;
import pt.unl.fct.ciai.exceptions.NotFoundException;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController { //implements ContactsApi {

	private final ContactRepository contacts;

	public ContactController(ContactRepository contacts) {
		this.contacts = contacts;
	}

	@GetMapping
	public Iterable<Contact> getContacts(@RequestParam(value = "search", required = false) String search) {
		return search == null ? contacts.findAll() : contacts.search(search);
	}

	@GetMapping(value = "/{id}")
	public Contact getContact(@PathVariable("id") Long id) {
		return findContactOrThrowException(id);
	}

	@PutMapping(value = "/{id}")
	public void updateContact(@PathVariable("id") Long id, @RequestBody Contact contact) {
		if (id.equals(contact.getId())) {
			findContactOrThrowException(id);
			contacts.save(contact);
		} else {
			throw new BadRequestException(String.format("Path id %d does not match contact id %d", id, contact.getId()));
		}
	}

	@DeleteMapping(value = "/{id}")
	public void deleteContact(@PathVariable("id") Long id) {
		findContactOrThrowException(id);
		contacts.deleteById(id);
	}
	
	private Contact findContactOrThrowException(Long id) {
		return contacts.findById(id).orElseThrow(() -> new NotFoundException(String.format("Contact with id %d does not exist", id)));
	}

}