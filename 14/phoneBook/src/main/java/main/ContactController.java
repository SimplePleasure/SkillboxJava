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
//        return Storage.getContacts();
        Iterable<Contact> iterable = contactRepository.findAll();
        ArrayList<Contact> contacts = new ArrayList<>();
        for(Contact contact : iterable) {
            contacts.add(contact);
        }
        return contacts;
    }


    @RequestMapping(value = "/contact/", method = RequestMethod.POST)
    public int add(Contact contact) {

        Contact entry= contactRepository.save(contact);
        return entry.getId();

    }

//    @RequestMapping(value = "/contact/{name}", method = RequestMethod.DELETE)
//    public void del(@PathVariable String name) {
//        Storage.delete(name);
//    }



    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity del(@PathVariable int id) {



        Optional<Contact> entry =  contactRepository.findById(id);
        if (!entry.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        contactRepository.delete(entry.get());
        return new ResponseEntity(HttpStatus.OK);





//        if (contactRepository.existsById(id)) {
//            contactRepository.deleteById(id);
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        return new ResponseEntity(HttpStatus.NOT_FOUND);






//        if (Storage.delete(name)) {
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
