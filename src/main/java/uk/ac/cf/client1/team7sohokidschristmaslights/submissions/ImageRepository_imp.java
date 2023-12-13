package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepository_imp implements ImageRepository{
    // allows opening of connection between DB data & java objects
    private final JdbcTemplate jdbc;
    private RowMapper<ImageClass> imageItemMapper;
    private RowMapper<RatingClass> ratingItemMapper;
    // Constructor
    public ImageRepository_imp(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setImageItemMapper();
        setRatingItemMapper();
    }

    // ---------- PRIVATE METHODS - INTERNAL USE ONLY ---------- //
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
    private void setRatingItemMapper(){
        ratingItemMapper = (rs, i) -> new RatingClass(
                rs.getLong("submission_id"),
                rs.getString("name"),
                rs.getString("comment"),
                rs.getString("date_time")
        );
    }

    private void insert(RatingClass rating){
        String insertRatingSQL =
                "INSERT INTO Ratings " +
                "(`submission_id`, `name`, `comment`, `date_time`)" +
                "VALUES (?,?,?,?)";

        jdbc.update(insertRatingSQL,
                rating.getSubmissionId(), // This is set when the form is handed in by thymeleaf in template.
                rating.getRaterName(),
                rating.getCommentText(),
                rating.getDateTime()
        );
    }

    // ---------- PUBLIC METHODS ---------- //
    // --- RETRIEVERS --- //
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
            WHERE d.id = ?"""; // Addressed potential ambiguity in the case of repeated entries in both tables from unit testing.

        } else {
            sql = "SELECT * FROM Drawings WHERE id = ?";
        }

        try{
            return jdbc.queryForObject(sql, imageItemMapper, id);

        } catch (IncorrectResultSizeDataAccessException e) {
            System.out.printf("%n ---------------- ERROR CAUGHT ----------------" +
                    "Either getImage() implementation in imageRepository_imp attempted to query for more than one object, or found no objects in Drawings table" +
                              "%n ----------------------------------------------"
                    );
            return null;
        }
    }

    public List<RatingClass> getRatingList(Long submission_id){

        String sql = "SELECT * FROM Ratings WHERE submission_id = ?";
        return jdbc.query(sql, ratingItemMapper, submission_id);
    }
//    public Integer countLikes(Long id){
//        String sql = "SELECT COUNT(*) FROM Ratings WHERE liked=1 AND submission_id=?";
//        return jdbc.queryForObject(sql, Integer.class, id);
//    }

    public Boolean lightCounterpartPresent(Long id){
        String sql = "SELECT EXISTS (SELECT 1 FROM Lights WHERE drawing_id = ?)";
        return jdbc.queryForObject(sql, Boolean.class, id);
    }
    // --- INSERTERS & UPDATERS --- //
    public void storeRating(RatingClass rating) {
        insert(rating);
        System.out.printf("%n--- Adding review to submission ---%n");
    }
    public void updateLikeCount(Long id, Integer increment) {
        String updateQuery = "UPDATE LikeCounts SET like_count = like_count + ? WHERE submission_id = ?";
        // The increment will be hardcoded to either -1 or 1 during this function's call, depending on the state change of the like button.
        try {
            int rowsAffected = jdbc.update(updateQuery, increment, id);
            if (rowsAffected > 0) {
                System.out.println("Updated like count for submission_id " + id + " by " + increment);
            } else {
                System.out.println("No rows were updated for submission_id " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
