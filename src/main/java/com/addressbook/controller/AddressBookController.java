package com.addressbook.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;
import com.addressbook.service.AddressBookService;

@RestController
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;


    // Create Address Book
    @PostMapping("/addressbooks")
    public AddressBook createAddressBook(@RequestParam String name) {

        return addressBookService.createAddressBook(name);
    }


    // Get All Address Books
    @GetMapping("/addressbooks")
    public Map<String, AddressBook> getAllAddressBooks() {

        return addressBookService.getAllAddressBooks();
    }


    // Add Contact to Address Book
    @PostMapping("/addressbooks/{name}/contacts")
    public String addContact(@PathVariable String name,
                             @RequestBody Contact contact) {

        return addressBookService.addContact(name, contact);
    }


    // Get Contacts from Address Book
    @GetMapping("/addressbooks/{name}/contacts")
    public List<Contact> getContacts(@PathVariable String name) {

        return addressBookService.getContacts(name);
    }
}