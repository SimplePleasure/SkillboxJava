package main;

import main.model.Contact;
import main.model.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ContactController {

    @Autowired
    ContactRepository contactRepository;


    @RequestMapping(value = "/contact/", method = RequestMethod.GET)
    public List<Contact> list() {

        Iterable<Contact> iterable = contactRepository.findAll();
        ArrayList<Contact> contacts = new ArrayList<>();
        for(Contact contact : iterable) {
            contacts.add(contact);
        }
        return contacts;
    }


    @RequestMapping(value = "/contact/", method = RequestMethod.POST)
    public ResponseEntity<Integer> add(Contact contact) {

        if (contact.getName().length() == 0 || contact.getPhone().length() == 0 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Contact entry= contactRepository.save(contact);
        return ResponseEntity.status(HttpStatus.OK).body(entry.getId());
    }



    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity del(@PathVariable int id) {

        Optional<Contact> entry =  contactRepository.findById(id);
        if (!entry.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        contactRepository.delete(entry.get());
        return new ResponseEntity(HttpStatus.OK);
    }

}
