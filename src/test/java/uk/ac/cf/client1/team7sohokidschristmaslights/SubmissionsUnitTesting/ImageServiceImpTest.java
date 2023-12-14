package uk.ac.cf.client1.team7sohokidschristmaslights.SubmissionsUnitTesting;

import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import uk.ac.cf.client1.team7sohokidschristmaslights.MetadataPopulator;
import uk.ac.cf.client1.team7sohokidschristmaslights.submissions.*;
import uk.ac.cf.client1.team7sohokidschristmaslights.moderation.TextModerationService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// NOTE: You may need to run the test twice initially to ensure tables are created for the first time.
//       You also need to configure a test database named team_7_soho_kids_database_test.
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// This is really cool.
// Spring uses the original database within the scope of transactions that can be rolled back. Annotate methods with @Transactional.
// So I can delete data during the test but won't need to worry about the production database losing anything. This effectively isolates the testing environment from the production environment.
@SpringBootTest(properties = {"spring.config.location=classpath:application_test.properties"})
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"classpath:test_schema.sql"})
class ImageServiceImpTest {

    @Autowired
    private final JdbcTemplate jdbc = new JdbcTemplate();
    @Autowired
    private TextModerationService textModerationService;
    @Autowired
    private ImageRepository imageRepository;

    private ImageService_imp imageService;

    @BeforeEach
    public void setUp() {
        imageService = new ImageService_imp(imageRepository, textModerationService);
    }

