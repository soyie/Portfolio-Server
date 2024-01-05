package com.fullstackApp.fullStackApp.ManageClientUser;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {
    public final int id;
    public final String responseMail;
    public final String responseName;
    public final String message;
    public final LocalDate date;
    public final LocalTime time;

    public Message(int id, String responseMail, String responseName, String message, LocalDate date, LocalTime time) {
        this.id = id;
        this.responseMail = responseMail;
        this.responseName = responseName;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    // Getter methods for date and time if needed

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", responseMail='" + responseMail + '\'' +
                ", responseName='" + responseName + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
