package com.addressbook.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;

@Service
public class AddressBookService {

    private Map<String, AddressBook> addressBooks = new HashMap<>();


    // Create Address Book
    public AddressBook createAddressBook(String name) {

        AddressBook addressBook = new AddressBook(name);
        addressBooks.put(name, addressBook);

        return addressBook;
    }


    // Get All Address Books
    public Map<String, AddressBook> getAllAddressBooks() {

        return addressBooks;
    }


    // Add Contact to Address Book
    public String addContact(String addressBookName, Contact contact) {

        AddressBook addressBook = addressBooks.get(addressBookName);

        if (addressBook == null) {
            return "AddressBook not found";
        }

        List<Contact> contacts = addressBook.getContacts();

        if (contacts.contains(contact)) {
            return "Duplicate contact not allowed";
        }

        contacts.add(contact);

        cityMap.computeIfAbsent(contact.getCity(), k -> new ArrayList<>()).add(contact);
        stateMap.computeIfAbsent(contact.getState(), k -> new ArrayList<>()).add(contact);

        return "Contact added successfully";
    }
    
    // Get Contacts from Address Book
    public List<Contact> getContacts(String addressBookName) {

        AddressBook addressBook = addressBooks.get(addressBookName);

        if (addressBook != null) {
            return addressBook.getContacts();
        }

        return null;
    }
    
    // Search Contacts by City across all Address Books
    public List<Contact> searchByCity(String city) {

        return addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .toList();
    }
    
    // Search Contacts by State across all Address Books
    public List<Contact> searchByState(String state) {

        return addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .toList();
    }
    
    // View Persons by City across all Address Books
    public Map<String, List<Contact>> viewPersonsByCity() {

        return addressBooks.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getCity));
    }
    
    // View Persons by State across all Address Books
    public Map<String, List<Contact>> viewPersonsByState() {

        return addressBooks.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getState));
    }
    
    // Additional Maps for City and State to optimize search and view operations
    private Map<String, List<Contact>> cityMap = new HashMap<>();
    private Map<String, List<Contact>> stateMap = new HashMap<>();
    
    // Count Contacts by City across all Address Books
    public Map<String, Long> countContactsByCity() {

        return addressBooks.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getCity, Collectors.counting()));
    }
    
    // Count Contacts by State across all Address Books
    public Map<String, Long> countContactsByState() {

        return addressBooks.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getState, Collectors.counting()));
    }
    
    // Sort Contacts by Name across all Address Books
    public List<Contact> sortContactsByName() {

        return addressBooks.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .sorted(Comparator.comparing(Contact::getFirstName))
                .toList();
    }
    
    // Sort Contacts by City across all Address Books
    public List<Contact> sortContactsByCity() {

        return addressBooks.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .sorted(Comparator.comparing(Contact::getCity))
                .toList();
    }
    
    // Sort Contacts by State across all Address Books
    public List<Contact> sortContactsByState() {

        return addressBooks.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .sorted(Comparator.comparing(Contact::getState))
                .toList();
    }
    
    // Sort Contacts by Zip across all Address Books
    public List<Contact> sortContactsByZip() {

        return addressBooks.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .sorted(Comparator.comparing(Contact::getZip))
                .toList();
    }
    
    // Get All Contacts across all Address Books
    public List<Contact> getAllContacts() {

        return addressBooks.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .toList();
    }
}