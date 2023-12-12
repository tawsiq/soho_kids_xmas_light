package uk.ac.cf.client1.team7sohokidschristmaslights.SubmissionsUnitTesting;

import uk.ac.cf.client1.team7sohokidschristmaslights.submissions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.ac.cf.client1.team7sohokidschristmaslights.moderation.TextModerationService;

class ImageServiceImpTest {

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private TextModerationService textModerationService;

    @InjectMocks
    private ImageService_imp imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getImageItemListTest() {
        // Test your getImageItemList method here
        // Mock imageRepository.getImageItemList and validate the returned result
    }

    @Test
    void getImageTest() {
        // Test your getImage method here
        // Mock imageRepository.getImage and validate the returned result for different scenarios
    }

    // Add more test methods for other public methods in ImageService_imp
}
