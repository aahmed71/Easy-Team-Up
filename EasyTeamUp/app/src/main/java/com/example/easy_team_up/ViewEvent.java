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
        String location = res.getString(14);
        Cursor userRes = DB.getNameFromUserId(Integer.parseInt(res.getString(1)));
        userRes.moveToFirst();
        //setting title
        TextView titleView = findViewById(R.id.eventTitle2);
        titleView.setText(res.getString(2));
        //setting host
        TextView hostView = findViewById(R.id.host);
        //userRes.getString(1)
        String username = userRes.getString(1);
        hostView.setText(username);
        //setting location
        TextView locationView = findViewById(R.id.location);
        locationView.setText(location);

        TextView dateView = findViewById(R.id.date);
        String date = res.getString(6) + ", " + res.getInt(7) + " " + res.getInt(8);
        dateView.setText(date);

        TextView startView = findViewById(R.id.startTime);
        startView.setText("" + res.getInt(4));

        TextView endView = findViewById(R.id.endTime);
        endView.setText("" + res.getInt(5));

        TextView dueDateView = findViewById(R.id.dueDate);
        String dueDate = res.getString(9) + ", " + res.getInt(10) + " " + res.getInt(11);
        dueDateView.setText(dueDate);

        TextView dueTimeView = findViewById(R.id.dueTime);
        String dueTime = "" + res.getInt(12);
        dueTimeView.setText(dueTime);

        TextView typeView = findViewById(R.id.type);
        typeView.setText(res.getString(3));

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