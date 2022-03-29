package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    DBHelper DB;
    Button switchView;
    Button userPortal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_invitations);

        DB = new DBHelper(this);
        List<Invite> invites = new LinkedList<>();
        //want grab from database here: need to pass in the userid

        //try id that doesnt exist
        Cursor res = DB.getInvitations(1);
//        if(res.getCount()==0){
//            Toast.makeText(InvitationActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//            return;
//        }
        while(res.moveToNext()){
            //invite user event title
            Invite invite = new Invite(res.getInt(0), res.getInt(3), res.getInt(2),
                    res.getString(1));
            invites.add(invite);
        }
        //end snippet

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DemoAdapter adapter = new DemoAdapter(invites, DB, this);
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
                    Cursor rsvpRes = DB.getRSVPs(1);
                    while(rsvpRes.moveToNext()){
                        //id event user
                        Invite rsvp = new Invite(rsvpRes.getInt(0), rsvpRes.getInt(2), rsvpRes.getInt(1),
                                "empty");
                        rsvps.add(rsvp);
                    }

                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
                    RSVPAdapter adapter = new RSVPAdapter(rsvps, DB, ListActivity.this);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    title.setText("My Invitations");
                    changeView.setText("View RSVPs");

                    List<Invite> invites = new LinkedList<>();
                    //get updated invitations
                    Cursor res = DB.getInvitations(1);
                    while(res.moveToNext()){
                        //invite user event title
                        Invite invite = new Invite(res.getInt(0), res.getInt(3), res.getInt(2),
                                res.getString(1));
                        invites.add(invite);
                    }

                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
                    DemoAdapter adapter = new DemoAdapter(invites, DB, ListActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
    public void goToPortal(){
        Intent intent = new Intent(this, UserPortal.class);
        startActivity(intent);
    }
}