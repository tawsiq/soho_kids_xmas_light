// Please create separate packages for other pages. Mixing controllers is probably a bad idea...
package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubmissionsController {

    @GetMapping("home/year-groups")
    public ModelAndView showYearGroups() {
        return new ModelAndView("submissions-page/year-groups-list");
    }
    @GetMapping("home/year-groups/group-submissions")
    public ModelAndView showYearGroupSubmissions() {
        return new ModelAndView("submissions-page/group-submissions");
    }
    @GetMapping("home/year-groups/group-submissions/submission-details")
    public ModelAndView showSubmissionDetails() {
        return new ModelAndView("submissions-page/submission-details");
    }

}
