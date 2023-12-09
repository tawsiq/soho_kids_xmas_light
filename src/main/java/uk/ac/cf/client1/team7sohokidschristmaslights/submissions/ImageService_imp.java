package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService_imp implements ImageService{

    private final ImageRepository imageRepository;

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
//
//        if (rating.getCommentText()){ // .isSafe()
//            imageRepository.addRatingToSubmission(rating);
//
//        } else {
//            String safeComment = rating.getCommentText();//.change
//            rating.setCommentText(safeComment);
//            imageRepository.addRatingToSubmission(rating);
//        }
    }
}
