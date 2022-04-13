package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewEditProfilePt2 extends AppCompatActivity {
    Integer userId;
    TextView username, password, email, age1;
    Button submit;
    Button returnToPortal;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_profile_pt2);

        DB = new DBHelper(this);
        userId = getIntent().getIntExtra("userId", -1);
        System.out.println("User Id in ViewEditProfilePt2.java" + userId);

        username = (EditText) findViewById(R.id.userNameDisplayET);
        password = (EditText)findViewById(R.id.passwordDisplayET);
        age1 = (EditText)findViewById(R.id.ageDisplayET);
        password = (EditText)findViewById(R.id.passwordDisplayET);
        email = (EditText)findViewById(R.id.emailDisplayET);
        submit = (Button) findViewById(R.id.buttonSubmit);
        returnToPortal = (Button) findViewById(R.id.returnToPortalButton);

        returnToPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), UserPortal.class);

                System.out.println("In ViewEditProfilePt2.java ASDFQWERQEQVASDVASGA: " + userId);
                intent.putExtra("userId", userId);

                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), UserPortal.class);

                Toast.makeText(ViewEditProfilePt2.this, "Changes Saved!", Toast.LENGTH_SHORT).show();
                System.out.println("In ViewEditProfilePt2.java ASDFQWERQEQVASDVASGA: " + userId);
                intent.putExtra("userId", userId);

                startActivity(intent);
            }
        });
    }

}