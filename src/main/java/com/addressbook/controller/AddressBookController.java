package com.addressbook.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.addressbook.io.AddressBookCSVService;
import com.addressbook.io.AddressBookFileService;
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
 // Count Contacts by City
    @GetMapping("/contacts/count/city")
    public Map<String, Long> countContactsByCity() {

        return addressBookService.countContactsByCity();
    }
    
    // Count Contacts by State
    @GetMapping("/contacts/count/state")
    public Map<String, Long> countContactsByState() {

        return addressBookService.countContactsByState();
    }
 // Sort Contacts by Name across all Address Books
    @GetMapping("/contacts/sort/name")
    public List<Contact> sortContactsByName() {

        return addressBookService.sortContactsByName();
    }
 // Sort Contacts by City across all Address Books
    @GetMapping("/contacts/sort/city")
    public List<Contact> sortContactsByCity() {

        return addressBookService.sortContactsByCity();
    }
    
    // Sort Contacts by State across all Address Books
    @GetMapping("/contacts/sort/state")
    public List<Contact> sortContactsByState() {

        return addressBookService.sortContactsByState();
    }
    
    // Sort Contacts by Zip across all Address Books
    @GetMapping("/contacts/sort/zip")
    public List<Contact> sortContactsByZip() {

        return addressBookService.sortContactsByZip();
    }
    @GetMapping("/contacts/write")
    public String writeContactsToFile() {

        List<Contact> contacts = addressBookService.getAllContacts();

        AddressBookFileService fileService = new AddressBookFileService();
        fileService.writeContactsToFile(contacts);

        return "Contacts written to file";
    }
 // Write Contacts to CSV file
    @GetMapping("/contacts/csv/write")
    public String writeContactsToCSV() {

        List<Contact> contacts = addressBookService.getAllContacts();

        AddressBookCSVService csvService = new AddressBookCSVService();
        csvService.writeContactsToCSV(contacts);

        return "Contacts written to CSV file";
    }
    
    // Read Contacts from CSV file
    @GetMapping("/contacts/csv/read")
    public List<Contact> readContactsFromCSV() {

        AddressBookCSVService csvService = new AddressBookCSVService();
        return csvService.readContactsFromCSV();
    }
}