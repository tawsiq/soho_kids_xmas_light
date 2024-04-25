package uk.ac.cf.client1.team7sohokidschristmaslights.SubmissionsUnitTesting;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.cf.client1.team7sohokidschristmaslights.MetadataPopulator;
import uk.ac.cf.client1.team7sohokidschristmaslights.submissions.*;
import uk.ac.cf.client1.team7sohokidschristmaslights.moderation.TextModerationService;
import org.springframework.jdbc.core.JdbcTemplate;

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
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(properties = {"spring.config.location=classpath:application_test.properties"})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Sql({"classpath:test_schema.sql"})
//@Transactional
//@Rollback


    //////// GET-IMAGE TESTS ////////
class ImageServiceImpTest {

    @Test
    void testGetNonExistingLight() {
        assert(true);
    }
}




