// Please create separate packages for other pages. Mixing controllers is probably a bad idea...
package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubmissionsController {

    @GetMapping("home/submissions")
    public ModelAndView submissionYearList() {
        return new ModelAndView("submissions/years-list");
    }
}
