package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class ViewNotifications extends AppCompatActivity {
    DBHelper DB;
    Button back;
    Integer userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notifications);
        userId = getIntent().getIntExtra("userId", -1);
        System.out.println("current userid: " + userId);
        DB = new DBHelper(this);
        List<Notification> notifications = new LinkedList<>();

        //want grab from database here: need to pass in the userid
        //try id that doesnt exist
        Cursor res = DB.getNotifications(userId);
        while(res.moveToNext()){
            //id title userid place: event class: id user title
            Notification n = new Notification(res.getInt(0), res.getInt(1), res.getString(2));
            notifications.add(n);
        }
        //end snippet

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NotificationAdapter adapter = new NotificationAdapter(notifications, DB, this);
        recyclerView.setAdapter(adapter);

        back = (Button) findViewById(R.id.returnUser);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("returning to user portal");
                goToPortal();
            }
        });
    }
    public void goToPortal(){
        Intent intent = new Intent(this, UserPortal.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}