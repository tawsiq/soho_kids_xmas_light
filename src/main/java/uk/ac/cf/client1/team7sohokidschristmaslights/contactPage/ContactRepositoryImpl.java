package uk.ac.cf.client1.team7sohokidschristmaslights.contactPage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// Mark the class as a repository (data access component) using the @Repository annotation
@Repository
public class ContactRepositoryImpl implements ContactRepository {

    // Declare a private field to hold a reference to JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    // Declare a field for the RowMapper responsible for mapping database rows to Contact objects
    private RowMapper<Contact> contactRowMapper;

    // Constructor: Inject the JdbcTemplate dependency
    public ContactRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbcTemplate = aJdbc;
        // Initialize the contactRowMapper
        setContactRowMapper();
    }

    // Private method to set up the contactRowMapper
    private void setContactRowMapper() {
        // Define the contactRowMapper using a lambda expression
        contactRowMapper = (rs, i) -> new Contact(
                rs.getInt("id"),          // Map the "id" column from the result set to the Contact's id field
                rs.getString("name"),     // Map the "name" column to the Contact's name field
                rs.getString("email"),    // Map the "email" column to the Contact's email field
                rs.getString("subject"),  // Map the "subject" column to the Contact's subject field
        );
    }
}
