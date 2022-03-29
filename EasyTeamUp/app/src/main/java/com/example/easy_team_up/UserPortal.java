package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserPortal extends AppCompatActivity {
    Button rsvpInvites;
    Button viewMap;
    Button viewEvent;
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

        viewMap = findViewById(R.id.viewMapButton);
        viewMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToMaps();
            }
        });

        viewEvent = findViewById(R.id.viewEventsButton);
        viewEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToEvents();
            }
        });
        
    }
    public void goToInvitations(){
        Intent intent = new Intent(this, InvitationActivity.class);
        startActivity(intent);
    }

    public void goToMaps(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void goToEvents(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }


}