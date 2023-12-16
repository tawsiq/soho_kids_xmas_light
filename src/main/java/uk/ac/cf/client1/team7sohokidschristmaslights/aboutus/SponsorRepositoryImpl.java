package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class SponsorRepositoryImpl implements SponsorRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Sponsor> sponsorRowMapper;
    // Constructor
    public SponsorRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbcTemplate = aJdbc;
        setSponsorRowMapper();
    }

    private void setSponsorRowMapper(){
        sponsorRowMapper = (rs, i) -> new Sponsor(
                rs.getInt("sponsor_id"),
                rs.getString("company_name"),
                rs.getString("contact_person"),
                rs.getString("email")
        );
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