    @BeforeAll
    public static void before() {
        String jdbcURL = "jdbc:mariadb://localhost:3306/team7_soho_kids_database_test?user=root&password=comsc"; //TODO: Improve safety here by defining individual variables that scan application.properties for user & password, so that program is maintainable.

        try (Connection connection = DriverManager.getConnection(jdbcURL)) {
            MetadataPopulator.populateDatabase(jdbcURL);
            MetadataPopulator.initializeLikeCounts(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //////// GET-IMAGE TESTS ////////
    @Test
    void testGetExistingImage() {
        Long id = 1L; // Assuming this ID exists in the database (at least 1 will be stored according to previous testing above).
        ImageClass drawing = imageService.getImage(id, false);
        ImageClass light = imageService.getImage(id, true);

        System.out.println();
        System.out.println("Testing For Null image objects...");
        System.out.println();
        assertNotNull(drawing);
        assertNotNull(light);
        System.out.println("Passed.");

        System.out.println("Testing For Null Fields in Drawing...");
        assertThat(drawing)
                .extracting(
                        "id",
                        "fileName",
                        "filePath",
                        "mimeType",
                        "submissionYear",
                        "yearGroup",
                        "participantName"
                )
                .doesNotContainNull();
        System.out.println("Passed.");

        System.out.println("Testing For Null Fields in light...");
        assertThat(light)
                .extracting(
                        "id",
                        "fileName",
                        "filePath",
                        "mimeType",
                        "submissionYear",
                        "yearGroup",
                        "participantName"
                )
                .doesNotContainNull();
        System.out.println("Passed.");
    }

    @Test
    void getImageItemListGeneralTests() {
        // Access the database and store the return statement in actualResult.
        List<List<ImageClass>> actualResult = imageService.getImageItemList();

        System.out.println("-----------------------------------------------------------");
        System.out.println(actualResult.toString());
        System.out.println("-----------------------------------------------------------");
        System.out.flush();
        // Assertions(expectedResult, actualResult)
        assertEquals(2, actualResult.size()); // Check that only two lists are obtained, following the two tables in the database
        System.out.println("-----------------------------------------------------------");
        System.out.println("Passed Number of Lists check.");
        System.out.println("-----------------------------------------------------------");
        System.out.flush();

        assertTrue(isListOfDrawings(actualResult.get(0))); // Assert that only drawings are present in the first list
        assertTrue(isListOfLights(actualResult.get(1))); // Do the same for the second list containing only lights.
        System.out.println("-----------------------------------------------------------");
        System.out.println("Passed drawing & light containment check.");
        System.out.println("-----------------------------------------------------------");
        System.out.flush();

        assertTrue(infoMatchesFileNames(actualResult)); // Make sure every image in every list has info fields matching its name.
        System.out.println("-----------------------------------------------------------");
        System.out.println("Passed info - name matching check.");
        System.out.println("-----------------------------------------------------------");
        System.out.flush();
        // Checks if each list contains ImageClass instances (non-null and of the expected type), which would not be if the database storage was incomplete.
        assertTrue(actualResult.stream().allMatch(list -> list != null && list.stream().allMatch(Objects::nonNull)));
        System.out.println("-----------------------------------------------------------");
        System.out.println("Passed Not-Null check");
        System.out.println("-----------------------------------------------------------");
        System.out.flush();
        // Checks for any empty lists returned, which would indicate empty database tables. If this fails, the images weren't stored correctly.
        assertTrue(actualResult.stream().noneMatch(List::isEmpty));
        System.out.println("-----------------------------------------------------------");
        System.out.println("Passed Empty List return check whilst DB is populated.");
        System.out.println("-----------------------------------------------------------");
        System.out.flush();
    }
    private boolean isListOfDrawings(@NotNull List<ImageClass> imageList){
        return imageList.stream().allMatch(image -> image.getFileName().contains("drawing"));
    }
    private boolean isListOfLights(@NotNull List<ImageClass> imageList){
        return imageList.stream().allMatch(image -> image.getFileName().contains("light"));
    }
    private boolean infoMatchesFileNames(@NotNull List<List<ImageClass>> listOfImageLists){
        // Iterate through each list in the object that is passed in.
        for (List<ImageClass> imageList: listOfImageLists) {
            // Iterate through each image in the list of the current iteration.
            for (ImageClass image: imageList) {
                // Find the name of the file to compare it to the info + the string after the slash in mime_type field (should have just used png or jpeg)
                String fileName = image.getFileName();
                String mimeType = Objects.requireNonNull(getStringAfterSlash(image.getMimeType()));
                String interchangeableMimeType = "";

                // This caused a nasty mismatch bug that was failing tests when it shouldn't have. Again, should have stored image mimetype to be jpg as the standard & changed all JPEGs to JPGs
                if(mimeType.equals("jpg")){
                    interchangeableMimeType = "jpeg";

                } else if (mimeType.equals("jpeg")) {
                    interchangeableMimeType = "jpg";
                }
                // Assert that only when all info is contained within the name that true is returned. False otherwise.
                boolean nameContainsAllInfo =
                        fileName.contains(image.getParticipantName()) &&
                                fileName.contains(image.getYearGroup()) &&
                                fileName.contains(image.getSubmissionYear().toString()) &&
                                (fileName.contains(mimeType) || fileName.contains(interchangeableMimeType))
                        ;
                if(!nameContainsAllInfo){
                    // Immediately exit the loop when filename & Info do not match.
                    return false;
                }
            }
        }
        return true;
    }
    private String getStringAfterSlash(String slashedString){
        // Need to account for variable slashed string. Not all may be image/jpeg or image/png.
        int indexOfSlash = slashedString.indexOf('/');
        // Makes sure there's some sort of string after the slash index.
        if (indexOfSlash != -1 && indexOfSlash < slashedString.length() - 1) {
            return slashedString.substring(indexOfSlash + 1);
        } else {
            System.out.println("Invalid format or no substring after the slash.");
            return "";
        }
    }

    @Test
    @Transactional
    void getImageItemListTestEmptyDatabase() {
        // Need to delete table data that rely on foreign keys first, then the host of that foreign key.
        jdbc.execute("DELETE FROM ratings");
        jdbc.execute("DELETE FROM Lights");
        jdbc.execute("DELETE FROM Drawings");

        // Function to be tested is called to retrieve lists from empty the now database tables.
        List<List<ImageClass>> actualResult = imageService.getImageItemList();
        // Make sure the expected two empty lists are returned.
        assertTrue(actualResult.get(0).isEmpty()); // Check drawings list
        assertTrue(actualResult.get(1).isEmpty()); // Check lights list
    }

    @Test
    @Transactional
    void testGetNonExistingLight() {
        // Delete from a table that doesn't host foreign keys
        jdbc.execute("DELETE FROM Lights");
        Long id = 1L; // No longer exists in lights
        ImageClass nonExistingLight = imageService.getImage(id, true);
        assertNull(nonExistingLight);
        // If this passes, a type mismatch test is also passed because we know the function handles the retrieval of non-existent objects well.
        // Plus, nothing in the database table "lights" will exist without first having a corresponding id to the Drawings table, because they're created with foreign keys.
    }
    @Test
    void clearTables() {
        // This is a pseudo AfterAll method that executes once all tests are done.
        // I did this because it was an indescribable pain in my everything to get jdbc autowiring to work with static fields and methods.
        // So, I'm doing it this way. And it works for resetting the test database. If it ain't broke don't fix it.
        jdbc.execute("DELETE FROM team7_soho_kids_database_test.LikeCounts");
        jdbc.execute("DELETE FROM team7_soho_kids_database_test.ratings");
        jdbc.execute("DELETE FROM team7_soho_kids_database_test.Lights");
        jdbc.execute("DELETE FROM team7_soho_kids_database_test.Drawings");
    }

}
