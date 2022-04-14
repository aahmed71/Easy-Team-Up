package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_invitations);
        userId = getIntent().getIntExtra("userId", -1);
        System.out.println("manage invitations userid: " + userId);
        DB = new DBHelper(this);
        List<Invite> invites = new LinkedList<>();
        //want grab from database here: need to pass in the userid
        Cursor res = DB.getInvitations(userId);
        while(res.moveToNext()){
            //invite user event title
            Invite invite = new Invite(res.getInt(0), res.getInt(1), res.getInt(2));
            invites.add(invite);
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