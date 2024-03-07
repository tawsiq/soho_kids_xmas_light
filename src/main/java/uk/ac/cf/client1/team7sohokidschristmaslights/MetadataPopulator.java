package uk.ac.cf.client1.team7sohokidschristmaslights;


import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Paths;
// Pair programmed with ChatGPT.
public class MetadataPopulator {

    private static final Map<String, Long> drawingNameToIdMap = new HashMap<>();

    public static void populateDatabase(String jdbcURL) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("MetadataPopulator located here: " + currentDirectory);

        String directoryPath = Paths.get(currentDirectory, "src", "main", "resources", "static", "submission_storage_directory").toString();

        try (Connection connection = DriverManager.getConnection(jdbcURL)) {
            File mainDirectory = new File(directoryPath);
            File[] years = mainDirectory.listFiles();

            if (years != null) {
                for (File year : years) {
                    if (year.isDirectory()) {
                        File[] yearGroups = year.listFiles();

                        if (yearGroups != null) {
                            for (File yearGroup : yearGroups) {
                                if (yearGroup.isDirectory()) {
                                    File[] drawingsAndLights = yearGroup.listFiles();

                                    if (drawingsAndLights != null) {
                                        for (File file : drawingsAndLights) {

                                            if (file.isFile() && (file.getName().endsWith(".png") || file.getName().endsWith(".jpeg") || file.getName().endsWith(".jpg"))) {

                                                if (file.getName().contains("drawing")) {
                                                    long drawingId = insertDrawingIntoDatabase(connection, file.getName(), file.getAbsolutePath(), year.getName(), yearGroup.getName());
                                                    drawingNameToIdMap.put(getDrawingName(file.getName()), drawingId); // Store drawing names and their IDs

                                                } else if (file.getName().contains("light")) {
                                                    System.out.println("Processing light image: " + file.getName());
                                                    String drawingFileName = getDrawingName(file.getName());
                                                    Long correspondingDrawingId = drawingNameToIdMap.get(drawingFileName); // Get corresponding drawing ID

                                                    if (correspondingDrawingId != null) {
                                                        System.out.println("Light image filename: " + file.getName() + ", Corresponding Drawing ID: " + correspondingDrawingId);
                                                        insertLightIntoDatabase(connection, file.getName(), file.getAbsolutePath(), correspondingDrawingId);

                                                    } else {
                                                        System.out.println("Corresponding Drawing ID not found for light image: " + file.getName());
                                                        System.out.println("Drawing file name: " + drawingFileName);
                                                        System.out.println("Keys in drawingNameToIdMap: " + drawingNameToIdMap.keySet());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("---- POPULATOR FAILED: SOME DATABASE TABLES FOUND. PRINTING ERROR TO STACK TRACE \n-----");
            e.printStackTrace();
        }
    }

    private static long insertDrawingIntoDatabase(Connection connection, String fileName, String filePath, String submissionYear, String yearGroup) throws Exception {
        String sql = "INSERT INTO Drawings (filename, filepath, mime_type, submission_year, year_group, `name`) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, fileName);
            preparedStatement.setString(2, filePath);
            preparedStatement.setString(3, getMimeType(fileName));
            preparedStatement.setString(4, submissionYear);
            preparedStatement.setString(5, yearGroup);
            preparedStatement.setString(6, getNameFromFileName(fileName)); // Extract name from filename
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1); // Return the generated ID
            } else {
                throw new SQLException("Failed to get the generated ID.");
            }
        }
    }


    private static void insertLightIntoDatabase(Connection connection, String fileName, String filePath, long correspondingDrawingId) throws Exception {
        String sql = "INSERT INTO Lights (drawing_id, filename, filepath, mime_type) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, correspondingDrawingId); // Use the corresponding drawing ID
            preparedStatement.setString(2, fileName);
            preparedStatement.setString(3, filePath);
            preparedStatement.setString(4, getMimeType(fileName));
            System.out.println();
            System.out.println("Executing insertLightIntoDatabase query for file: " + fileName + " and drawing ID: " + correspondingDrawingId);
            preparedStatement.executeUpdate();
            System.out.println("Inserted light image: " + fileName + " for drawing ID: " + correspondingDrawingId);
            System.out.println();
            System.out.println();
        }
    }

    public static void initializeLikeCounts(Connection connection) {
        try {
            String countRowsQuery = "SELECT COUNT(*) AS rowCount FROM LikeCounts";
            String insertIntoLikeCounts = "INSERT INTO LikeCounts (submission_id, like_count) VALUES (?, 0)";

            PreparedStatement countStatement = connection.prepareStatement(countRowsQuery);
            ResultSet rowCountResult = countStatement.executeQuery();
            rowCountResult.next();
            int rowCount = rowCountResult.getInt("rowCount");

            if (rowCount == 0) {
                PreparedStatement selectStatement = connection.prepareStatement("SELECT id FROM Drawings");
                ResultSet drawingsIds = selectStatement.executeQuery();

                PreparedStatement insertStatement = connection.prepareStatement(insertIntoLikeCounts);

                while (drawingsIds.next()) {
                    long drawingId = drawingsIds.getLong("id");
                    insertStatement.setLong(1, drawingId);
                    insertStatement.addBatch();
                }

                insertStatement.executeBatch();
                System.out.println("Inserted Drawings IDs into LikeCounts with initial like_count of 0.");
            } else {
                System.out.println("LikeCounts table already contains data. Skipping initialization.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static String getMimeType(String fileName) {
        // Could have used the .endsWith method here, but I thought for testing purposes I should use a more flexible approach and grab whatever it is that's after the slash.
        if (fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else {
            return "unknown";
        }
    }

    private static String getNameFromFileName(String fileName) {
        String[] parts = fileName.split("_");
        return parts[2];
    }

    private static String getDrawingName(String fileName) {
        return fileName.replace("_light", "_drawing").replace("_drawing", "").replace(".png", "").replace(".jpg", "").replace(".jpeg", "");
    }
}
