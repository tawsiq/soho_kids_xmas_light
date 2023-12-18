package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import java.util.List;

public interface SponsorService {
    List<Sponsor> getAllSponsors();
    Sponsor getSponsor(Integer id);
    void saveSponsor(Sponsor sponsor);
}
