package com.example.easy_team_up;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<String> createdEvents, invites, rsvps;
    public User(String name){
        this.name = name;
        createdEvents = new ArrayList<String>();
        invites = new ArrayList<String>();
        rsvps = new ArrayList<String>();
    }
}
