package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewEditProfile extends AppCompatActivity {
    Integer userId;
    DBHelper DB;
    ArrayList<String> userInfo;
    TextView username, password, email, age1;
    Button editProfile;
    Button returnToPortal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB = new DBHelper(this);
        userInfo = new ArrayList<String>();
        userId = getIntent().getIntExtra("userId", -1);
        System.out.println("User Id in ViewEditProfile.java" + userId);
        setContentView(R.layout.activity_view_edit_profile);

        username = (TextView) findViewById(R.id.userNameDisplayTV);
        password = (TextView)findViewById(R.id.passwordDisplayTV);
        age1 = (TextView)findViewById(R.id.ageDisplayTV);
        password = (TextView)findViewById(R.id.passwordDisplayTV);
        email = (TextView)findViewById(R.id.emailDisplayTV);
        editProfile = (Button) findViewById(R.id.buttonEditProfile);
        returnToPortal = (Button) findViewById(R.id.returnToPortalButton);

        // (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT, age INT
        Cursor cursor = DB.returnUserInfo(userId);
        cursor.moveToFirst();

        for (int i = 0; i < 5; i++) {
            userInfo.add(String.valueOf(cursor.getString(i)));
        }

        System.out.println(userInfo);

        username.setText(userInfo.get(1));
        password.setText(userInfo.get(2));
        email.setText(userInfo.get(3));
        age1.setText(userInfo.get(4));

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), ViewEditProfilePt2.class);

                System.out.println("In ViewEditProfile.java ASDFQWERQEQVASDVASGA: " + userId);
                intent.putExtra("userId", userId);

                startActivity(intent);
            }
        });

        returnToPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), UserPortal.class);
                System.out.println("In ViewEditProfile.java ASDFQWERQEQVASDVASGA: " + userId);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

    }
}