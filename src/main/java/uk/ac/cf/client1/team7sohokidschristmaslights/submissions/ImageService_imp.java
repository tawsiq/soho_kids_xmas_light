package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.stereotype.Service;
import uk.ac.cf.client1.team7sohokidschristmaslights.moderation.TextModerationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ImageService_imp implements ImageService{

    private final ImageRepository imageRepository;
    private final TextModerationService textModerationService;

    public ImageService_imp(ImageRepository anImageRepository, TextModerationService atextModerationService) {
        this.imageRepository = anImageRepository;
        this.textModerationService = atextModerationService;
    }
    // Implement the ImageService Methods using the repository.
    public List<List<ImageClass>> getImageItemList() {
        return imageRepository.getImageItemList();
    }

    public ImageClass getImage(Long id, Boolean light) {
        return imageRepository.getImage(id, light);
    }

    public Boolean lightCounterpartPresent(Long id){
        return imageRepository.lightCounterpartPresent(id);
    }

    public List<RatingClass> getRatingList(Long submission_id){
        return imageRepository.getRatingList(submission_id);
    }
    // Retrieves image data based on the absolute file path stored in ImageClass
    public byte[] getImageData(ImageClass image) throws IOException {

        Path pathToImage = Paths.get(image.getFilePath());
        // Read the image data into a byte array
        return Files.readAllBytes(pathToImage);
    }
    public void moderateRating(RatingClass rating) {
        String moderatedName = textModerationService.moderateText(rating.getRaterName());
        String moderatedComment = textModerationService.moderateText(rating.getCommentText());

        rating.setRaterName(moderatedName);
        rating.setCommentText(moderatedComment);

    }
    public void storeRating(RatingClass rating) {
        imageRepository.storeRating(rating);
    }
    public String logDateTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date = currentDateTime.format(formatDateTime).substring(0, 10);
        String time = currentDateTime.format(formatDateTime).substring(11, 16);
        return "On " + date + " at " + time;
    }
}
