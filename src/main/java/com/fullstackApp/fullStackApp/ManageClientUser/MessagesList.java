package com.fullstackApp.fullStackApp.ManageClientUser;

import java.time.LocalDate;
import java.time.LocalTime;

//this works for messages
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
        System.out.println(Sender);
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getSenderEmail() {
        System.out.println(SenderEmail);
        return SenderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        SenderEmail = senderEmail;
    }

    public String getMessage() {
        System.out.println(Message);
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public LocalDate getDate() {
        System.out.println(Date);
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalTime getTime() {
        System.out.println(Time);
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
