package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String eventType;
    String eventName;
    String startTime;
    String endTime;
    String eventDate;
    String eventMonth;
    String eventYear;
    String signupDueMonth;
    int signupDueDate;
    int signupDueYear;
    int signupDueTime;
    String currentUser;
    String eventDescription;
    String publicOrPrivate;

    String temp;
    String temp1;
    String temp2;
    String temp3;
    String temp8;
    String temp9;

    // DB1 = new DBHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        currentUser = getIntent().getStringExtra("user");

        // Event name text view
        EditText etEventName = (EditText)findViewById(R.id.editText);
        // Event description text view
        EditText etEventDescription = (EditText)findViewById(R.id.editText3);
        // spinner = event type
        Spinner spinner = findViewById(R.id.spinner);
        // spinner 1 = start time
        Spinner spinner1 = findViewById(R.id.spinner1);
        // spinner 2 = end time
        Spinner spinner2 = findViewById(R.id.spinner2);

        // spinner 7 = event month
        Spinner spinner7 = findViewById(R.id.spinner7);
        // spinner 8 = event date
        Spinner spinner8 = findViewById(R.id.spinner8);
        // spinner 9 = event year
        Spinner spinner9 = findViewById(R.id.spinner9);
        // public


        /*
        // spinner 3 = sign up due month
        Spinner spinner3 = findViewById(R.id.spinner3);
        // spinner 4 = sign up due date
        Spinner spinner4 = findViewById(R.id.spinner4);
        // spinner 5 = sign up due year
        Spinner spinner5 = findViewById(R.id.spinner5);
        // spinner 6 = sign up due time
        Spinner spinner6 = findViewById(R.id.spinner6);*/


        // Submit button
        Button buttonNext = findViewById(R.id.buttonNext);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.eventType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);
        spinner7.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.dates, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);
        spinner8.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter9);
        spinner9.setOnItemSelectedListener(this);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = etEventName.getText().toString();

                eventDescription = etEventDescription.getText().toString();

                eventType = temp;

                startTime = temp1;

                endTime = temp2;

                eventMonth = temp3;

                eventDate = temp8;

                eventYear = temp9;

                if (eventName.equals("") || eventDescription.equals("")) {
                    Toast.makeText(CreateEvent.this, "Please fill in Event Name and/or Event Description", Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.println("HEREERER");
                    System.out.println("Eventname = " + eventName);
                    System.out.println("event type = " + temp);
                    System.out.println("event start time = " + temp1);
                    //System.out.println("event start time = " + startTime);
                    System.out.println("event end time = " + temp2);
                    System.out.println("event Month = " + temp3);
                    System.out.println("event Date = " + temp8);
                    System.out.println("event Year = " + temp9);
                    System.out.println("creator = " + currentUser);
                    System.out.println("line 165");

                    Intent intent = new Intent(getApplicationContext(), CreateEventPt2.class);

                    intent.putExtra("eventName", eventName);
                    intent.putExtra("eventType", eventType);
                    intent.putExtra("eventStartTime", startTime);
                    intent.putExtra("eventEndTime", endTime);
                    intent.putExtra("eventMonth", eventMonth);
                    intent.putExtra("eventDate", eventDate);
                    intent.putExtra("eventYear", eventYear);
                    intent.putExtra("eventCreator", currentUser);
                    intent.putExtra("eventDescription", eventDescription);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        switch (parent.getId()) {
            case R.id.spinner:
                temp = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner1:
                temp1 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner2:
                temp2 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner7:
                temp3 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner8:
                temp8 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner9:
                temp9 = parent.getItemAtPosition(position).toString();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}