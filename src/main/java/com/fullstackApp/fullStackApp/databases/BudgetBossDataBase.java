package com.fullstackApp.fullStackApp.databases;

import com.fullstackApp.fullStackApp.ManageClientUser.ProjectData;
import com.fullstackApp.fullStackApp.ManageClientUser.MessagesList;

import java.io.InputStream;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class BudgetBossDataBase {

    public static final String DATABASE_URL = "jdbc:sqlite::resource:myprojects.db";

    public List<byte[]> imagesList = new ArrayList<byte[]>();
    List<ProjectData> images = new ArrayList<>();
    List<MessagesList> messages = new ArrayList<>();
    InputStream inputStream = BudgetBossDataBase.class.getClassLoader().getResourceAsStream("myprojects.db");
    Path targetPath = Path.of("myprojects.db");

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS images ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "image BLOB,"
                + "name TEXT,"
                + "type TEXT,"
                + "gitHub TEXT,"
                + "testLink TEXT,"
                + "views INTEGER,"
                + "description TEXT,"
                + "projectId TEXT,"
                + "image1 BLOB,"
                + "image2 BLOB,"
                + "image3 BLOB"
                + ");";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDataTable() {
        String sql = "CREATE TABLE IF NOT EXISTS messages("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "responseMail,"
                + "responseName,"
                + "message,"
                + "Date,"
                + "Time"
                + ");";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void insertImage(ProjectData imageModel) {
        String sql = "INSERT INTO images (image, name, type, gitHub, testLink, views, description, projectId, image1, image2, image3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBytes(1, imageModel.getImage());
            pstmt.setString(2, imageModel.getName());
            pstmt.setString(3, imageModel.getType());
            pstmt.setString(4, imageModel.getGitHub());
            pstmt.setString(5, imageModel.getLink());
            pstmt.setInt(6, imageModel.getViews());
            pstmt.setString(7, imageModel.getDescription());
            pstmt.setString(8, String.valueOf(imageModel.getProjectUID()));
            pstmt.setBytes(9, imageModel.getScreen1());
            pstmt.setBytes(10, imageModel.getScreen2());
            pstmt.setBytes(11, imageModel.getScreen3());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProjectData getImageByName(Long name) {
        String sql = "SELECT * FROM images WHERE id = ?;";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(name));

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ProjectData imageModel = new ProjectData();

                    imageModel.setImage(rs.getBytes("image"));
                    imageModel.setName(rs.getString("name"));
                    imageModel.setType(rs.getString("type"));
                    imageModel.setGitHub(rs.getString("gitHub"));
                    imageModel.setLink(rs.getString("testLink"));
                    imageModel.setViews(rs.getInt("views"));
                    imageModel.setDescription(rs.getString("description"));
                    imageModel.setProjectUID(UUID.fromString(rs.getString("projectId")));
                    imageModel.setScreen1(rs.getBytes("image1"));
                    imageModel.setScreen2(rs.getBytes("image2"));
                    imageModel.setScreen3(rs.getBytes("image3"));

                    return imageModel;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




    public List<ProjectData> getAllImages() {
        String sql = "SELECT * FROM images;";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            images.clear();

            while (rs.next()) {
                imagesList.clear();
                ProjectData imageModel = new ProjectData();
                imageModel.setId(Integer.parseInt(rs.getString("id")));
                imageModel.setImage(rs.getBytes("image"));
                imageModel.setName(rs.getString("name"));
                imageModel.setType(rs.getString("type"));
                imageModel.setGitHub(rs.getString("gitHub"));
                imageModel.setLink(rs.getString("testLink"));
                imageModel.setViews(rs.getInt("views"));
                imageModel.setDescription(rs.getString("description"));
                imageModel.setProjectUID(UUID.fromString(rs.getString("projectId")));
                imageModel.setScreen1(rs.getBytes("image1"));
                imageModel.setScreen2(rs.getBytes("image2"));
                imageModel.setScreen3(rs.getBytes("image3"));
                images.add(imageModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return images;
    }



    public static void updateImage(int id, ProjectData updatedImageModel) {
        String sql = "UPDATE images SET image=?, name=?, type=?, gitHub=?, testLink=?, views=?, description=? WHERE id=?;";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBytes(1, updatedImageModel.getImage());
            pstmt.setString(2, updatedImageModel.getName());
            pstmt.setString(3, updatedImageModel.getType());
            pstmt.setString(4, updatedImageModel.getGitHub());
            pstmt.setString(5, updatedImageModel.getLink());
            pstmt.setInt(6, updatedImageModel.getViews());
            pstmt.setString(7, updatedImageModel.getDescription());
            pstmt.setInt(8, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteImage(int id) {
        String sql = "DELETE FROM images WHERE id=?;";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object getAllMessages() {
        String sql = "SELECT * FROM messages;";


        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MessagesList imageModel = new MessagesList();

                    imageModel.setSenderEmail(rs.getString("responseMail"));
                    imageModel.setSender(rs.getString("responseName"));
                    imageModel.setMessage(rs.getString("message"));
                    imageModel.setDate(LocalDate.parse(rs.getString("Date").split(",")[0]));
                    imageModel.setTime(LocalTime.parse(rs.getString("Time")));
                    messages.add(imageModel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public void insertMessage(MessagesList imageModel) {
        String sql = "INSERT INTO messages(responseMail, responseName, message, Date, Time) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, imageModel.getSenderEmail());
            pstmt.setString(2, imageModel.getSender());
            pstmt.setString(3, imageModel.getMessage());
            pstmt.setString(4, String.valueOf(imageModel.getDate()));
            pstmt.setString(5, String.valueOf(imageModel.getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

