package com.example.manageinvitations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.LinkedList;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class InvitationActivity extends AppCompatActivity{
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);
        List<Invite> invites = new LinkedList<>();
        //want grab from database here
        Cursor res = DB.getData(1);
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
        DemoAdapter adapter = new DemoAdapter(invites, DB);
        recyclerView.setAdapter(adapter);

//        findViewById(R.id.add).setOnClickListener(view ->{
//            items.add(data[counter%3]);
//            counter++;
//            adapter.notifyItemInserted(items.size()-1);
//        });
    }
}