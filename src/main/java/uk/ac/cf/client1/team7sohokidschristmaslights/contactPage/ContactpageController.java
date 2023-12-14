package uk.ac.cf.client1.team7sohokidschristmaslights.contactPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactpageController {

    @GetMapping("/home/contactus")
    public ModelAndView hostContactPage(){
        return new ModelAndView("NewContactUs/NewContactUs");
    }
}
