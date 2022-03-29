package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewEvent extends AppCompatActivity {
    private Button button;
    private String returnPage;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_event);
        Intent intent = getIntent();
        Integer eventId = intent.getIntExtra("eventId", -1);
        if(eventId == -1){
            System.out.println("event does not exist");
            return;
        }
        returnPage = intent.getStringExtra("returnPage");
        System.out.println("Event id: " + eventId);
        DB = new DBHelper(this);

        Cursor res = DB.getEventById(eventId);
        System.out.println("result size: " + res.getCount());
        res.moveToFirst();
        String location = res.getString(12);
        Cursor userRes = DB.getNameFromUserId(res.getInt(2));
        userRes.moveToFirst();
        //setting title
        TextView titleView = findViewById(R.id.eventTitle);
        titleView.setText(res.getString(2));
        //setting host
        TextView hostView = findViewById(R.id.host);
        //userRes.getString(1)
        hostView.setText("a host");
        //setting location
        TextView locationView = findViewById(R.id.location);
        locationView.setText(location);
        button = findViewById(R.id.goBack);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(returnPage.equals("viewMyEvents")) {
                    viewMyEvents();
                }
                else displayInvitations();
            }
        });
    }

    public void displayInvitations(){
        Intent intent = new Intent(this, InvitationActivity.class);
        startActivity(intent);
    }
    public void viewMyEvents(){
        Intent intent = new Intent(this, ViewMyEvents.class);
        startActivity(intent);
    }
}