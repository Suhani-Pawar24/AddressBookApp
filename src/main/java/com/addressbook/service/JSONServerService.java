package com.addressbook.service;
import com.addressbook.model.Contact;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

public class JSONServerService {

	    private static final String JSON_SERVER_URL =
	            "http://localhost:3000/contacts";

	    public List<Contact> getContactsFromJSONServer() {

	        RestTemplate restTemplate = new RestTemplate();

	        Contact[] contacts =
	                restTemplate.getForObject(JSON_SERVER_URL, Contact[].class);

	        return Arrays.asList(contacts);
	    }
	    public String addMultipleContactsToJSONServer(List<Contact> contacts) {

	        RestTemplate restTemplate = new RestTemplate();

	        String url = "http://localhost:3000/contacts";

	        contacts.forEach(contact -> {
	            restTemplate.postForObject(url, contact, Contact.class);
	        });

	        return "Contacts added to JSON Server successfully";
	    }

}
