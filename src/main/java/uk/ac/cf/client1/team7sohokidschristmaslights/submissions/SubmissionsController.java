// Please create separate packages for other pages. Mixing controllers is probably a bad idea...
package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SubmissionsController {

    private final ImageService imageService; //TODO: Test warning fix: final

    public SubmissionsController(ImageService anImageService){
        this.imageService = anImageService;
    }

    @GetMapping("home/submissions/submission-details")
    public ModelAndView showSubmissionDetails() {
        return new ModelAndView("submissions-page/submission-details");
    }


// TODO: implement ImageService.getImageItemList()

    @GetMapping("home/submissions")
    public ModelAndView getImageMenu() {
        ModelAndView modelAndView = new ModelAndView("submissions-page/submissions");

        List<ImageClass> ImageItemList = imageService.getImageItemList();
        modelAndView.addObject("ImageItemList", ImageItemList);
        return modelAndView;
    }
    // Use getImage here from image service to fix the "never used" warning. This will be used when querying for specific images -> submission-details.

}
