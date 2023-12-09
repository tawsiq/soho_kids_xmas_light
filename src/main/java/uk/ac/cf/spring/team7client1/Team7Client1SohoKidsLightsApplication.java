package uk.ac.cf.spring.team7client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Team7Client1SohoKidsLightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team7Client1SohoKidsLightsApplication.class, args);
		MetadataPopulator.populateDatabase();
	}

}
