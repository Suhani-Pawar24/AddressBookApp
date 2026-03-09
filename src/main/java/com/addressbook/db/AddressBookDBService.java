package com.addressbook.db;

import com.addressbook.model.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Service to handle database operations for Address Book
public class AddressBookDBService {

    private static final String URL =
            "jdbc:mysql://localhost:3306/addressbook_db";

    private static final String USER = "root";
    private static final String PASSWORD = "yourpassword";


    public List<Contact> getContactsFromDB() {

        List<Contact> contacts = new ArrayList<>();

        try {

            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM contacts";

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                Contact contact = new Contact(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"),
                        rs.getString("phone"),
                        rs.getString("email")
                );

                contacts.add(contact);
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contacts;
    }
    
    // Update contact in database
    public String updateContact(Contact contact) {

        String query = "UPDATE contacts SET first_name=?, last_name=?, address=?, city=?, state=?, zip=?, phone=?, email=? WHERE id=?";

        try {

            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getAddress());
            statement.setString(4, contact.getCity());
            statement.setString(5, contact.getState());
            statement.setString(6, contact.getZip());
            statement.setString(7, contact.getPhoneNumber());
            statement.setString(8, contact.getEmail());
            statement.setInt(9, contact.getId());

            int rowsUpdated = statement.executeUpdate();

            connection.close();

            if (rowsUpdated > 0) {
                return "Contact updated successfully";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Update failed";
    }
}