package uk.ac.cf.client1.team7sohokidschristmaslights.AboutUsTesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.cf.client1.team7sohokidschristmaslights.MetadataPopulator;
import uk.ac.cf.client1.team7sohokidschristmaslights.aboutus.Sponsor;
import uk.ac.cf.client1.team7sohokidschristmaslights.aboutus.SponsorRepository;
import uk.ac.cf.client1.team7sohokidschristmaslights.moderation.TextModerationService;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = {"spring.config.location=classpath:application_test.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"classpath:test_schema.sql"})
@Transactional
@Rollback
public class SponsorRepositoryImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SponsorRepository sponsorRepository;

    @BeforeEach
    public void setUp() {
        // Initialize and populate the database with test data
        MetadataPopulator.populateDatabase("jdbc:mariadb://localhost:3306/team7_soho_kids_database_test?user=root&password=comsc");
    }

   // @Test
   // public void testGetAllSponsors() {
        // Assuming there are 3 sponsors in the test data
    //    List<Sponsor> sponsors = sponsorRepository.getAllSponsors();
    //    assertEquals(3, sponsors.size());
   // }

    //@Test
    //public void testGetSponsor() {
    //    Integer sponsorId = 1; // Assuming this ID exists in test data
    //    Sponsor sponsor = sponsorRepository.getSponsor(sponsorId);
    //    assertNotNull(sponsor);
    //    assertEquals(sponsorId, sponsor.getSponsorId());
   // }

   // @Test
   // public void testSaveSponsor() {
        // Assuming there is a sponsor object to save
    //    Sponsor newSponsor = new Sponsor(null, "New Company", "New Contact", "new@example.com");
        // Save the sponsor
    //    sponsorRepository.saveSponsor(newSponsor);

        // Retrieve the saved sponsor from the database
    //    List<Sponsor> sponsors = jdbcTemplate.query("SELECT * FROM SponsorInfo WHERE company_name = 'New Company'",
    //            (rs, rowNum) -> new Sponsor(
    //                    rs.getInt("sponsor_id"),
    //                    rs.getString("company_name"),
    //                    rs.getString("contact_person"),
    //                    rs.getString("email")
     //           ));

        // Assuming only one sponsor with 'New Company' exists in the test data
    //    assertEquals(1, sponsors.size());

    //    Sponsor savedSponsor = sponsors.get(0);
    //    assertNotNull(savedSponsor.getSponsorId());
    //    assertEquals("New Company", savedSponsor.getCompanyName());
     //   assertEquals("New Contact", savedSponsor.getContactPerson());
    //    assertEquals("new@example.com", savedSponsor.getEmail());
   // }
}

