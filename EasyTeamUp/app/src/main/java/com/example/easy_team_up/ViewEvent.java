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
        System.out.println("Event id: " + eventId);
        DB = new DBHelper(this);

        Cursor res = DB.getEventById(eventId);
        System.out.println("result size: " + res.getCount());
        res.moveToFirst();
//        String userId = String.valueOf(res.getInt(2));
        String location = res.getString(3);
        Cursor userRes = DB.getNameFromUserId(res.getInt(2));
        userRes.moveToFirst();
        //setting title
        TextView titleView = findViewById(R.id.eventTitle);
        titleView.setText(res.getString(1));
        //setting host
        TextView hostView = findViewById(R.id.host);
        hostView.setText(userRes.getString(1));
        //setting location
        TextView locationView = findViewById(R.id.location);
        locationView.setText(location);
        button = findViewById(R.id.goBack);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                displayInvitations();
            }
        });
    }

    public void displayInvitations(){
        Intent intent = new Intent(this, InvitationActivity.class);
        startActivity(intent);
    }
}