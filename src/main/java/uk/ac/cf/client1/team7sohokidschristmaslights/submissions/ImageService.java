package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<List<ImageClass>> getImageItemList();
    ImageClass getImage(Long id, Boolean light);
    List<RatingClass> getRatingList(Long submission_id);
    Boolean lightCounterpartPresent(Long id);
    byte[] getImageData(ImageClass imageClass) throws IOException;
    void moderateRating(RatingClass rating);
    void storeRating(RatingClass rating);
    String logDateTime();
}
