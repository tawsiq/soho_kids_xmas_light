package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<ImageClass> getImageItemList();
    ImageClass getImage(Long id);
    byte[] getImageData(ImageClass imageClass) throws IOException;
}
