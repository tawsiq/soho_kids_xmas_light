// Please create separate packages for other pages. Mixing controllers is probably a bad idea...
package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class SubmissionsController {

    private final ImageService imageService; //TODO: Test warning fix: final

    // TODO: Come back and autowire this if you face issues. Also use "anImageService" for the parameter.
    public SubmissionsController(ImageService imageService){
        this.imageService = imageService;
    }
    // Use URL for debugging template.
    @GetMapping("home/submissions/submission-details-template")
    public ModelAndView showSubmissionDetails() {
        return new ModelAndView("submissions-page/submission-details");
    }


    @GetMapping("home/submissions")
    public ModelAndView getImageMenu() {
        ModelAndView modelAndView = new ModelAndView("submissions-page/submissions");

        List<List<ImageClass>> imageItemList = imageService.getImageItemList();
        modelAndView.addObject("imageItemList", imageItemList);
        return modelAndView;
    }

    // Controller endpoint to serve image data based on image metadata
    @GetMapping("/getImage/{id}/{light}")
    public ResponseEntity<byte[]> getImageDataForTemplate(@PathVariable Long id,
                                                          @PathVariable Boolean light) throws IOException {
        // Retrieve ImageClass based on the provided ID (from database)
        ImageClass image = imageService.getImage(id, light);
        // Get image data bytes from the service method
        byte[] imageData = imageService.getImageData(image);
        // Set appropriate headers and return the image data
        if (image.getMimeType().equals("image/jpeg")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);

        } else {
            // The only image types stored in the DB are jpeg & png
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageData);
        }
    }
    // Page linking controller -> Allows user to visit submission-details page.
    @GetMapping("/home/submissions/{id}")
    public ModelAndView displaySubmissionDetails(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("submissions-page/submission-details");

        ImageClass drawing = imageService.getImage(id, false); // Required for all detail-pages.

        if (imageService.lightCounterpartPresent(id)) {
            ImageClass light = imageService.getImage(id, true); // Required only for winning submissions.
            modelAndView.addObject("light", light);
        }
        modelAndView.addObject("drawing", drawing);
        return modelAndView;
    }
    // These next two handle reviews. One hosts & the other receives, processes and redirects. Note that these URLS shouldn't be used elsewhere. Same goes for the rest.
    @GetMapping("home/submissions/{id}/add-rating-to-submission")
    public ModelAndView hostRatingSection(@PathVariable Long id){
        return new ModelAndView("submissions-page/submission-details");
    }
    @PostMapping("home/submissions/{id}/add-rating-to-submission")
    public ModelAndView processPostedRating(@PathVariable Long id){
        // TODO: Come back once you've made a review entity class.
        return new ModelAndView();
    }

}
