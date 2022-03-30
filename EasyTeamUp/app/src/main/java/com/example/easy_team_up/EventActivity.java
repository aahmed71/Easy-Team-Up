package com.example.easy_team_up;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {
    Button accept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        LinearLayout linearLayout =  (LinearLayout) findViewById(R.id.linearlayout);
        TextView nameText = (TextView)findViewById(R.id.name);
        nameText.setText(getIntent().getStringExtra("name"));
        TextView addrText = (TextView)findViewById(R.id.address);
        addrText.setText(getIntent().getStringExtra("address"));
        TextView timeText = (TextView)findViewById(R.id.time);
        timeText.setText(getIntent().getStringExtra("time"));
        TextView descText = (TextView)findViewById(R.id.desc);
        descText.setText(getIntent().getStringExtra("desc"));

        accept = (Button)findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                accept.setVisibility(View.GONE);
            }
        });

    }
}