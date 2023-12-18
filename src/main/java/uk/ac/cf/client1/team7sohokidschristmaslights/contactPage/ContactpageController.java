package uk.ac.cf.client1.team7sohokidschristmaslights.contactPage;

import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactpageController {

    // Declare a reference to the ContactService, this is a seperate file (made after this)
    ContactService contactService;

    // Constructor to inject the ContactService dependency
    public ContactpageController(ContactService contactService){
        this.contactService = contactService;
    }

    // Handler method for the "/home/contactus" URL
    @GetMapping("/home/contactus")
    public ModelAndView hostContactPage(){
        // Return a new ModelAndView with the view name "NewContactUs/NewContactUs"
        return new ModelAndView("NewContactUs/NewContactUs");
    }

    // Handler method for the "/home/contactus/contact-details" URL
    @GetMapping("/home/contactus/contact-details")
    public ModelAndView hostContactDetailsPage(){
        // Create a new ModelAndView with the view name "NewContactUs/new-contact-details"
        ModelAndView modelAndView = new ModelAndView("NewContactUs/new-contact-details");
        // Add the "contactsList" attribute to the model, populated from the ContactService
        modelAndView.addObject("contactsList", contactService.getContactList());
        // Return the populated ModelAndView
        return modelAndView;
    }

    @GetMapping("/home/contactus/addContact")
    public ModelAndView hostContactForm(){
        return new ModelAndView("NewContactUs/NewContactUs");
    }
    @PostMapping("/home/contactus/addContact")
    public ModelAndView processContactForm(Contact contact){
        contactService.saveContact(contact);
        return new ModelAndView("redirect:/home/contactus/contact-details");
    }
}
