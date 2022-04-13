package com.example.easy_team_up;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewUser extends AppCompatActivity {

    EditText username, password, repassword, etEmail, etAge;
    Button signup, returnToLogin;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        returnToLogin = (Button) findViewById(R.id.btnLogin2);
        etEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        etAge = (EditText) findViewById(R.id.editTextNumberDecimal);

        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String email = etEmail.getText().toString();
                String age = etAge.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("") || email.equals("") || age.equals("")) {
                    Toast.makeText(CreateNewUser.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkUserName(user);
                        if (checkuser == false) {
                            Integer userIdCorrect = DB.insertNewUserData(user, pass, email, age);
                            if (userIdCorrect != -1) {
                                Toast.makeText(CreateNewUser.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent  = new Intent(getApplicationContext(), UserPortal.class);
                                intent.putExtra("userName", user);

                                Cursor cursor = DB.getIdfromUsername(user);
                                cursor.moveToLast();
                                Integer userId = cursor.getInt(0);

                                System.out.println("ASDFQWERQEQVASDVASGA" + userId);
                                intent.putExtra("userId", userId);

                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(CreateNewUser.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(CreateNewUser.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(CreateNewUser.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        returnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(intent);
            }
        });
    }
}