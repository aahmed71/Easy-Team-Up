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
    TextView usernameET, passwordET, emailET, reenterpasswordET, ageET;
    String username, password, reenterPassword, email, age = null;

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

        usernameET = (EditText) findViewById(R.id.userNameDisplayET);
        passwordET = (EditText)findViewById(R.id.passwordDisplayET);
        reenterpasswordET = (EditText)findViewById(R.id.passwordDisplayET2);
        ageET = (EditText)findViewById(R.id.ageDisplayET);
        passwordET = (EditText)findViewById(R.id.passwordDisplayET);
        emailET = (EditText)findViewById(R.id.emailDisplayET);
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

                username = usernameET.getText().toString();
                password = passwordET.getText().toString();
                reenterPassword = reenterpasswordET.getText().toString();
                age = ageET.getText().toString();
                email = emailET.getText().toString();

                if (password.equals(reenterPassword) && (!DB.checkUserName(username))) {
                    DB.updateProfile(userId, username, password, email, age);
                    Toast.makeText(ViewEditProfilePt2.this, "Changes Saved!", Toast.LENGTH_SHORT).show();

                    Intent intent  = new Intent(getApplicationContext(), ViewEditProfile.class);

                    System.out.println("In ViewEditProfilePt2.java ASDFQWERQEQVASDVASGA: " + userId);
                    intent.putExtra("userId", userId);

                    startActivity(intent);

                }
                else {
                    if (!password.equals(reenterPassword)) {
                        Toast.makeText(ViewEditProfilePt2.this, "Please enter the same password twice!", Toast.LENGTH_SHORT).show();
                    }
                    if (DB.checkUserName(username)) {
                        Toast.makeText(ViewEditProfilePt2.this, "User already exists, pick another username!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

}