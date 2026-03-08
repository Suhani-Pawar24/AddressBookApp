package com.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.addressbook.model.Contact;
import com.addressbook.service.AddressBookService;

@RestController
@RequestMapping("/contacts")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    // Add Contact
    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return addressBookService.addContact(contact);
    }

    // Get All Contacts
    @GetMapping
    public List<Contact> getAllContacts() {
        return addressBookService.getAllContacts();
    }

    // Get Contact By ID
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable int id) {
        return addressBookService.getContactById(id);
    }

    // Update Contact
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable int id,
                                 @RequestBody Contact contact) {

        return addressBookService.updateContact(id, contact);
    }
}