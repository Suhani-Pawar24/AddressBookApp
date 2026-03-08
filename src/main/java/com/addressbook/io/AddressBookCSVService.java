package com.addressbook.io;

import com.addressbook.model.Contact;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AddressBookCSVService {

    private static final String FILE_NAME = "contacts.csv";

    // Write contacts to CSV
    public void writeContactsToCSV(List<Contact> contacts) {

        try {

            CSVWriter writer = new CSVWriter(new FileWriter(FILE_NAME));

            String[] header = {"ID","FirstName","LastName","Address","City","State","Zip","Phone","Email"};
            writer.writeNext(header);

            for (Contact contact : contacts) {

                String[] data = {
                        String.valueOf(contact.getId()),
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getAddress(),
                        contact.getCity(),
                        contact.getState(),
                        contact.getZip(),
                        contact.getPhoneNumber(),
                        contact.getEmail()
                };

                writer.writeNext(data);
            }

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read contacts from CSV
    public List<Contact> readContactsFromCSV() {

        List<Contact> contacts = new ArrayList<>();

        try {

            CSVReader reader = new CSVReader(new FileReader(FILE_NAME));

            List<String[]> rows = reader.readAll();

            for (int i = 1; i < rows.size(); i++) {

                String[] row = rows.get(i);

                Contact contact = new Contact(
                        Integer.parseInt(row[0]),
                        row[1],
                        row[2],
                        row[3],
                        row[4],
                        row[5],
                        row[6],
                        row[7],
                        row[8]
                );

                contacts.add(contact);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contacts;
    }
}
