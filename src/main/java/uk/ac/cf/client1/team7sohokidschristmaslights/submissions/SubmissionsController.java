// Please create separate packages for other pages. Mixing controllers is probably a bad idea...
package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubmissionsController {

    private ImageService imageService; //TODO: Test if warning fix works

    public SubmissionsController(ImageService anImageService){
        this.imageService = anImageService;
    }

    @GetMapping("home/submissions")
    public ModelAndView showYearGroupSubmissions() {
        return new ModelAndView("submissions-page/submissions");
    }
    @GetMapping("home/submissions/submission-details")
    public ModelAndView showSubmissionDetails() {
        return new ModelAndView("submissions-page/submission-details");
    }

}
