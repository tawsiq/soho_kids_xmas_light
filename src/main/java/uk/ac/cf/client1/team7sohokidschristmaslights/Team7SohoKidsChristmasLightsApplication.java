package uk.ac.cf.client1.team7sohokidschristmaslights;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.cf.client1.team7sohokidschristmaslights.submissions.MetadataPopulator;

@SpringBootApplication
public class Team7SohoKidsChristmasLightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team7SohoKidsChristmasLightsApplication.class, args);

		MetadataPopulator.populateDatabase();

	}

}
