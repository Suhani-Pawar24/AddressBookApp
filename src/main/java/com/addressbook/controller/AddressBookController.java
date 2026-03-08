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
    
    // Search Contacts by City
    @GetMapping("/contacts/city/{city}")
    public List<Contact> searchByCity(@PathVariable String city) {

        return addressBookService.searchByCity(city);
    }
    
    // Search Contacts by State
    @GetMapping("/contacts/state/{state}")
    public List<Contact> searchByState(@PathVariable String state) {

        return addressBookService.searchByState(state);
    }
    
    // View Persons by City
    @GetMapping("/contacts/view/city")
    public Map<String, List<Contact>> viewPersonsByCity() {

        return addressBookService.viewPersonsByCity();
    }
    
    // View Persons by State
    @GetMapping("/contacts/view/state")
    public Map<String, List<Contact>> viewPersonsByState() {

        return addressBookService.viewPersonsByState();
    }
}