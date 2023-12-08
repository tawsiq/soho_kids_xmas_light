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
        setImageItemMapper(); // Method defined below.
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

    public List<ImageClass> getImageItemList() {
        String sql = "SELECT * FROM Drawings";
        return jdbc.query(sql, imageItemMapper);
    }
    public ImageClass getImage(Long id){
        // ? replaced with id in return statement. Only one object is returned. Exception otherwise.
        String sql = "SELECT * FROM Drawings WHERE id = ?";
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
}
