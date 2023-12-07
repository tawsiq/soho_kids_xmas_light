package uk.ac.cf.client1.team7sohokidschristmaslights.submissions;

import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MetadataPopulator {

    public static void populateDatabase() {
        String directoryPath = "path/to/your/directory";
        String jdbcURL = "jdbc:your_database_connection_details";

        try (Connection connection = DriverManager.getConnection(jdbcURL)) {
            File directory = new File(directoryPath);
            File[] submissionYears = directory.listFiles();

            if (submissionYears != null) {
                for (File submissionYear : submissionYears) {
                    File[] yearGroups = submissionYear.listFiles();

                    if (yearGroups != null) {
                        for (File yearGroup : yearGroups) {
                            File[] drawingsAndLights = yearGroup.listFiles();

                            if (drawingsAndLights != null) {
                                for (File file : drawingsAndLights) {
                                    if (file.getName().endsWith(".png") || file.getName().endsWith(".jpeg")) {
                                        if (file.getName().contains("drawing")) {
                                            insertDrawingIntoDatabase(connection, file.getName(), file.getAbsolutePath(), submissionYear.getName(), yearGroup.getName());
                                        } else if (file.getName().contains("light")) {
                                            insertLightIntoDatabase(connection, file.getName());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertDrawingIntoDatabase(Connection connection, String fileName, String filePath, String submissionYear, String yearGroup) throws Exception {
        String sql = "INSERT INTO Drawings (filename, filepath, mime_type, submission_year, year_group, `name`) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fileName);
            preparedStatement.setString(2, filePath);
            preparedStatement.setString(3, getMimeType(fileName));
            preparedStatement.setString(4, submissionYear);
            preparedStatement.setString(5, yearGroup);
            preparedStatement.setString(6, getNameFromFileName(fileName)); // Extract name from filename
            preparedStatement.executeUpdate();
        }
    }

    private static void insertLightIntoDatabase(Connection connection, String fileName) throws Exception {
        String sql = "INSERT INTO Lights (filename, mime_type) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fileName);
            preparedStatement.setString(2, getMimeType(fileName));
            preparedStatement.executeUpdate();
        }
    }

    private static String getMimeType(String fileName) {
        if (fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else {
            return "unknown";
        }
    }

    private static String getNameFromFileName(String fileName) {
        // Parse the filename to extract the name based on the convention
        String[] parts = fileName.split("_");
        return parts[2];
    }
}

