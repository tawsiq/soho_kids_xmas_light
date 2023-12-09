package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import java.util.List;

// Working with the database to retrieve submission information.
public interface ImageRepository {
    List<List<ImageClass>> getImageItemList();
    ImageClass getImage(Long id, Boolean light); // use the primary key to locate specific images in the database. Whether it's a light or a drawing is dependent on which table is used.
    Boolean lightCounterpartPresent(Long id);
    void addRatingToSubmission(RatingClass rating);
}
