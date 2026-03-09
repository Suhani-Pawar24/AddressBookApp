package com.addressbook.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.addressbook.model.Contact;
public class AddressBookDBService 
{

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
}
