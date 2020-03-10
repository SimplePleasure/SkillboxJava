package main;

import response.Contact;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    static ArrayList<Contact> list = new ArrayList<>();

    public static List<Contact> getContacts() {
        return list;
    }

    public static int addNewContact(Contact contact) {
        list.add(contact);
        int id = list.size();
        contact.setId(id);
        return id;
    }

    public static void delete (String name) {
        for(Contact c: list) {
            if (c.getName().equals(name)) {
                list.remove(c);
                break;
            }
        }
    }



}
