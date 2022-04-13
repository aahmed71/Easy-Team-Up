package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewEditProfile extends AppCompatActivity {
    Integer userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getIntent().getIntExtra("userId", -1);
        setContentView(R.layout.activity_view_edit_profile);
    }
}