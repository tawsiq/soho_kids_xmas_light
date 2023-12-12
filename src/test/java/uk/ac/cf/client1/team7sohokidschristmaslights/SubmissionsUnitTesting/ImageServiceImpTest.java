package uk.ac.cf.client1.team7sohokidschristmaslights.SubmissionsUnitTesting;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.client1.team7sohokidschristmaslights.submissions.*;
import uk.ac.cf.client1.team7sohokidschristmaslights.moderation.TextModerationService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TO GET THESE TESTS TO WORK: Make sure the database is populated & the tables are not dropped before running these tests. populateDatabase() doesn't work for some reason.
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@SpringBootTest
class ImageServiceImpTest {

    @Autowired
    private final JdbcTemplate jdbc = new JdbcTemplate();
    private ImageService_imp imageService;

    @BeforeEach
    public void setUp() {
        TextModerationService atextModerationService = new TextModerationService();
        ImageRepository anImageRepository = new ImageRepository_imp(jdbc);
        imageService = new ImageService_imp(anImageRepository, atextModerationService);
    }

    @Test
    void getImageItemListTest() {
        // Access the database and store the return statement in actualResult.
        List<List<ImageClass>> actualResult = imageService.getImageItemList();

        System.out.println("-----------------------------------------------------------");
        System.out.println(actualResult.toString());
        System.out.println("-----------------------------------------------------------");
        System.out.flush();
        // Assertions(expectedResult, actualResult)
        assertEquals(2, actualResult.size()); // Check that only two lists are obtained, following the two tables in the database

        assertTrue(isListOfDrawings(actualResult.get(0))); // Assert that only drawings are present in the first list
        assertTrue(isListOfLights(actualResult.get(1))); // Do the same for the second list containing only lights.
//
//        // Checks if each list contains ImageClass instances (non-null and of the expected type), which would not be if the database storage was incomplete.
        assertTrue(actualResult.stream().allMatch(list -> list != null && list.stream().allMatch(Objects::nonNull)));
//        // Checks for any empty lists returned, which would indicate empty database tables. If this fails, the images weren't stored correctly.
        assertTrue(actualResult.stream().noneMatch(List::isEmpty));
    }

    private boolean isListOfDrawings(@NotNull List<ImageClass> imageList){
        return imageList.stream().allMatch(image -> image.getFileName().contains("drawing"));
    }
    private boolean isListOfLights(@NotNull List<ImageClass> imageList){
        return imageList.stream().allMatch(image -> image.getFileName().contains("light"));
    }

    @Test
    void getImageTest() {
        assertTrue(true);
    }

}
