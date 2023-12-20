package uk.ac.cf.client1.team7sohokidschristmaslights.contactPage;

// Adding new imports for email automation:
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


import org.springframework.stereotype.Service;

import java.util.List;

// Mark the class as a service component using the @Service annotation
@Service
public class ContactServiceImpl implements ContactService {

    // Declare a reference to the ContactRepository
    ContactRepository contactRepository;

    // Constructor: Inject the ContactRepository dependency
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Method to retrieve a specific contact by ID
    public Contact getContact(Integer id) {
        return contactRepository.getContact(id);
    }

    // Method to retrieve a list of contacts
    public List<Contact> getContactList() {
        return contactRepository.getContactList();
    }

    public void saveContact(Contact contact){
        contactRepository.saveContactDetails(contact);
    }
}
