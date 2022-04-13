package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserPortal extends AppCompatActivity {
    Button viewNotifications;
    Button rsvpInvites;
    Button manageEvents;
    Button viewMap;
    Button viewEvent;
    Button createEvent;
    Button logOut;
    Integer userId;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userId = getIntent().getIntExtra("userId", -1);
        userName = getIntent().getStringExtra("userName");
        System.out.println("current userid: " + userId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portal);
        logOut = findViewById(R.id.logOutButton);
        logOut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                logOutUser();
            }
        });

        viewNotifications = findViewById(R.id.notificationsButton);
        viewNotifications.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToNotifications();
            }
        });

        rsvpInvites = findViewById(R.id.rsvpInviteButton);
        rsvpInvites.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToInvitations();
            }
        });

        createEvent = findViewById((R.id.createEventButton));
        createEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToCreateEvent();
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
        manageEvents = findViewById(R.id.manageEvents);
        manageEvents.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToManageEvents();
            }
        });

    }

    public void goToNotifications(){
        Intent intent = new Intent(this, ViewNotifications.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void logOutUser(){
        Intent intent = new Intent(this, LoginActivity.class);
        System.out.println("logging out user");
        intent.putExtra("userId", -1);
        startActivity(intent);
    }

    public void goToInvitations(){
        Intent intent = new Intent(this, InvitationActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void goToCreateEvent(){
        Intent intent = new Intent(this, CreateEvent.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
    public void goToManageEvents(){
        Intent intent = new Intent(this, ViewMyEvents.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void goToMaps(){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void goToEvents(){
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }


}