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

public class DemoAdapter extends RecyclerView.Adapter<DemoVH>{
    List<Invite> invites;
    DBHelper DB;
    Context c;
    Integer userId;
    public DemoAdapter(List<Invite> invites, DBHelper DB, Context c, Integer userId){
        this.invites = invites;
        this.DB = DB;
        this.c = c;
        this.userId = userId;
    }
    @NonNull
    @Override
    public DemoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new DemoVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoVH holder, int position) {
        Integer eventId = invites.get(position).eventId;
        Cursor event = DB.getEventById(eventId);
        event.moveToFirst();
        holder.textView.setText(event.getString(2));
    }

    @Override
    public int getItemCount() {
        return invites.size();
    }
}

class DemoVH extends RecyclerView.ViewHolder{
    TextView textView;
    private DemoAdapter adapter;
    public void displayEvent(){
        Intent intent = new Intent(adapter.c, ViewEvent.class);
        System.out.println(adapter.invites.get(getAdapterPosition()).eventId);
        intent.putExtra("eventId",
                adapter.invites.get(getAdapterPosition()).eventId);
        intent.putExtra("userId", adapter.userId);
        adapter.c.startActivity(intent);
    }
    public DemoVH(@NonNull View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        textView.setOnClickListener(view->{
            displayEvent();
            System.out.println("clicking event");
        });
        //deleting invitation
        itemView.findViewById(R.id.delete).setOnClickListener(view -> {
            //call database and delete
            System.out.print("hi delete");
            adapter.DB.deleteInvitation(adapter.invites.get(getAdapterPosition()));
            adapter.invites.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
        //accepting invitation
        itemView.findViewById(R.id.accept).setOnClickListener(view -> {
            //call database and delete
            System.out.print("hi accept");
            adapter.DB.acceptInvitation(adapter.invites.get(getAdapterPosition()));
            adapter.invites.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }
    public DemoVH linkAdapter(DemoAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
