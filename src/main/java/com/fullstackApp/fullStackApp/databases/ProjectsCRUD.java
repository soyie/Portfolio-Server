package com.fullstackApp.fullStackApp.databases;

import com.fullstackApp.fullStackApp.ManageClientUser.Project;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectsCRUD {

    private static final String DB_HOST = "sql10.freesqldatabase.com";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "sql10675453";
    private static final String DB_USER = "sql10675453";
    private static final String DB_PASSWORD = "IuyAKjadUu";

    private static final String PROJECTS_DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    // ... rest of the code

    // Use this method to obtain a connection to the MySQL database
    public static Connection getConnection() {
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            return DriverManager.getConnection(PROJECTS_DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public static int insertProject(String name, String type, String gitHub, String testLink, String description, String projectId, byte[] image, byte[] image1, byte[] image2, byte[] image3) {
        try (Connection connection = DriverManager.getConnection(PROJECTS_DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO projects (image, name, type, gitHub, testLink, description, projectId, image1, image2, image3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setBinaryStream(1, new ByteArrayInputStream(image), image.length);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, gitHub);
            preparedStatement.setString(5, testLink);
            preparedStatement.setString(6, description);
            preparedStatement.setString(7, projectId);
            preparedStatement.setBinaryStream(8, new ByteArrayInputStream(image1), image1.length);
            preparedStatement.setBinaryStream(9, new ByteArrayInputStream(image2), image2.length);
            preparedStatement.setBinaryStream(10, new ByteArrayInputStream(image3), image3.length);
            System.out.println("added successfully");
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating project failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating project failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public Project getProject(Long projectId) {
        try (Connection connection = DriverManager.getConnection(PROJECTS_DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM projects WHERE id = ?")) {

            preparedStatement.setLong(1, projectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProject(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(PROJECTS_DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM projects")) {

            while (resultSet.next()) {
                projects.add(mapResultSetToProject(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public void updateProject(int projectId, String name, String type, String gitHub, String testLink, String description) {
        try (Connection connection = DriverManager.getConnection(PROJECTS_DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE projects SET name=?, type=?, gitHub=?, testLink=?, description=? WHERE id=?")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, gitHub);
            preparedStatement.setString(4, testLink);
            preparedStatement.setString(5, description);
            preparedStatement.setInt(6, projectId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating project failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject(Long projectId) {
        try (Connection connection = DriverManager.getConnection(PROJECTS_DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM projects WHERE id=?")) {

            preparedStatement.setInt(1, Math.toIntExact(projectId));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting project failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Project mapResultSetToProject(ResultSet resultSet) throws SQLException {
        return new Project(
                resultSet.getInt("id"),
                resultSet.getBytes("image"),
                resultSet.getString("name"),
                resultSet.getString("type"),
                resultSet.getString("gitHub"),
                resultSet.getString("testLink"),
                resultSet.getString("description"),
                resultSet.getString("projectId"),
                resultSet.getBytes("image1"),
                resultSet.getBytes("image2"),
                resultSet.getBytes("image3")

                // Add mapping for other columns if needed
        );
    }
}

