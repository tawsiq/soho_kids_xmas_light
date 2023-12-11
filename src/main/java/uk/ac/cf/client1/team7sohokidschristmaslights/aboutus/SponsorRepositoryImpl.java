package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SponsorRepositoryImpl implements SponsorRepository {

    private final JdbcTemplate jdbcTemplate;

    public SponsorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sponsor> getAllSponsors() {
        return jdbcTemplate.query("SELECT * FROM sponsors", new SponsorRowMapper());
    }

    @Override
    public void saveSponsor(Sponsor sponsor) {
        jdbcTemplate.update(
                "INSERT INTO sponsors (company_name, contact_person, email) VALUES (?, ?, ?)",
                sponsor.getCompanyName(),
                sponsor.getContactPerson(),
                sponsor.getEmail()
        );
    }
}

