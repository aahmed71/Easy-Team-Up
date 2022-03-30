package com.example.easy_team_up;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class EditEvent extends AppCompatActivity {
    DBHelper DB;
    Integer eventId;
    String eventName;
    String eventType;
    String eventLocation;
    String privateOrPublic;
    String month;
    Integer date;
    Integer year;
    Integer startTime;
    Integer endTime;
    Integer userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        //get intent
        Intent intent = getIntent();
        eventId = intent.getIntExtra("eventId", -1);
        userId = intent.getIntExtra("userId", -1);
        if(eventId == -1){
            System.out.println("event does not exist");
            return;
        }
        else System.out.println("editing event: " + eventId);

        //grab event details and flesh out the hints
        DB = new DBHelper(this);
        Cursor eventInfo = DB.getEventById(eventId);
        eventInfo.moveToFirst();

        eventName = eventInfo.getString(2);
        System.out.println("event name: " + eventInfo.getString(2));
        EditText editName = (EditText) findViewById(R.id.eventName);
        editName.setHint("Event Name: " + eventName);

        eventType = eventInfo.getString(3);
        EditText editType = (EditText) findViewById(R.id.eventType);
        if(eventType != null) editType.setHint("Event Type: " + eventType);

        eventLocation = eventInfo.getString(14);
        System.out.println("event location: " + eventLocation);
        EditText editLocation = (EditText) findViewById(R.id.location);
        if(eventLocation != null) editLocation.setHint("Event Location: " + eventLocation);

        Spinner monthSpinner = findViewById(R.id.month);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("January");
        arrayList.add("February");
        arrayList.add("March");
        arrayList.add("April");
        arrayList.add("May");
        arrayList.add("June");
        arrayList.add("July");
        arrayList.add("August");
        arrayList.add("September");
        arrayList.add("October");
        arrayList.add("November");
        arrayList.add("December");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(arrayAdapter);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                month = tutorialsName;
//                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                month = eventInfo.getString(6);
//                Toast.makeText(parent.getContext(), "Selected: " + month, Toast.LENGTH_LONG).show();
            }
        });
        Spinner dateSpinner = findViewById(R.id.date);
        ArrayList<String> dateList = new ArrayList<>();
        for(int i = 1; i <= 31; i++){
            dateList.add(String.valueOf(i));
        }
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dateList);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(dateAdapter);
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                date = Integer.parseInt(tutorialsName);
//                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                date = eventInfo.getInt(7);
            }
        });
        Spinner yearSpinner = findViewById(R.id.year);
        ArrayList<String> yearList = new ArrayList<>();
        for(int i = 2022; i <= 2100; i++){
            yearList.add(String.valueOf(i));
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearList);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                year = Integer.parseInt(tutorialsName);
                System.out.println(tutorialsName);
//                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                year = eventInfo.getInt(8);
//                Toast.makeText(parent.getContext(), "Selected: " + String.valueOf(year), Toast.LENGTH_LONG).show();
            }
        });
        Spinner timeSpinner = findViewById(R.id.startTime);
        ArrayList<String> timeList = new ArrayList<>();
        for(int i = 0; i <= 24; i++){
            timeList.add(String.valueOf(i));
        }
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, timeList);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                startTime = Integer.parseInt(tutorialsName);
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                startTime = eventInfo.getInt(4);
            }
        });

        Spinner endTimeSpinner = findViewById(R.id.endTime);
        ArrayList<String> endTimeList = new ArrayList<>();
        for(int i = 0; i <= 24; i++){
            endTimeList.add(String.valueOf(i));
        }
        ArrayAdapter<String> endTimeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, endTimeList);
        endTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endTimeSpinner.setAdapter(endTimeAdapter);
        endTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                endTime = Integer.parseInt(tutorialsName);
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                endTime = eventInfo.getInt(5);
            }
        });
        View.OnClickListener clickPrivate = new View.OnClickListener(){
            public void onClick(View v) {
                System.out.println("clicking private");
                privateOrPublic = "private";
            }
        };
        View.OnClickListener clickPublic = new View.OnClickListener(){
            public void onClick(View v) {
                System.out.println("clicking public");
                privateOrPublic = "public";
            }
        };
        RadioButton privateEvent = (RadioButton) findViewById(R.id.privateEventButton);
        privateEvent.setOnClickListener(clickPrivate);
        RadioButton publicEvent = (RadioButton) findViewById(R.id.publicEventButton);
        publicEvent.setOnClickListener(clickPublic);

        View.OnClickListener submitChanges = new View.OnClickListener(){
            public void onClick(View v) {
                System.out.println("submitting");
                eventName = editName.getText().toString();
                eventType = editType.getText().toString();
                eventLocation = editLocation.getText().toString();
                System.out.println(eventName);
                System.out.println(eventType);
                System.out.println(eventLocation);
                System.out.println(privateOrPublic);
                System.out.println(month);
                System.out.println(date);
                System.out.println(year);
                System.out.println(startTime);
                System.out.println(endTime);
                DB.updateMyEvent(eventId, eventName, eventType, startTime,
                        endTime, month, date, year, eventLocation, privateOrPublic);
                DB.close();
                //return to my events
                returnToMyEvents();
            }
        };
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(submitChanges);
    }
    public void returnToMyEvents(){
        Intent intent = new Intent(this, ViewMyEvents.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}