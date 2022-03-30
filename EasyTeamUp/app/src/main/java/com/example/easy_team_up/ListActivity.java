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
    Button userPortal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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
        PublicAdapter adapter = new PublicAdapter(invites, DB, this);
        recyclerView.setAdapter(adapter);

    }
    public void goToPortal(){
        Intent intent = new Intent(this, UserPortal.class);
        startActivity(intent);
    }
}