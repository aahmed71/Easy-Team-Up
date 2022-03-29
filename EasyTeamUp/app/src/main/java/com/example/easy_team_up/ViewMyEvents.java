package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class ViewMyEvents extends AppCompatActivity {
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_events);
        DB = new DBHelper(this);
        List<Event> events = new LinkedList<>();
        //want grab from database here: need to pass in the userid

        //try id that doesnt exist
        Cursor res = DB.getMyEvents(1);
//        if(res.getCount()==0){
//            Toast.makeText(InvitationActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//            return;
//        }
        while(res.moveToNext()){
            //id title userid place: event class: id user title
            Event event = new Event(res.getInt(0), res.getInt(2), res.getString(1));
            events.add(event);
        }
        //end snippet

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EventAdapter adapter = new EventAdapter(events, DB, this);
        recyclerView.setAdapter(adapter);
    }
}