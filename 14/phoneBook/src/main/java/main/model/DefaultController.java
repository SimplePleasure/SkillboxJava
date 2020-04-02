package main.model;

import main.model.Contact;
import main.model.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    ContactRepository contactRepository;


    @RequestMapping("/")
    public String index(Model model) {

        Iterable<Contact> contactIterable = contactRepository.findAll();
        ArrayList<Contact> contacts = new ArrayList<>();
        for(Contact contact : contactIterable) {
            contacts.add(contact);
        }
        model.addAttribute("Contacts", contacts);
        model.addAttribute("Count", contacts.size());

        return "index.html";
    }


}
