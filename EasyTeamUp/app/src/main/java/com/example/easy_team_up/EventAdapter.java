package com.example.easy_team_up;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.Month;

public class EventAdapter extends RecyclerView.Adapter<EventVH>{
    List<Event> events;
    DBHelper DB;
    Context c;
    Integer userId;
    Button edit;
    View view;
    public EventAdapter(List<Event> events, DBHelper DB, Context c, Integer userId){
        this.events = events;
        this.DB = DB;
        this.c = c;
        this.userId = userId;
    }
    @NonNull
    @Override
    public EventVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myeventitem, parent, false);
         edit = (Button) view.findViewById(R.id.editEvent);
        return new EventVH(view).linkAdapter(this);
    }

    public String monthToNumber(String month){
        String [] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Nov",
        "Dec"};
        int idx = java.util.Arrays.asList(months).indexOf(month) + 1;
        if(idx < 10){
            return "0" + Integer.toString(idx);
        }
        else return Integer.toString(idx);
    }
    @Override
    public void onBindViewHolder(@NonNull EventVH holder, int position) {
        int eventId = events.get(position).eventId;
        Cursor res = DB.getEventById(eventId);
        res.moveToFirst();
        holder.textView.setText(res.getString(2));
        //hide edit event button if past due time
        //get due time
        System.out.println("Result: " + res);
        String year = Integer.toString(res.getInt(11));
        String month = monthToNumber(res.getString(9));
        Integer numDate = res.getInt(10);
        String date;
        if(numDate >= 1 && numDate <= 9){
            date = "0" + Integer.toString(numDate);
        }
        else date = Integer.toString(numDate);
        Integer numTime = res.getInt(12);
        String time;
        if(numTime < 10){
            time = "0" + Integer.toString(res.getInt(12));
        }
        else time = Integer.toString(res.getInt(12));
        String str = year + "-" + month + '-' + date + ' ' + time;
        System.out.println("event string: " + str);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        LocalDateTime now = LocalDateTime.now();
        // if past edit due date, hide edit button
        if(now.isAfter(dateTime)){
            edit.setVisibility(view.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}

class EventVH extends RecyclerView.ViewHolder{
    TextView textView;
    private EventAdapter adapter;
    public void displayEvent(){
        Intent intent = new Intent(adapter.c, ViewEvent.class);
        System.out.println(adapter.events.get(getAdapterPosition()).eventId);
        intent.putExtra("eventId",
                adapter.events.get(getAdapterPosition()).eventId);
        intent.putExtra("returnPage",
                "viewMyEvents");
        intent.putExtra("userId", adapter.userId);
        adapter.c.startActivity(intent);
    }
    public void editEvent(){
        Intent intent = new Intent(adapter.c, EditEvent.class);
        System.out.println(adapter.events.get(getAdapterPosition()).eventId);
        intent.putExtra("eventId",
                adapter.events.get(getAdapterPosition()).eventId);
        intent.putExtra("userId",
                adapter.userId);
        adapter.c.startActivity(intent);
    }
    public EventVH(@NonNull View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.eventTitle);
        textView.setOnClickListener(view->{
            displayEvent();
            System.out.println("clicking my event");
        });
        //go to update event page
        itemView.findViewById(R.id.editEvent).setOnClickListener(view -> {
            //go to edit event page, start another activity
            editEvent();
        });
    }
    public EventVH linkAdapter(EventAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}