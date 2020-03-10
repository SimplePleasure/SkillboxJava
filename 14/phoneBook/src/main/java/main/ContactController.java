package main;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import response.Contact;

import java.util.List;

@RestController
public class ContactController {

    @RequestMapping(value = "/contact/", method = RequestMethod.GET)
    public List<Contact> list() {
        return Storage.getContacts();
    }


    @RequestMapping(value = "/contact/", method = RequestMethod.POST)
    public int add(Contact contact) {
        return Storage.addNewContact(contact);
    }

    @RequestMapping(value = "/contact/{name}", method = RequestMethod.DELETE)
    public void del(@PathVariable String name) {
        Storage.delete(name);
    }

}
