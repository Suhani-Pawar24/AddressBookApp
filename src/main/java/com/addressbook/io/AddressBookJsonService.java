package com.addressbook.io;

import com.addressbook.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddressBookJsonService {

    private static final String FILE_NAME = "contacts.json";

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Write contacts to JSON
    public void writeContactsToJSON(List<Contact> contacts) {

        try {

            FileWriter writer = new FileWriter(FILE_NAME);

            gson.toJson(contacts, writer);

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read contacts from JSON
    public List<Contact> readContactsFromJSON() {

        List<Contact> contacts = new ArrayList<>();

        try {

            FileReader reader = new FileReader(FILE_NAME);

            Type contactListType = new TypeToken<List<Contact>>(){}.getType();

            contacts = gson.fromJson(reader, contactListType);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contacts;
    }
}
