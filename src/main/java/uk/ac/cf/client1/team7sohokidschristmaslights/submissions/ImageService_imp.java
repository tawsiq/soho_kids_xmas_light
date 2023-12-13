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

    private ImageRepository imageRepository;
    private TextModerationService textModerationService;

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

    public Integer countLikes(Long id){
        return imageRepository.countLikes(id);
    }
    // Retrieves image data based on the absolute file path stored in ImageClass
    public byte[] getImageData(ImageClass image) throws IOException {

        Path pathToImage = Paths.get(image.getFilePath());
        // Read the image data into a byte array
        return Files.readAllBytes(pathToImage);
    }

    public void moderateRating(RatingClass rating) {
        System.out.printf("%n --- Rating received into moderateRating ---%n");

        String moderatedName = textModerationService.moderateText(rating.getRaterName());
        System.out.printf("Name changed to %s%n", moderatedName);

        String moderatedComment = textModerationService.moderateText(rating.getCommentText());
        System.out.printf("Comment changed to %s%n", moderatedComment);

        rating.setRaterName(moderatedName);
        System.out.printf("New name set%n");

        rating.setCommentText(moderatedComment);
        System.out.printf("New comment set%n");
    }
    public void storeRating(RatingClass rating) {
        imageRepository.storeRating(rating);
    }
    public String logDateTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = currentDateTime.format(formatDateTime).substring(0, 10);
        String time = currentDateTime.format(formatDateTime).substring(11, 16);
        return "On " + date + " at " + time;
    }

}
