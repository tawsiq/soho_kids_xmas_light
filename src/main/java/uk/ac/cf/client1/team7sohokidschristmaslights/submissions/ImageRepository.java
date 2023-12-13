package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import java.util.List;

// Working with the database to retrieve submission information.
public interface ImageRepository {
    List<List<ImageClass>> getImageItemList();
    ImageClass getImage(Long id, Boolean light); // use the primary key to locate specific images in the database. Whether it's a light or a drawing is dependent on which table is used.
    List<RatingClass> getRatingList(Long submission_id);
    Boolean lightCounterpartPresent(Long id);
    void storeRating(RatingClass rating);
    Integer getLikeCount(Long id);
    void updateLikeCount(Long id, Integer increment);
}
