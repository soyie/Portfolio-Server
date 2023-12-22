package com.fullstackApp.fullStackApp.databases;

import com.fullstackApp.fullStackApp.ManageClientUser.ByteToMultipartFile;
import com.fullstackApp.fullStackApp.ManageClientUser.ProjectData;
import com.fullstackApp.fullStackApp.ManageClientUser.MessagesList;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class BudgetBossDataBase {
    public String sql;

    public static final String DATABASE_URL = "jdbc:sqlite:myprojects.db";
    public List<byte[]> imagesList = new ArrayList<byte[]>();

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
        String sql = "CREATE TABLE IF NOT EXISTS messages ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "responseMail,"
                + "responseName,"
                + "message"
                + "Date"
                + "Time"  // Add a comma here
                + ");";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




//    public static void insertImage(ProjectData imageModel, List<byte[]> project) {
//        String sql = "INSERT INTO images (image, name, type, gitHub, testLink, views, description, projectId, image1, image2, image3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//
//        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setBytes(1, imageModel.getImage());
//            pstmt.setString(2, imageModel.getName());
//            pstmt.setString(3, imageModel.getType());
//            pstmt.setString(4, imageModel.getGitHub());
//            pstmt.setString(5, imageModel.getTestLink());
//            pstmt.setInt(6, imageModel.getViews());
//            pstmt.setString(7, imageModel.getDescription());
//            pstmt.setString(8, String.valueOf(imageModel.getUuid()));
//            pstmt.setBytes(9, project.get(0));
//            pstmt.setBytes(10, project.get(1));
//            pstmt.setBytes(11, project.get(2));
//
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

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
                    imageModel.setTestLink(rs.getString("testLink"));
                    imageModel.setViews(rs.getInt("views"));
                    imageModel.setDescription(rs.getString("description"));
                    imageModel.setUuid(UUID.fromString(rs.getString("projectId")));
                    imagesList.add(rs.getBytes("image1"));
                    imagesList.add(rs.getBytes("image2"));
                    imagesList.add(rs.getBytes("image3"));
                    imageModel.setImageList(imagesList);

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
        List<ProjectData> images = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ProjectData imageModel = new ProjectData();
                imageModel.setId(Integer.parseInt(rs.getString("id")));
                
//                MultipartFile multipartFile = new MockMultipartFile("file", "mainPicture.jpg", "image/jpeg", rs.getBytes("image"));
                imageModel.setImage(rs.getBytes("image"));
                imageModel.setName(rs.getString("name"));
                imageModel.setType(rs.getString("type"));
                imageModel.setGitHub(rs.getString("gitHub"));
                imageModel.setTestLink(rs.getString("testLink"));
                imageModel.setViews(rs.getInt("views"));
                imageModel.setDescription(rs.getString("description"));
                imageModel.setUuid(UUID.fromString(rs.getString("projectId")));
                imagesList.add(rs.getBytes("image"));
                imagesList.add(rs.getBytes("image1"));
                imagesList.add(rs.getBytes("image2"));
                imageModel.setImageList(imagesList);

                images.add(imageModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return images;
    }



//    public static void updateImage(int id, ProjectData updatedImageModel) {
//        String sql = "UPDATE images SET image=?, name=?, type=?, gitHub=?, testLink=?, views=?, description=? WHERE id=?;";
//
//        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setBytes(1, updatedImageModel.getImage());
//            pstmt.setString(2, updatedImageModel.getName());
//            pstmt.setString(3, updatedImageModel.getType());
//            pstmt.setString(4, updatedImageModel.getGitHub());
//            pstmt.setString(5, updatedImageModel.getTestLink());
//            pstmt.setInt(6, updatedImageModel.getViews());
//            pstmt.setString(7, updatedImageModel.getDescription());
//            pstmt.setInt(8, id);
//
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

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
                if (rs.next()) {
                    MessagesList imageModel = new MessagesList();

                    imageModel.setSenderEmail(rs.getString("responseMail"));
                    imageModel.setSender(rs.getString("responseName"));
                    imageModel.setMessage(rs.getString("message"));
                    imageModel.setDate(LocalDate.parse(rs.getString("Date")));
                    imageModel.setTime(LocalTime.parse(rs.getString("Time")));
                    return imageModel;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void insertMessage(MessagesList imageModel) {
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

