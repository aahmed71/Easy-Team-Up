package com.example.easy_team_up;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PublicAdapter extends RecyclerView.Adapter<PublicVH>{
    List<Event> items;
    public PublicAdapter(List<Event> items){
        this.items = items;
    }
    @NonNull
    @Override
    public PublicVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new PublicVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicVH holder, int position) {
        holder.textView.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class PublicVH extends RecyclerView.ViewHolder{
    TextView textView;
    private PublicAdapter adapter;
    public PublicVH(@NonNull View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        itemView.findViewById(R.id.view).setOnClickListener(view -> {
            Event event = adapter.items.get(getAdapterPosition());
            Context c = itemView.getContext();
            Intent i = new Intent(c, EventActivity.class);
            i.putExtra("name", event.getName());
            i.putExtra("desc", event.getDesc());
            i.putExtra("address", event.getAddress());
            i.putExtra("time", event.getEventStart());
            c.startActivity(i);
        });
    }
    public PublicVH linkAdapter(PublicAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
