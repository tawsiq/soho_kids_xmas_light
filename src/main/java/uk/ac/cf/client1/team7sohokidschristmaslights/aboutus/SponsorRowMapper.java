package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SponsorRowMapper implements RowMapper<Sponsor> {

    @Override
    public Sponsor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Sponsor sponsor = new Sponsor();
        sponsor.setCompanyName(resultSet.getString("company_name"));
        sponsor.setContactPerson(resultSet.getString("contact_person"));
        sponsor.setEmail(resultSet.getString("email"));
        return sponsor;
    }
}

