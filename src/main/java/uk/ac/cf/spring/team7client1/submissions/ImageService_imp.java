package uk.ac.cf.spring.team7client1.submissions;

//import uk.ac.cf.client1.team7sohokidschristmaslights.moderation.TextModerationService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService_imp implements ImageService{

    private final ImageRepository imageRepository;
//    TextModerationService textModerationService = new TextModerationService();

    public ImageService_imp(ImageRepository anImageRepository) {
        this.imageRepository = anImageRepository;
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
    public void moderateComment(RatingClass rating) {

//        String moderatedName = textModerationService.moderateText(rating.getRaterName());
//        String moderatedComment = textModerationService.moderateText(rating.getCommentText());
//
//        rating.setRaterName(moderatedName);
//        rating.setCommentText(moderatedComment);
    }
}
