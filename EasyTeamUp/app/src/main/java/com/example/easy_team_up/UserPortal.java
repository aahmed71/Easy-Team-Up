package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserPortal extends AppCompatActivity {
    Button rsvpInvites;
    Button viewEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portal);
        rsvpInvites = findViewById(R.id.rsvpInviteButton);
        rsvpInvites.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToInvitations();
            }
        });

        viewEvents = findViewById(R.id.viewEventsButton);
        viewEvents.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToEvents();
            }
        });
        
    }
    public void goToInvitations(){
        Intent intent = new Intent(this, InvitationActivity.class);
        startActivity(intent);
    }

    public void goToEvents(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}