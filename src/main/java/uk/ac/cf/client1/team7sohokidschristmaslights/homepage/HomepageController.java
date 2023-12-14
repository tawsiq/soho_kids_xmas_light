package uk.ac.cf.client1.team7sohokidschristmaslights.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomepageController {

    @GetMapping("/home")
    public ModelAndView hostHomePage() {
        return new ModelAndView("Homepage/index");
    }

}
