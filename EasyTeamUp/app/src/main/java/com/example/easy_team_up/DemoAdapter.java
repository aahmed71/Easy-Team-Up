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

public class DemoAdapter extends RecyclerView.Adapter<DemoVH>{
    List<Event> items;
    public DemoAdapter(List<Event> items){
        this.items = items;
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
        holder.textView.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class DemoVH extends RecyclerView.ViewHolder{
    TextView textView;
    private DemoAdapter adapter;
    public DemoVH(@NonNull View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        itemView.findViewById(R.id.view).setOnClickListener(view -> {
            Event event = adapter.items.get(getAdapterPosition());
            Context c = itemView.getContext();
            Intent i = new Intent(c, EventActivity.class);
            i.putExtra("name", event.getName());
            i.putExtra("desc", event.getDesc());
            i.putExtra("address", event.getAddress());
            i.putExtra("time", event.getTime().toString());
            c.startActivity(i);
        });
    }
    public DemoVH linkAdapter(DemoAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
