package com.addressbook.db;

import com.addressbook.model.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Service to handle database operations for Address Book
public class AddressBookDBService {

    private static final String URL =
            "jdbc:mysql://localhost:3306/addressbook_db";

    private static final String USER = "root";
    private static final String PASSWORD = "Suhani@1123";


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
    public List<Contact> getContactsByDateRange(String startDate, String endDate) {

        List<Contact> contacts = new ArrayList<>();

        String query = "SELECT * FROM contacts WHERE date_added BETWEEN ? AND ?";

        try {

            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, startDate);
            statement.setString(2, endDate);

            ResultSet rs = statement.executeQuery();

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
    //Count by City
    public Map<String, Integer> countContactsByCity() {

        Map<String, Integer> cityCount = new HashMap<>();

        String query = "SELECT city, COUNT(*) as count FROM contacts GROUP BY city";

        try {

            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                String city = rs.getString("city");
                int count = rs.getInt("count");

                cityCount.put(city, count);
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityCount;
    }
    //Count by State
    public Map<String, Integer> countContactsByState() {

        Map<String, Integer> stateCount = new HashMap<>();

        String query = "SELECT state, COUNT(*) as count FROM contacts GROUP BY state";

        try {

            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                String state = rs.getString("state");
                int count = rs.getInt("count");

                stateCount.put(state, count);
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stateCount;
    }
    public String addContact(Contact contact) {

        String query = "INSERT INTO contacts(first_name,last_name,address,city,state,zip,phone,email,date_added) VALUES(?,?,?,?,?,?,?,?,CURDATE())";

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

            int rowsInserted = statement.executeUpdate();

            connection.close();

            if (rowsInserted > 0) {
                return "Contact added successfully";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Insert failed";
    }

}