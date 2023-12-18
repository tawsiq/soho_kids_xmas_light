package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingClass {
    private Long submissionId;
    private String raterName;
    private String commentText;

    private String dateTime;
}
