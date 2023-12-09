package uk.ac.cf.spring.team7client1.submissions;


import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<List<ImageClass>> getImageItemList();
    ImageClass getImage(Long id, Boolean light);
    List<RatingClass> getRatingList(Long submission_id);
    Boolean lightCounterpartPresent(Long id);
    byte[] getImageData(ImageClass imageClass) throws IOException;
    void moderateComment(RatingClass rating);
}
