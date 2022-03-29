package com.example.easy_team_up;

import java.time.LocalTime;

public class Event2 {
    private String name;
    private String desc;
    private String address;
    private LocalTime time;
    private int userID;
    private int eventID;

    public Event2(String name, String desc, String address, LocalTime time){
        this.name = name;
        this.desc = desc;
        this.address = address;
        this.time = time;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }


}