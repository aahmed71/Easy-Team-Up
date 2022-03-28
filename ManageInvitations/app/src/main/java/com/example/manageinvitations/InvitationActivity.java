package com.example.manageinvitations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.LinkedList;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InvitationActivity extends AppCompatActivity{
    DBHelper DB;
    Button switchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);
        List<Invite> invites = new LinkedList<>();
        //want grab from database here: need to pass in the userid
        Cursor res = DB.getInvitations(1);
        if(res.getCount()==0){
            Toast.makeText(InvitationActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println("hello");
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

        switchView = (Button) findViewById(R.id.inviteRSVPView);
        switchView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TextView title = findViewById(R.id.inviteRSVPTitle);
                Button changeView = findViewById(R.id.inviteRSVPView);
                if(title.getText() == "My Invitations") {
                    title.setText("My RSVPs");
                    changeView.setText("View Invitations");
                }
                else{
                    title.setText("My Invitations");
                    changeView.setText("View RSVPs");
                }
            }
        });
    }
}