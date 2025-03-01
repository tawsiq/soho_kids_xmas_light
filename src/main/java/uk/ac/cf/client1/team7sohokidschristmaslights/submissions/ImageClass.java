package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// Stores information about the image's metadata, retrieved from the database. Mirrors MenuItemClass
public class ImageClass {
    private Long id;
    private String fileName;
    private String filePath;
    private String mimeType;

    private Integer submissionYear;
    private String yearGroup;
    private String participantName; // not to be confused with filename.

}
// Methods to obtain this info will be covered in the service layer.
