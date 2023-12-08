package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepository_imp implements ImageRepository{
    // allows opening of connection between DB data & java objects
    private final JdbcTemplate jdbc; //TODO: Test "final" here.
    private RowMapper<ImageClass> imageItemMapper;
    // Constructor
    public ImageRepository_imp(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setImageItemMapper();
    }

    private void setImageItemMapper() {
        imageItemMapper = (rs, i) -> new ImageClass(
                rs.getLong("id"),
                rs.getString("filename"),
                rs.getString("filepath"),
                rs.getString("mime_type"),
                rs.getInt("submission_year"),
                rs.getString("year_group"),
                rs.getString("name")
        );
    }

    // Returns a list of lists. The first list, at element 0 of the return, will contain drawings. The second at element 1 will contain the lights.
    // Since these only contain the metadata, this shouldn't impact performance so much. The byte data is read separately when needed.
    public List<List<ImageClass>> getImageItemList() {
        // TODO: remove boolean parameter & if statement. Return a list of lists (from bot tables) and select which you'd like in the controller call.

        String sql_drawings = "SELECT * FROM Drawings";
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

        return List.of(
                jdbc.query(sql_drawings, imageItemMapper),
                jdbc.query(sql_lights, imageItemMapper)
        );
    }
    public ImageClass getImage(Long id, Boolean light){
        String sql;

        if (light){
            // ? replaced with id in return statement. Only one object is returned. Exception otherwise.
            sql = """
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
                Drawings d ON l.drawing_id = d.id\s
            WHERE id = ?""";

        } else {
            sql = "SELECT * FROM Drawings WHERE id = ?";
        }

        try{
            return jdbc.queryForObject(sql, imageItemMapper, id);

        } catch (IncorrectResultSizeDataAccessException ignored) {
            System.out.printf("%n ---------------- ERROR CAUGHT ----------------" +
                    "Either getImage() implementation in imageRepository_imp attempted to query for more than one object, or found no objects in Drawings table" +
                              "%n ----------------------------------------------"
                    );
        }
        return null;
    }
    // Come back to https://www.notion.so/unequaled-moustache-536/Servers-2-73bc55350f0d4274b34ff8e9b67f8c50?pvs=4#1f1efe08fdab40f695a379ee5f30e56f if you want to start adding submissions through the app.
    public Boolean lightCounterpartPresent(Long id){
        String sql = "SELECT EXISTS (SELECT 1 FROM Lights WHERE drawing_id = ?)";
        return jdbc.queryForObject(sql, Boolean.class, id);
    }
}
