package com.example.easy_team_up;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class CreateEvent extends Fragment {
    private EditText nameET, descET, addressET, eventStartET,
            eventEndET, eventDateET, dueTimeET, dueDateET, userInviteET;
    private CheckBox publicFlagCB;
    private Button submit, inviteButton;
    private String name, desc, address, eventStart,
            eventEnd, eventDate, dueTime, dueDate, userID;
    private boolean publicFlag;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ArrayList<String> invitedUsers;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);
        invitedUsers = new ArrayList<String>();

        // Input fields
        nameET = view.findViewById(R.id.event_name);
        descET = view.findViewById(R.id.event_desc);
        addressET = view.findViewById(R.id.event_address);
        eventStartET = view.findViewById(R.id.event_startTime);
        eventEndET = view.findViewById(R.id.event_EndTime);
        eventDateET = view.findViewById(R.id.event_date);
        dueTimeET = view.findViewById(R.id.event_dueTime);
        dueDateET = view.findViewById(R.id.event_dueDate);
        publicFlagCB = view.findViewById(R.id.event_publicPrivate);

        inviteButton = view.findViewById(R.id.inviteButton);
        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invitedUsers.add(userInviteET.getText().toString());
                userInviteET.setText("");
            }
        });
        submit = view.findViewById(R.id.event_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameET.getText().toString();
                desc = nameET.getText().toString();
                address = addressET.getText().toString();
                eventStart = eventStartET.getText().toString();
                eventEnd = eventEndET.getText().toString();
                eventDate = eventDateET.getText().toString();
                dueTime = dueTimeET.getText().toString();
                dueDate = dueDateET.getText().toString();
                publicFlag = publicFlagCB.isChecked();

                CollectionReference dbEvents = db.collection("Events");
                DocumentReference eventRef = dbEvents.document();
                Event event = new Event(name, desc, address, eventStart, eventEnd, eventDate, dueTime,
                        dueDate, mAuth.getCurrentUser().getEmail(), eventRef.getId(), publicFlag);
                eventRef.set(event).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // after the data addition is successful
                        // we are displaying a success toast message.
                        Toast.makeText(getContext(), "Your event has been added!", Toast.LENGTH_SHORT).show();
                        DocumentReference userRef = db.collection("Users").document(mAuth.getCurrentUser().getEmail());
                        userRef.update("createdEvents", FieldValue.arrayUnion(eventRef.getId()))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Your event has been added!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "Fail to add event. \n" + e, Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // this method is called when the data addition process is failed.
                        // displaying a toast message when data addition is failed.
                        Toast.makeText(getContext(), "Fail to add event. \n" + e, Toast.LENGTH_SHORT).show();
                    }
                });
                // Iterate through invited users and send invite
                for(String user : invitedUsers){
                    String eventID = eventRef.getId();
                    DocumentReference userRef = db.collection("Users").document(user);
                    userRef.update("invites", FieldValue.arrayUnion(eventRef.getId()))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "Failed to invite user: " + user + ". \n" + e, Toast.LENGTH_SHORT).show();
                                }
                            });

                }
                invitedUsers.clear();
            }
        });
        // Inflate the layout for this fragment
        return view;

    }
}