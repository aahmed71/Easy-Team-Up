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

public class CreateEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    /*
        String eventType;
        String eventName;
        String startTime;
        String endTime;
        String month;
        String date;
        String year;
        String eventDueTime; */
    String eventType;
    String eventName;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        currentUser = getIntent().getStringExtra("user");

        // Event name text view
        EditText etEventName = (EditText)findViewById(R.id.editText);
        // spinner = event type
        Spinner spinner = findViewById(R.id.spinner);
        // spinner 1 = start time
        Spinner spinner1 = findViewById(R.id.spinner1);
        // spinner 2 = end time
        Spinner spinner2 = findViewById(R.id.spinner2);

        // spinner 3 = month
        Spinner spinner3 = findViewById(R.id.spinner3);
        // spinner 4 = date
        Spinner spinner4 = findViewById(R.id.spinner4);
        // spinner 5 = year
        Spinner spinner5 = findViewById(R.id.spinner5);
        // spinner 6 = event due time
        Spinner spinner6 = findViewById(R.id.spinner6);
        // spinner 7 = event month
        Spinner spinner7 = findViewById(R.id.spinner7);
        // spinner 8 = event date
        Spinner spinner8 = findViewById(R.id.spinner8);
        // spinner 9 = event year
        Spinner spinner9 = findViewById(R.id.spinner9);
        // public
        RadioButton radioPublic = (RadioButton) findViewById(R.id.radioPublic);
        // private
        RadioButton radioPrivate = (RadioButton) findViewById(R.id.radioPrivate);
        // Submit button
        Button buttonSubmit = findViewById(R.id.button);


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
        /****************************/
        DB1 = new DBHelper(this);
        /*************************/

        radioPublic.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicOrPrivate = "public";
            }
        }));

        radioPrivate.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicOrPrivate = "private";
            }
        }));

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = etEventName.getText().toString();

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
                System.out.println("Public or Private" + publicOrPrivate);

                /****************************/
                DB1.insertNewEventData(currentUser, eventName, eventType, startTime,
                        endTime, eventMonth, eventDate, eventYear, signupDueMonth,
                        signupDueDate, signupDueYear, signupDueTime, publicOrPrivate);

                Intent intent  = new Intent(getApplicationContext(), UserPortal.class);
                startActivity(intent);
                //  DB1.insertNewEventData(currentUser, eventName, eventType, startTime, endTime, eventMonth, eventDate, eventYear, signupDueMonth, signupDueDate, signupDueYear, signupDueTime);
                /*************************/
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        switch (parent.getId()) {
            case R.id.spinner:
                String temp = parent.getItemAtPosition(position).toString();
                eventType = temp;
                break;
            case R.id.spinner1:
                String temp1 = parent.getItemAtPosition(position).toString();
                startTime = Integer.parseInt(temp1);
                break;
            case R.id.spinner2:
                String temp2 = parent.getItemAtPosition(position).toString();
                endTime = Integer.parseInt(temp2);
                break;
            case R.id.spinner3:
                signupDueMonth = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner4:
                String temp4 = parent.getItemAtPosition(position).toString();
                signupDueDate = Integer.parseInt(temp4);
                break;
            case R.id.spinner5:
                String temp5 = parent.getItemAtPosition(position).toString();
                signupDueYear = Integer.parseInt(temp5);
                break;
            case R.id.spinner6:
                String temp6 = parent.getItemAtPosition(position).toString();
                signupDueTime = Integer.parseInt(temp6);
                break;

            case R.id.spinner7:
                eventMonth = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner8:
                String temp8 = parent.getItemAtPosition(position).toString();
                eventDate = Integer.parseInt(temp8);
                break;
            case R.id.spinner9:
                String temp9 = parent.getItemAtPosition(position).toString();
                eventYear = Integer.parseInt(temp9);
                break;


            /*
            case R.id.spinner:
                eventType = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner1:
                startTime = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner2:
                endTime = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner3:
                month = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner4:
                date = parent.getItemAtPosition(position).toString();
            case R.id.spinner5:
                year = parent.getItemAtPosition(position).toString();
            case R.id.spinner6:
                eventDueTime = parent.getItemAtPosition(position).toString(); */

            /*
            case R.id.spinner:
                eventType = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner1:
                startTime = Integer.parseInt(parent.getItemAtPosition(position).toString());
                break;
            case R.id.spinner2:
                endTime = Integer.parseInt(parent.getItemAtPosition(position).toString());
                break;
            case R.id.spinner3:
                month = Integer.parseInt(parent.getItemAtPosition(position).toString());
                break;
            case R.id.spinner4:
                date = Integer.parseInt(parent.getItemAtPosition(position).toString());
            case R.id.spinner5:
                year = Integer.parseInt(parent.getItemAtPosition(position).toString());
            case R.id.spinner6:
                eventDueTime = Integer.parseInt(parent.getItemAtPosition(position).toString());*/
        }

        /*
        String text = parent.getItemAtPosition(position).toString();
        System.out.println(text);
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();*/
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}