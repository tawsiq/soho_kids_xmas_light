package uk.ac.cf.client1.team7sohokidschristmaslights.contactPage;

import org.springframework.stereotype.Service;

import java.util.List;


// Adding necessary imports for the Email Automation System

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


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
        // sendEmail(contact); // Send email after saving contact details
                            // This is part of the additional code in order to get automated emails to send
                            // NOTE I HAVE DISABLED THE EMAIL AUTOMATION DUE TO NOT BEING ABLE TO CONFIGURE MY
                            // GMAIL PROPERLY:

    // Google has tightened security for Gmail accounts.
    // By default, it doesn’t allow “less secure apps”
    // (such as THE Java application) to access the account.

    }


    // Adding function in order to be able to send emails using JavaMail

    // This function sets up the SMTP server properties, email credentials, and email content.

    // It creates a Session object with authentication.

    // Then, it creates a MimeMessage, sets message details, and sends the message using Transport.send().

    public void sendEmail(Contact contact) {
        // SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Email credentials
        String username = "shadmo@cardiff.ac.uk";
        String password = "Password";

        // Email content
        String toEmail = "shadmo@cardiff.ac.uk";
        String subject = "New Contact Form Submission";
        String messageContent = "Name: " + contact.getName() + "\n" +
                "Email: " + contact.getEmail() + "\n" +
                "Subject: " + contact.getSubject() + "\n" +
                "Message: " + contact.getMessage();

        // Create a Session object with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set message details
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageContent);

            // Send the message
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle exceptions (log or alert user)
        }
    }
}






