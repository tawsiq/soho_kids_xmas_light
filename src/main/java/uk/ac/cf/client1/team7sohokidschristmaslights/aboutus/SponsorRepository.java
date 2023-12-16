package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import java.io.IOException;
import java.util.List;

public interface SponsorRepository {

    List<Sponsor> getAllSponsors();

    Sponsor getSponsor(Integer id);

    void saveSponsor(Sponsor sponsor);

}
