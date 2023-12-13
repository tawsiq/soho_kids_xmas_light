// Please create separate packages for other pages. Mixing controllers is probably a bad idea...
package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class SubmissionsController {

    private final ImageService imageService;

    StringBuilder htmlBuilder;

    // TODO: Make image viewing asynchronous
    public SubmissionsController(ImageService imageService){
        this.imageService = imageService;
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
//        List<RatingClass> ratingList = imageService.getRatingList(id);
//        List<RatingClass> filteredList = ratingList.stream()
//                .filter(obj -> !Objects.equals(obj.getCommentText(), ""))
//                .toList();

        modelAndView.addObject("drawing", drawing);
//        modelAndView.addObject("likeCount", imageService.countLikes(id));
//        modelAndView.addObject("ratingList", filteredList); // Also retrieve rating list from the database to play around with in the template.

        return modelAndView;
    }
    // These next two handle reviews. One hosts & the other receives, processes and redirects. Note that these URLS shouldn't be used elsewhere. Same goes for the rest.
    // TODO: Transform this into an AJAX request.
    @GetMapping("/home/submissions/{id}/addReview")
    public ModelAndView hostRatingSection(@PathVariable Long id){
        return new ModelAndView("submissions-page/submission-details");
    }
    @PostMapping("/home/submissions/{id}/addReview")
    public ModelAndView processPostedRating(@PathVariable Long id, RatingClass rating) {

        if(rating.getRaterName().equals("")){
            rating.setRaterName("Anonymous");
        }

        if (ratingIsNotNull(rating)){
            rating.setDateTime(imageService.logDateTime());
            rating.setSubmissionId(id);
            imageService.moderateRating(rating);
            imageService.storeRating(rating);
        }
        return new ModelAndView("redirect:/home/submissions/" + id);
    }
    private boolean ratingIsNotNull(RatingClass rating){
        return rating.getLiked()!=null || !Objects.equals(rating.getCommentText(), "") || !Objects.equals(rating.getRaterName(), "");
    }

    @GetMapping(value = "/home/submissions/{id}/getComments", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getComments(@PathVariable Long id) {
        // Mimics the fetching system that occurs on page load or refresh. The aim is to stop that...
        // ... and rely only on this. Something like "document.onload" should work (see async JS file linked to submission-details template)
        List<RatingClass> ratingList = imageService.getRatingList(id);
        List<RatingClass> filteredList = ratingList.stream()
                .filter(obj -> !Objects.equals(obj.getCommentText(), ""))
                .toList();

        // Generating HTML for comments section. This would have been done by Thymeleaf on page refresh or load.
        htmlBuilder = new StringBuilder();
        for (RatingClass rating : filteredList) {
            htmlBuilder.append("<li class=\"user-comment\">");
            htmlBuilder.append("<p class=\"user-name\">").append(rating.getRaterName()).append("</p>");
            htmlBuilder.append("<p class=\"user-comment-text\">").append(rating.getCommentText()).append("</p>");
            htmlBuilder.append("<div class=\"divider-line\"></div>");
            htmlBuilder.append("</li>");
        }
        return htmlBuilder.toString();
    }

    @GetMapping(value = "/home/submissions/{id}/getLikes", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getLikes(@PathVariable Long id) {

        Integer likeCount = imageService.countLikes(id);

        htmlBuilder = new StringBuilder();
        htmlBuilder.append("<div id=\"likeCount\">").append(likeCount).append("</div>");

        return htmlBuilder.toString();
    }

}
//