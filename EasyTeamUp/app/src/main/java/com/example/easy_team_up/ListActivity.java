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
import android.widget.Toast;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    DBHelper DB;
    Button userPortal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ArrayList<Event2> invites = new ArrayList<Event2>();
        //invites.add(new Event2("Study", "Studying for midterms.",
        //        "3115 Orchard Avenue, Los Angeles, CA", LocalTime.of(10,43)));
        //invites.add(new Event2("Birthday", "Bob's birthday.",
        //        "3335 S Figueroa St, Los Angeles, CA", LocalTime.of(9,50)));
        DB = new DBHelper(this);
        //want grab from database here: need to pass in the userid

        //try id that doesnt exist
        Cursor res = DB.getPublicEvents();
        while(res.moveToNext()){
            //invite user event title
            LocalTime time = LocalTime.parse(res.getString(4));
            Event2 invite = new Event2(res.getString(2), res.getString(3), res.getString(14), time, res.getInt(1), res.getInt(0));
            invites.add(invite);
        }
        //end snippet

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PublicAdapter adapter = new PublicAdapter(invites, DB, this, 1);
        recyclerView.setAdapter(adapter);

    }
    public void goToPortal(){
        Intent intent = new Intent(this, UserPortal.class);
        startActivity(intent);
    }
}