package uk.ac.cf.client1.team7sohokidschristmaslights.contactPage;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactpageController {

    private final ContactService contactService;
    private final EmailService emailService; // Inject EmailService

    @Autowired
    public ContactpageController(ContactService contactService, EmailService emailService){
        this.contactService = contactService;
        this.emailService = emailService; // Initialize EmailService
    }

    // Handler method for the "/home/contactus" URL
    @GetMapping("/home/contactus")
    public ModelAndView hostContactPage(){
        return new ModelAndView("NewContactUs/NewContactUs");
    }

    // Handler method for the "/home/contactus/contact-details" URL
    @GetMapping("/home/contactus/contact-details")
    public ModelAndView hostContactDetailsPage(){
        ModelAndView modelAndView = new ModelAndView("NewContactUs/new-contact-details");
        modelAndView.addObject("contactsList", contactService.getContactList());
        return modelAndView;
    }

    @GetMapping("/home/contactus/addContact")
    public ModelAndView hostContactForm(){
        return new ModelAndView("NewContactUs/NewContactUs");
    }

    @PostMapping("/home/contactus/addContact")
    public ModelAndView processContactForm(@ModelAttribute("contact") Contact contact) throws MessagingException {
        contactService.saveContact(contact);
        emailService.sendEmail(contact); // Invoke EmailService to send email
        return new ModelAndView("redirect:/home/contactus/contact-details");
    }
}
