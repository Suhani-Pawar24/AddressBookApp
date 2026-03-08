package com.addressbook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}