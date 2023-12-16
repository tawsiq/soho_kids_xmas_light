package uk.ac.cf.client1.team7sohokidschristmaslights.contactPage;

import java.util.List;

// Declare an interface for managing contacts
public interface ContactRepository {

    // Method to retrieve a list of contacts
    List<Contact> getContactList();

    // Method to retrieve a specific contact by ID
    Contact getContact(Integer id);
}
