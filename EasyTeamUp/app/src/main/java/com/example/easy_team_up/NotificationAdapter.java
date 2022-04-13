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

public class NotificationAdapter extends RecyclerView.Adapter<NotificationVH>{
    List<Notification> notifications;
    DBHelper DB;
    Context c;
    public NotificationAdapter(List<Notification> n, DBHelper DB, Context c){
        this.notifications = n;
        this.DB = DB;
        this.c = c;
    }
    @NonNull
    @Override
    public NotificationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);
        return new NotificationVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationVH holder, int position) {
//        holder.textView.setText(notifications.get(position).description);
        holder.textView.setText("hi");
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}

class NotificationVH extends RecyclerView.ViewHolder{
    TextView textView;
    private NotificationAdapter adapter;
    public NotificationVH(@NonNull View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.description);
        //go to update event page
//        itemView.findViewById(R.id.returnUser).setOnClickListener(view -> {
//            //return to user portal
//        });
    }
    public NotificationVH linkAdapter(NotificationAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}