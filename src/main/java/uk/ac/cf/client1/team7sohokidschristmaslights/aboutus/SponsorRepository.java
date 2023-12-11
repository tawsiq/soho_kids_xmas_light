package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import java.util.List;

public interface SponsorRepository {

    List<Sponsor> getAllSponsors();

    void saveSponsor(Sponsor sponsor);
}
