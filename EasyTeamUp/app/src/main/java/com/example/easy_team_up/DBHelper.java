package com.example.easy_team_up;

import java.time.LocalTime;
import java.util.ArrayList;

public class DBHelper {
    public static ArrayList<Event> getPublicEvents(){
        ArrayList<Event> publicEvents = new ArrayList<Event>();
        publicEvents.add(new Event("Study", "Studying for midterms.",
                "3115 Orchard Avenue, Los Angeles, CA", LocalTime.of(10,43)));
        publicEvents.add(new Event("Birthday", "Bob's birthday.",
                "3335 S Figueroa St, Los Angeles, CA", LocalTime.of(9,50)));
        return publicEvents;
    }
}
