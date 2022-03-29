package com.example.easy_team_up;

public class Event {
    Integer eventId;
    Integer userId;
    String title;
//    String date;

    public Event(Integer eventId, Integer userId, String title){
        this.userId = userId;
        this.eventId = eventId;
        this.title = title;
    }
}
