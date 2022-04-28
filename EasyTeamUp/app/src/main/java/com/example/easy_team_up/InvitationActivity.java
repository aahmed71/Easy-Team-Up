package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.LinkedList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InvitationActivity extends AppCompatActivity{
    DBHelper DB;
    Button switchView;
    Button userPortal;
    Integer userId;
    public String monthToNumber(String month){
        String [] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Nov",
                "Dec"};
        int idx = java.util.Arrays.asList(months).indexOf(month) + 1;
        System.out.println("idx: "+ idx);
        if(idx < 10){
            return "0" + Integer.toString(idx);
        }
        else return Integer.toString(idx);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_invitations);
        userId = getIntent().getIntExtra("userId", -1);
        System.out.println("manage invitations userid: " + userId);
        DB = new DBHelper(this);
        List<Invite> invites = new LinkedList<>();
        //want grab from database here: need to pass in the userid
        Cursor invite_res = DB.getInvitations(userId);
        while(invite_res.moveToNext()){
            int eventId = invite_res.getInt(1);
            Cursor res = DB.getEventById(eventId);
            res.moveToFirst();
            //only add to invites if sign up due date is after now
            String year = Integer.toString(res.getInt(11));
            String month = monthToNumber(res.getString(9));
            Integer numDate = res.getInt(10);
            String date;
            if(numDate >= 1 && numDate <= 9){
                date = "0" + Integer.toString(numDate);
            }
            else date = Integer.toString(numDate);
            Integer numTime = res.getInt(12);
            String time;
            if(numTime < 10){
                time = "0" + Integer.toString(res.getInt(12));
            }
            else time = Integer.toString(res.getInt(12));
            String str = year + "-" + month + '-' + date + ' ' + time;
            System.out.println("event string: " + str);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
            LocalDateTime now = LocalDateTime.now();
            // if past edit due date, delete event
            Invite invite = new Invite(invite_res.getInt(0), invite_res.getInt(1), invite_res.getInt(2));
            if(now.isBefore(dateTime)) {
                //invite user event ids
                invites.add(invite);
            }
            else{
                //delete invite
                DB.deleteInvitation(invite);
            }
        }
        System.out.println( invites.size());
        //end snippet

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DemoAdapter adapter = new DemoAdapter(invites, DB, this, userId);
        recyclerView.setAdapter(adapter);
        userPortal = (Button) findViewById(R.id.returnUser);
        userPortal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                goToPortal();
            }
        });

        switchView = (Button) findViewById(R.id.inviteRSVPView);
        switchView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TextView title = findViewById(R.id.inviteRSVPTitle);
                Button changeView = findViewById(R.id.inviteRSVPView);
                if(title.getText() == "My Invitations") {
                    title.setText("My RSVPs");
                    changeView.setText("View Invitations");
                    //get updated rsvps
                    List<Invite> rsvps = new LinkedList<>();
                    Cursor rsvpRes = DB.getRSVPs(userId);
                    while(rsvpRes.moveToNext()){
                        //id event user
                        Invite rsvp = new Invite(rsvpRes.getInt(0), rsvpRes.getInt(1), rsvpRes.getInt(2));
                        rsvps.add(rsvp);
                    }

                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(InvitationActivity.this));
                    RSVPAdapter adapter = new RSVPAdapter(rsvps, DB, InvitationActivity.this, userId);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    title.setText("My Invitations");
                    changeView.setText("View RSVPs");

                    List<Invite> invites = new LinkedList<>();
                    //get updated invitations
                    Cursor res = DB.getInvitations(userId);
                    while(res.moveToNext()){
                        //invite user event
                        Invite invite = new Invite(res.getInt(0), res.getInt(1), res.getInt(2));
                        invites.add(invite);
                    }

                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(InvitationActivity.this));
                    //userId
                    DemoAdapter adapter = new DemoAdapter(invites, DB, InvitationActivity.this, userId);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
    public void goToPortal(){
        Intent intent = new Intent(this, UserPortal.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}