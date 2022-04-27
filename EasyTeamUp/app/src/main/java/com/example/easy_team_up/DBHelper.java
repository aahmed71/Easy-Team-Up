package com.example.easy_team_up;

import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    public static void getEvents(GetEventsCallback callback){
        ArrayList<Event> events = new ArrayList<Event>();
        //publicEvents.add(new Event("Study", "Studying for midterms.",
        //        "3115 Orchard Avenue, Los Angeles, CA", "3:00", "5:00", "4/15/2022", "7:00", "3/30/2022", "apple", true));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Events").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // after getting the data we are calling on success method
                    // and inside this method we are checking if the received
                    // query snapshot is empty or not.
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // if the snapshot is not empty we are
                        // hiding our progress bar and adding
                        // our data in a list.
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            // after getting this list we are passing
                            // that list to our object class.
                            Event e = d.toObject(Event.class);
                            e.setEventID(d.getId());
                            events.add(e);
                            // and we will pass this object class
                            // inside our arraylist which we have
                            // created for recycler view.

                        }
                        // after adding the data to recycler view.
                        // we are calling recycler view notifuDataSetChanged
                        // method to notify that data has been changed in recycler view.
                        //courseRVAdapter.notifyDataSetChanged();
                    } else {
                        // if the snapshot is empty we are displaying a toast message.
                        //Toast.makeText(ListFragment."No data found in Database", Toast.LENGTH_SHORT).show();
                    }
                    callback.onCallback(events);
                }).addOnFailureListener(e -> {
                    // if we do not get any data or any error we are displaying
                    // a toast message that we do not get any data
                    //Toast.makeText(CourseDetails.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                });
    }
}

