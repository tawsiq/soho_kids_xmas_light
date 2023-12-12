package uk.ac.cf.client1.team7sohokidschristmaslights.SubmissionsUnitTesting;

import uk.ac.cf.client1.team7sohokidschristmaslights.submissions.*;
import uk.ac.cf.client1.team7sohokidschristmaslights.moderation.TextModerationService;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ImageServiceImpTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

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
        // Mocking the behavior of imageItemMapper and jdbc.query for both tables
        RowMapper<ImageClass> imageItemMapper = mock(RowMapper.class);
        String sql_lights = """
                SELECT\s
                    l.drawing_id AS id,
                    l.filename,
                    l.filepath,
                    l.mime_type,
                    d.submission_year,
                    d.year_group,
                    d.name
                FROM\s
                    Lights l
                INNER JOIN\s
                    Drawings d ON l.drawing_id = d.id;""";

        String sql_drawings = "SELECT * FROM Drawings";

        when(imageRepository.getImageItemList()).thenReturn(
                List.of(
                        jdbcTemplate.query(sql_drawings, imageItemMapper),
                        jdbcTemplate.query(sql_lights, imageItemMapper)
                )
        );

        // Creating a list of ImageClass instances that you'd expect to be returned from the database. Using data that isn't necessarily there so that the tests are not DB dependent.
        List<ImageClass> mockedDrawings = List.of(
                new ImageClass(
                        500L,
                        "2023_year6_Sophia_drawing.png",
                        "D:\\Projects\\team-7-soho-kids-christmas-lights\\src\\main\\resources\\static\\submission_storage_directory\\2023\\year6\\2023_year6_Sophia_drawing.png",
                        "image/png",
                        2023,
                        "year6",
                        "Sophia")
                ,
                new ImageClass(
                        501L,
                        "2023_year6_Omar_drawing.png",
                        "D:\\Projects\\team-7-soho-kids-christmas-lights\\src\\main\\resources\\static\\submission_storage_directory\\2023\\year6\\2023_year6_Omar_drawing.png",
                        "image/png",
                        2023,
                        "year6",
                        "Omar")
        );


        // Light counterparts to drawings, if present.
        List<ImageClass> mockedLights = List.of(
                new ImageClass(
                        500L,
                        "2023_year6_Sophia_light.png",
                        "D:\\Projects\\team-7-soho-kids-christmas-lights\\src\\main\\resources\\static\\submission_storage_directory\\2023\\year6\\2023_year6_Sophia_light.png",
                        "image/jpeg",
                        2023,
                        "year6",
                        "Sophia")
                ,
                new ImageClass(
                        501L,
                        "2023_year6_Omar_light.png",
                        "D:\\Projects\\team-7-soho-kids-christmas-lights\\src\\main\\resources\\static\\submission_storage_directory\\2023\\year6\\2023_year6_Omar_light.png",
                        "image/jpeg",
                        2023,
                        "year6",
                        "Omar")
        );

        // This doesn't actually query the database, rather it just pretends to. "When this happens do this"
        when(jdbcTemplate.query(sql_drawings, imageItemMapper)).thenReturn(mockedDrawings);
        when(jdbcTemplate.query(sql_lights, imageItemMapper)).thenReturn(mockedLights);

        // Simulates the result expected, a list of lists of ImageClass instances.
        List<List<ImageClass>> result = imageService.getImageItemList();

        // Assertions
        assertEquals(2, result.size()); // Assuming it returns drawings and lights separately as two lists.
        assertEquals(mockedDrawings, result.get(0)); // Assert that drawings are the first list
        assertEquals(mockedLights, result.get(1)); // Assert that the lights are the second list. 
    }

    @Test
    void getImageTest() {
        // Test your getImage method here
        // Mock imageRepository.getImage and validate the returned result for different scenarios
    }

    // Add more test methods for other public methods in ImageService_imp
}
