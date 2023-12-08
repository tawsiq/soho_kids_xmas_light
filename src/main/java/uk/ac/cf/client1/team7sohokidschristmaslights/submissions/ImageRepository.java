package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import java.awt.*;
import java.util.List;

// Working with the database to retrieve submission information.
public interface ImageRepository {
    List<ImageClass> getImageItems();
    ImageClass getImage(Long id); // use the primary key to locate specific images in the database. Whether it's a light or a drawing is dependent on which table is used.

}
