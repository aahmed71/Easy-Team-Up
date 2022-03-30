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
        invites.add(new Event2("Study", "Studying for midterms.",
                "3115 Orchard Avenue, Los Angeles, CA", LocalTime.of(10,43)));
        invites.add(new Event2("Birthday", "Bob's birthday.",
                "3335 S Figueroa St, Los Angeles, CA", LocalTime.of(9,50)));
        DB = new DBHelper(this);
        //want grab from database here: need to pass in the userid

        //try id that doesnt exist
        Cursor res = DB.getInvitations(1);
//        if(res.getCount()==0){
//            Toast.makeText(InvitationActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//            return;
//        }
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