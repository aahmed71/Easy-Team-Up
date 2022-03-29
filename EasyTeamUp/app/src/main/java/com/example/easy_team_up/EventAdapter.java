package com.example.easy_team_up;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventVH>{
    List<Event> events;
    DBHelper DB;
    Context c;
    public EventAdapter(List<Event> events, DBHelper DB, Context c){
        this.events = events;
        this.DB = DB;
        this.c = c;
    }
    @NonNull
    @Override
    public EventVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myeventitem, parent, false);
        return new EventVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull EventVH holder, int position) {
        int eventId = events.get(position).eventId;
        Cursor res = DB.getEventById(eventId);
        res.moveToFirst();
        holder.textView.setText(res.getString(2));
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
        adapter.c.startActivity(intent);
    }
    public void editEvent(){
        Intent intent = new Intent(adapter.c, EditEvent.class);
        System.out.println(adapter.events.get(getAdapterPosition()).eventId);
        intent.putExtra("eventId",
                adapter.events.get(getAdapterPosition()).eventId);
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
//            //call database and edit event by event id
//            adapter.DB.updateMyEvent(adapter.events.get(getAdapterPosition()).eventId, "New Event");
//            adapter.events.remove(getAdapterPosition());
//            adapter.notifyItemRemoved(getAdapterPosition());
            //go to edit event page, start another activity
            editEvent();
        });
    }
    public EventVH linkAdapter(EventAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}