package com.example.manageinvitations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.LinkedList;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{
    String[]data = {"Hello", "Hi", "Welcome"};
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> items = new LinkedList<>();
        items.add("Code it");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DemoAdapter adapter = new DemoAdapter(items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.add).setOnClickListener(view ->{
            items.add(data[counter%3]);
            counter++;
            adapter.notifyItemInserted(items.size()-1);
        });
    }
}