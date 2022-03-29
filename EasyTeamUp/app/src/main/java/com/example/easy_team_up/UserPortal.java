package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserPortal extends AppCompatActivity {
    Button rsvpInvites;
    Button manageEvents;
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
        manageEvents = findViewById(R.id.manageEvents);
        manageEvents.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToManageEvents();
            }
        });
        
    }
    public void goToInvitations(){
        Intent intent = new Intent(this, InvitationActivity.class);
        startActivity(intent);
    }
    public void goToManageEvents(){
        Intent intent = new Intent(this, ViewMyEvents.class);
        startActivity(intent);
    }
}