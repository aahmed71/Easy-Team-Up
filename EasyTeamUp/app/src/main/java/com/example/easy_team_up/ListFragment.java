package com.example.easy_team_up;

import static com.example.easy_team_up.DBHelper.getPublicEvents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easy_team_up.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        List<Event> items = new ArrayList<>();
        items = getPublicEvents();



        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PublicAdapter adapter = new PublicAdapter(items);
        recyclerView.setAdapter(adapter);

        return rootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}