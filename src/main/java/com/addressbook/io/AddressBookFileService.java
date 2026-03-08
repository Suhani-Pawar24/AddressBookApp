package com.addressbook.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.addressbook.model.Contact;

public class AddressBookFileService {

    public void writeContactsToFile(List<Contact> contacts) {

        try {
            FileWriter writer = new FileWriter("contacts.txt");

            for (Contact contact : contacts) {
                writer.write(contact.getFirstName() + " "
                        + contact.getLastName() + " "
                        + contact.getCity() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
