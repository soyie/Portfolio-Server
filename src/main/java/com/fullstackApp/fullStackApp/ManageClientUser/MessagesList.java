package com.fullstackApp.fullStackApp.ManageClientUser;

import java.time.LocalDate;
import java.time.LocalTime;

public class MessagesList {
    String Sender;
    String SenderEmail;
    String Message;
    LocalDate Date;
    LocalTime Time;

    public MessagesList(String sender, String senderEmail, String message, LocalDate date, LocalTime time) {
        Sender = sender;
        SenderEmail = senderEmail;
        Message = message;
        Date = date;
        Time = time;
    }

    public MessagesList() {
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getSenderEmail() {
        return SenderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        SenderEmail = senderEmail;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "MessagesList{" +
                "Sender='" + Sender + '\'' +
                ", SenderEmail='" + SenderEmail + '\'' +
                ", Message='" + Message + '\'' +
                ", Date=" + Date +
                ", Time=" + Time +
                '}';
    }
}
