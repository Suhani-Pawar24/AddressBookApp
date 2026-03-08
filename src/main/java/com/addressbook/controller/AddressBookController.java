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

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return addressBookService.addContact(contact);
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return addressBookService.getAllContacts();
    }
}