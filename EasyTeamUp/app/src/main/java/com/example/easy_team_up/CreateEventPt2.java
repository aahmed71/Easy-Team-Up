package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateEventPt2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String eventType;
    String eventName;
    String eventDescription;
    int startTime;
    int endTime;
    int eventDate;
    String eventMonth;
    int eventYear;
    String signupDueMonth;
    int signupDueDate;
    int signupDueYear;
    int signupDueTime;
    String currentUser;
    String publicOrPrivate;
    DBHelper DB1;
    String temp4;
    String temp5;
    String temp6;
    String temp10;
    String userSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_pt2);

                     /*
                Integer.parseInt(removeTrailingZeroes(removeLeadingZeroes(temp1)));
                Integer.parseInt(removeTrailingZeroes(removeLeadingZeroes(temp2)));
                Integer.parseInt(removeTrailingZeroes(removeLeadingZeroes(temp8)));
                 */
        eventDescription = getIntent().getStringExtra("eventDescription");
        eventName = getIntent().getStringExtra("eventName");
        eventType = getIntent().getStringExtra("eventType");
        startTime = Integer.parseInt(removeLeadingZeroes(removeTrailingZeroes(getIntent().getStringExtra("eventStartTime"))));
        endTime = Integer.parseInt(removeLeadingZeroes(removeTrailingZeroes(getIntent().getStringExtra("eventEndTime"))));
        eventMonth = getIntent().getStringExtra("eventMonth");
        eventDate = Integer.parseInt(removeLeadingZeroes(removeTrailingZeroes(getIntent().getStringExtra("eventDate"))));
        eventYear = Integer.parseInt(getIntent().getStringExtra("eventYear"));
        currentUser = getIntent().getStringExtra("eventCreator");


        // spinner 3 = sign up due month
        Spinner spinner3 = findViewById(R.id.spinner3);
        // spinner 4 = sign up due date
        Spinner spinner4 = findViewById(R.id.spinner4);
        // spinner 5 = sign up due year
        Spinner spinner5 = findViewById(R.id.spinner5);
        // spinner 6 = sign up due time
        Spinner spinner6 = findViewById(R.id.spinner6);
        // public
        Spinner spinner10 = findViewById(R.id.spinner10);
        // Submit button
        Button buttonSubmit = findViewById(R.id.buttonCreate);
        // User search button
        Button buttonSearch = findViewById(R.id.buttonCreate2);
        EditText etUsernameSearch = findViewById(R.id.editTextTextPersonName);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.dates, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
        spinner6.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this, R.array.privorpub, android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner10.setAdapter(adapter10);
        spinner10.setOnItemSelectedListener(this);

        DB1 = new DBHelper(this);



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp10.toLowerCase();
                signupDueDate = Integer.parseInt(removeLeadingZeroes(removeTrailingZeroes(temp4)));
                signupDueYear = Integer.parseInt(removeLeadingZeroes(removeTrailingZeroes(temp5)));
                signupDueTime = Integer.parseInt(removeLeadingZeroes(removeTrailingZeroes(temp6)));
                publicOrPrivate = temp10;

                System.out.println("Event Type is set to " + eventType);
                System.out.println("Start time is set to " + startTime);
                System.out.println("End time is set to " + endTime);
                System.out.println("Event month: " + eventMonth);
                System.out.println("Event date: " + eventDate);
                System.out.println("Event year: " + eventYear);
                System.out.println("Month is set to " + signupDueMonth);
                System.out.println("Date is set to " + signupDueDate);
                System.out.println("Year is set to " + signupDueYear);
                System.out.println("Due time is set to " + signupDueTime);
                System.out.println("User is : " + currentUser);
                System.out.println("Event name: " + eventName);
                System.out.println("Public or Private: " + publicOrPrivate);
                System.out.println("Event Description: " + eventDescription);



                DB1.insertNewEventData(currentUser, eventName, eventType, startTime,
                        endTime, eventMonth, eventDate, eventYear, signupDueMonth,
                        signupDueDate, signupDueYear, signupDueTime, publicOrPrivate, eventDescription);
                Toast.makeText(CreateEventPt2.this, "Event Created Successfully", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getApplicationContext(), UserPortal.class);
                startActivity(intent);
                //  DB1.insertNewEventData(currentUser, eventName, eventType, startTime, endTime, eventMonth, eventDate, eventYear, signupDueMonth, signupDueDate, signupDueYear, signupDueTime);


            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                userSearch = etUsernameSearch.getText().toString();
                if (DB1.checkUserName(userSearch)) {
                    Cursor cursor = DB1.getIdfromUsername(currentUser);
                    if (cursor.moveToFirst()) {
                        Toast.makeText(CreateEventPt2.this, "Invitation Sent!", Toast.LENGTH_SHORT).show();
                        //cursor.getString(cursor.getInt(1));
                    }
                }
                else {
                    Toast.makeText(CreateEventPt2.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        switch (parent.getId()) {
            case R.id.spinner3:
                signupDueMonth = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner4:
                temp4 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner5:
                temp5 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner6:
                temp6 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner10:
                temp10 = parent.getItemAtPosition(position).toString();
                break;
        }
    }
// just random changes so i can push this again
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    String removeLeadingZeroes(String s) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    String removeTrailingZeroes(String s) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}