package uk.ac.cf.client1.team7sohokidschristmaslights;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class Team7SohoKidsChristmasLightsApplication {

	public static void main(String[] args) {

		SpringApplication.run(Team7SohoKidsChristmasLightsApplication.class, args);

		String jdbcURL = "jdbc:mariadb://localhost:3306/team7_soho_kids_database?user=root&password=comsc"; //TODO: Improve safety here by defining individual variables that scan application.properties for user & password, so that program is maintainable.

		try (Connection connection = DriverManager.getConnection(jdbcURL)) {
			MetadataPopulator.populateDatabase(jdbcURL);
			MetadataPopulator.initializeLikeCounts(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
