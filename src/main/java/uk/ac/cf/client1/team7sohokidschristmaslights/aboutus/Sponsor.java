package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sponsor {

    private Integer sponsorId;
    private String companyName;
    private String contactPerson;
    private String email;

}