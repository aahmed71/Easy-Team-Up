package com.example.manageinvitations;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.LinkedList;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);
        List<String> items = new LinkedList<>();
        //want grab from database here
        Cursor res = DB.getdata();
        if(res.getCount()==0){
            Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        while(res.moveToNext()){
            items.add(res.getString(1));
        }
        //end snippet

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DemoAdapter adapter = new DemoAdapter(items);
        recyclerView.setAdapter(adapter);

//        findViewById(R.id.add).setOnClickListener(view ->{
//            items.add(data[counter%3]);
//            counter++;
//            adapter.notifyItemInserted(items.size()-1);
//        });
    }
}