package com.example.manageinvitations;
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
    public DemoAdapter(List<Invite> invites, DBHelper DB){
        this.invites = invites;
        this.DB = DB;
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
        holder.textView.setText(invites.get(position).title);
    }

    @Override
    public int getItemCount() {
        return invites.size();
    }
}

class DemoVH extends RecyclerView.ViewHolder{
    TextView textView;
    private DemoAdapter adapter;
    public DemoVH(@NonNull View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        //deleting invitation
        itemView.findViewById(R.id.delete).setOnClickListener(view -> {
            //call database and delete
            System.out.print("hi delete");
//            Integer inviteId = adapter.invites.get(getAdapterPosition()).inviteId;
            adapter.DB.deleteInvitation(adapter.invites.get(getAdapterPosition()));
            adapter.invites.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
        //accepting invitation
    }
    public DemoVH linkAdapter(DemoAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
