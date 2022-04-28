package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        Button logIn = (Button) findViewById(R.id.logIn);
        logIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goLogIn();
            }
        });
    }
    public void goLogIn(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}