package uk.ac.cf.client1.team7sohokidschristmaslights.SubmissionsUnitTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import uk.ac.cf.client1.team7sohokidschristmaslights.submissions.*;

import static org.mockito.ArgumentMatchers.any;

class ImageRepositoryImpTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ImageRepository_imp imageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getImageItemListTest() {
        // Test your getImageItemList method here
        // Mock jdbcTemplate.query and validate the returned result
    }

    @Test
    void getImageTest() {
        // Test your getImage method here
        // Mock jdbcTemplate.queryForObject and validate the returned result for different scenarios
    }

    // Add more test methods for other public methods in ImageRepository_imp
}
