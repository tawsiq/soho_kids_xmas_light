package uk.ac.cf.client1.team7sohokidschristmaslights.contactPage;

import java.util.List;

// Declare an interface for managing contacts
public interface ContactRepository {

    // Method to retrieve a list of contacts
    List<Contact> getContactList();
    // This method returns a list of contacts. Implementations of this interface
    // will provide the actual logic to fetch the contact data.

    // Method to retrieve a specific contact by ID
    Contact getContact(Integer id);
    // This method retrieves a specific contact based on the provided 'id'.
    // Implementations of this interface will handle the retrieval of a contact
    // with the given ID.

    void saveContactDetails(Contact contact);
    // This method saves the details of a contact. The 'contact' parameter contains
    // the information to be saved (e.g., name, email, phone number).
    // Implementations of this interface will handle the actual saving process,
    // such as storing the data in a database or other storage mechanism.
}
