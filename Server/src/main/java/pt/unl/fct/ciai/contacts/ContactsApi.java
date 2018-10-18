///**
// * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
// * https://github.com/swagger-api/swagger-codegen
// * Do not edit the class manually.
// */
//package pt.unl.fct.ciai.contacts;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.Valid;
//import javax.validation.constraints.*;
//import java.util.List;
//@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-27T18:31:48.842Z")
//
//@Api(value = "contacts", description = "the contacts API")
//public interface ContactsApi {
//
//    @ApiOperation(value = "Delete an existing contact with {id} as identifier", nickname = "deleteContact", notes = "Delete an existing contact with {id} as identifier", tags={ "Contacts", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "Removed contact"),
//        @ApiResponse(code = 400, message = "Invalid ID supplied"),
//        @ApiResponse(code = 404, message = "Contact not found"),
//        @ApiResponse(code = 405, message = "Validation exception") })
//    @RequestMapping(value = "/contacts/{id}",
//        method = RequestMethod.DELETE)
//    ResponseEntity<Void> deleteContact(@ApiParam(value = "Identifier of the contact to be deleted",required=true) @PathVariable("id") Long id);
//
//
//    @ApiOperation(value = "Shows the contact with identifier {id}", nickname = "getContact", notes = "", response = Object.class, tags={ "Contacts", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "Successful operation", response = Object.class),
//        @ApiResponse(code = 404, message = "Contact not found") })
//    @RequestMapping(value = "/contacts/{id}",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<Object> getContact(@ApiParam(value = "Identifier of the contact to be shown",required=true) @PathVariable("id") Long id);
//
//
//    @ApiOperation(value = "Get the list of all contacts", nickname = "getContacts", notes = "", response = Contact.class, responseContainer = "List", tags={ "Contacts", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "successful operation", response = Contact.class, responseContainer = "List") })
//    @RequestMapping(value = "/contacts",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<List<Contact>> getContacts(@ApiParam(value = "List all the contacts that contain the criteria") @Valid @RequestParam(value = "search", required = false) String search);
//
//
//    @ApiOperation(value = "Update an existing contact with {id} as identifier", nickname = "updateContact", notes = "Update an existing contact with {id} as identifier", tags={ "Contacts", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "Updated contact"),
//        @ApiResponse(code = 400, message = "Invalid ID supplied"),
//        @ApiResponse(code = 404, message = "Contact not found"),
//        @ApiResponse(code = 405, message = "Validation exception") })
//    @RequestMapping(value = "/contacts/{id}",
//        consumes = { "application/json" },
//        method = RequestMethod.PUT)
//    ResponseEntity<Void> updateContact(@ApiParam(value = "Identifier of the contact to be updated",required=true) @PathVariable("id") Long id,@ApiParam(value = "Contact object that needs to be updated in the collection" ,required=true )  @Valid @RequestBody Contact contact);
//
//}