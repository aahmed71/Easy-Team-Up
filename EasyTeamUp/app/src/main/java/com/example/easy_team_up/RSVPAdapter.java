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

public class RSVPAdapter extends RecyclerView.Adapter<RSVPvh>{
    List<Invite> rsvps;
    DBHelper DB;
    Context c;
    Integer userId;
    public RSVPAdapter(List<Invite> rsvps, DBHelper DB, Context c, Integer userId){
        this.rsvps = rsvps;
        this.DB = DB;
        this.c = c;
        this.userId = userId;
    }
    @NonNull
    @Override
    public RSVPvh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rsvpitem, parent, false);
        return new RSVPvh(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull RSVPvh holder, int position) {
        int eventId = rsvps.get(position).eventId;
        Cursor res = DB.getEventById(eventId);
        res.moveToFirst();
        holder.textView.setText(res.getString(2));
    }

    @Override
    public int getItemCount() {
        return rsvps.size();
    }
}

class RSVPvh extends RecyclerView.ViewHolder{
    TextView textView;
    private RSVPAdapter adapter;
    public void displayEvent(){
        Intent intent = new Intent(adapter.c, ViewEvent.class);
        System.out.println(adapter.rsvps.get(getAdapterPosition()).eventId);
        intent.putExtra("eventId",
                adapter.rsvps.get(getAdapterPosition()).eventId);
        intent.putExtra("userId", adapter.userId);
        adapter.c.startActivity(intent);
    }
    public RSVPvh(@NonNull View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.textRSVP);
        textView.setOnClickListener(view->{
            displayEvent();
            System.out.println("clicking rsvp event");
        });
        //deleting invitation
        itemView.findViewById(R.id.deleteRSVP).setOnClickListener(view -> {
            //call database and delete rsvp
            adapter.DB.deleteRSVP(adapter.rsvps.get(getAdapterPosition()));
            adapter.rsvps.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }
    public RSVPvh linkAdapter(RSVPAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}