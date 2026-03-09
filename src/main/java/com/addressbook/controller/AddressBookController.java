package com.addressbook.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.addressbook.db.AddressBookDBService;
import com.addressbook.io.AddressBookCSVService;
import com.addressbook.io.AddressBookFileService;
import com.addressbook.io.AddressBookJsonService;
import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;
import com.addressbook.service.AddressBookService;
import com.addressbook.service.JSONServerService;

@RestController
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private JSONServerService jsonServerService;

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
    
    // Write Contacts to JSON file
    @GetMapping("/contacts/json/write")
    public String writeContactsToJSON() {

        List<Contact> contacts = addressBookService.getAllContacts();

        AddressBookJsonService jsonService = new AddressBookJsonService();
        jsonService.writeContactsToJSON(contacts);

        return "Contacts written to JSON file";
    }
    
    // Read Contacts from JSON file
    @GetMapping("/contacts/json/read")
    public List<Contact> readContactsFromJSON() {

    	AddressBookJsonService jsonService = new AddressBookJsonService();
        return jsonService.readContactsFromJSON();
    }
    
    // Get Contacts from Database
    @GetMapping("/contacts/db")
    public List<Contact> getContactsFromDB() {

        AddressBookDBService dbService = new AddressBookDBService();

        return dbService.getContactsFromDB();
    }
    
    // Add Contact to Database
    @PutMapping("/contacts/db/update")
    public String updateContactInDB(@RequestBody Contact contact) {

        AddressBookDBService dbService = new AddressBookDBService();

        return dbService.updateContact(contact);
    }
    
    // Get Contacts added in a specific date range from Database
    @GetMapping("/contacts/db/date-range")
    public List<Contact> getContactsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        AddressBookDBService dbService = new AddressBookDBService();

        return dbService.getContactsByDateRange(startDate, endDate);
    }
    
    // Count Contacts by City from Database
    @GetMapping("/contacts/db/count/city")
    public Map<String, Integer> countContactsByCity1() {

        AddressBookDBService dbService = new AddressBookDBService();

        return dbService.countContactsByCity();
    }
    
    // Count Contacts by State from Database
    @GetMapping("/contacts/db/count/state")
    public Map<String, Integer> countContactsByState1() {

        AddressBookDBService dbService = new AddressBookDBService();

        return dbService.countContactsByState();
    }
    @PostMapping("/contacts/db/add")
    public String addContactToDB(@RequestBody Contact contact) {

        AddressBookDBService dbService = new AddressBookDBService();

        return dbService.addContact(contact);
    }
    @PostMapping("/contacts/db/add-multiple")
    public String addMultipleContacts(@RequestBody List<Contact> contacts) {

        AddressBookDBService dbService = new AddressBookDBService();

        dbService.addMultipleContacts(contacts);

        return "Multiple contacts are being added using threads";
    }
    @GetMapping("/contacts/jsonserver")
    public List<Contact> getContactsFromJSONServer() {

        JSONServerService jsonService = new JSONServerService();

        return jsonService.getContactsFromJSONServer();
    }
    @PostMapping("/contacts/jsonserver/add-multiple")
    public String addContactsToJSONServer(@RequestBody List<Contact> contacts) {

        JSONServerService jsonService = new JSONServerService();

        return jsonService.addMultipleContactsToJSONServer(contacts);
    }
    @PutMapping("/contacts/{id}")
    public Contact updateContact(@PathVariable int id, @RequestBody Contact contact) {

        return jsonServerService.updateContact(id, contact);
    }
    @DeleteMapping("/contacts/jsonserver/delete/{id}")
    public String deleteContactFromJSONServer(@PathVariable int id) {

        JSONServerService jsonService = new JSONServerService();

        return jsonService.deleteContactFromJSONServer(id);
    }






}