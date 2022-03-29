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
    List<Invite> invites;
    DBHelper DB;
    Context c;
    public PublicAdapter(List<Invite> invites, DBHelper DB, Context c){
        this.invites = invites;
        this.DB = DB;
        this.c = c;
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
        holder.textView.setText(invites.get(position).title);
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
        Intent intent = new Intent(adapter.c, ViewEvent.class);
        System.out.println(adapter.invites.get(getAdapterPosition()).eventId);
        intent.putExtra("eventId",
                adapter.invites.get(getAdapterPosition()).eventId);
        adapter.c.startActivity(intent);
    }
    public PublicVH(@NonNull View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        textView.setOnClickListener(view->{
            displayEvent();
            System.out.println("clicking event");
        });
    }
    public PublicVH linkAdapter(PublicAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
