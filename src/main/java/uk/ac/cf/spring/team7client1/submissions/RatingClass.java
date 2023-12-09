package uk.ac.cf.spring.team7client1.submissions;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingClass {
    private Long submissionId;
    private String raterName;
    private String commentText;
    private Boolean liked;

    private String dateTime;
}
