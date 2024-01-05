package com.fullstackApp.fullStackApp.databases;

import com.fullstackApp.fullStackApp.ManageClientUser.Message;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MessagesCRUD {

    private static final String MESSAGES_DB_URL = "jdbc:h2:~/messagesdb;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

//    public static void main(String[] args) {
//        createTable();
//        int messageId = insertMessage("response@example.com", "John Doe", "Hello, this is a message.", "2022-01-02", "12:34:56");
//        Message message = getMessage(messageId);
//        System.out.println("Retrieved Message: " + message);
//        updateMessage(messageId, "updated_response@example.com", "Updated John Doe", "Updated message.", "2022-01-03", "23:45:12");
//        List<Message> messages = getAllMessages();
//        System.out.println("All Messages: " + messages);
//        deleteMessage(messageId);
//    }

    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(MESSAGES_DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS messages (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "responseMail TEXT," +
                    "responseName TEXT," +
                    "message TEXT," +
                    "Date DATE," +
                    "Time TIME)";
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'messages' created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int insertMessage(String responseMail, String responseName, String message, LocalDate date, LocalTime time) {
        try (Connection connection = DriverManager.getConnection(MESSAGES_DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO messages (responseMail, responseName, message, Date, Time) VALUES (?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, responseMail);
            preparedStatement.setString(2, responseName);
            preparedStatement.setString(3, message);
            preparedStatement.setString(4, String.valueOf(date));
            preparedStatement.setString(5, String.valueOf(time));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating message failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating message failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Message getMessage(int messageId) {
        try (Connection connection = DriverManager.getConnection(MESSAGES_DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM messages WHERE id = ?")) {

            preparedStatement.setInt(1, messageId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMessage(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(MESSAGES_DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM messages")) {

            while (resultSet.next()) {
                messages.add(mapResultSetToMessage(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public static void updateMessage(int messageId, String responseMail, String responseName, String updatedMessage, String date, String time) {
        try (Connection connection = DriverManager.getConnection(MESSAGES_DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE messages SET responseMail=?, responseName=?, message=?, Date=?, Time=? WHERE id=?")) {

            preparedStatement.setString(1, responseMail);
            preparedStatement.setString(2, responseName);
            preparedStatement.setString(3, updatedMessage);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, time);
            preparedStatement.setInt(6, messageId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating message failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMessage(int messageId) {
        try (Connection connection = DriverManager.getConnection(MESSAGES_DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM messages WHERE id=?")) {

            preparedStatement.setInt(1, messageId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting message failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Message mapResultSetToMessage(ResultSet resultSet) throws SQLException {
        return new Message(
                resultSet.getInt("id"),
                resultSet.getString("responseMail"),
                resultSet.getString("responseName"),
                resultSet.getString("message"),
                LocalDate.parse(resultSet.getString("Date")),
                LocalTime.parse(resultSet.getString("Time"))
        );
    }
}



