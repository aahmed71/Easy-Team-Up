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

public class PublicAdapter extends RecyclerView.Adapter<PublicVH>{
    List<Event2> invites;
    DBHelper DB;
    Context c;
    Integer userId;
    public PublicAdapter(List<Event2> invites, DBHelper DB, Context c, Integer userId){
        this.invites = invites;
        this.DB = DB;
        this.c = c;
        this.userId = userId;
    }
    @NonNull
    @Override
    public PublicVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item2, parent, false);
        return new PublicVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicVH holder, int position) {
        holder.textView.setText(invites.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return invites.size();
    }
}

class PublicVH extends RecyclerView.ViewHolder{
    TextView textView;
    private PublicAdapter adapter;
    public void displayEvent(){
        Event2 event = adapter.invites.get(getAdapterPosition());
        Intent i = new Intent(adapter.c, EventActivity.class);
        i.putExtra("name", event.getName());
        i.putExtra("desc", event.getDesc());
        i.putExtra("address", event.getAddress());
        i.putExtra("time", event.getTime().toString());
        adapter.c.startActivity(i);
        //System.out.println(adapter.invites.get(getAdapterPosition()).eventId);
    }
    public PublicVH(@NonNull View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        textView.setOnClickListener(view->{
            displayEvent();
            System.out.println("clicking event");
        });
        itemView.findViewById(R.id.accept).setOnClickListener(view -> {
            //call database and delete
            /*
            System.out.print("hi accept");
            adapter.DB.acceptInvitation(adapter.invites.get(getAdapterPosition()));
            adapter.invites.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());*/
        });
    }
    public PublicVH linkAdapter(PublicAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
