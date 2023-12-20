package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService{

    private final SponsorRepository sponsorRepository;

    public SponsorServiceImpl(SponsorRepository aSponsorRepository) {this.sponsorRepository = aSponsorRepository; }
    public List<Sponsor> getAllSponsors() { return sponsorRepository.getAllSponsors();}
    public Sponsor getSponsor(Integer id) {return sponsorRepository.getSponsor(id);}
    public void saveSponsor(Sponsor sponsor) {sponsorRepository.saveSponsor(sponsor);}
}
