package com.example.proiectandroid;

public class MessageClass {
    private String uid;
    private String sender;
    private String message;
    private String time;

    public MessageClass(){

    }

    public MessageClass(String uid, String sender, String message, String time) {
        this.uid = uid;
        this.sender = sender;
        this.message = message;
        this.time=time;
    }

    public MessageClass(String sender, String message, String time) {
        this.sender = sender;
        this.message = message;
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageClass{" +
                "sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
