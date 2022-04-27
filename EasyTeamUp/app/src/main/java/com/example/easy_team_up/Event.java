package com.example.easy_team_up;

public class Event {
    private String name;
    private String desc;
    private String address;
    private String eventStart;
    private String eventEnd;
    private String eventDate;
    private String dueTime;
    private String dueDate;
    private String userID;
    private String eventID;
    private boolean publicFlag;

    public Event(){}

    public Event(String name, String desc, String address, String eventStart,
                 String eventEnd, String eventDate, String dueTime,
                 String dueDate, String userID, String eventID, Boolean publicFlag) {
        this.name = name;
        this.desc = desc;
        this.address = address;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventDate = eventDate;
        this.dueTime = dueTime;
        this.dueDate = dueDate;
        this.userID = userID;
        this.publicFlag = publicFlag;
        this.eventID = eventID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Boolean getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(Boolean publicFlag) {
        this.publicFlag = publicFlag;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}
