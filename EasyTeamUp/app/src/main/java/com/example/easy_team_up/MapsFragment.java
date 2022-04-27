package com.example.easy_team_up;

import static com.example.easy_team_up.DBHelper.getEvents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsFragment extends Fragment {
    //private ArrayList<Event> events;
    private HashMap<Marker, Event> eventMap;
    private Geocoder geocoder;
    private GoogleMap map;
    private FirebaseAuth mAuth;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            eventMap = new HashMap<Marker, Event>();
            geocoder = new Geocoder(getContext());
            mAuth = FirebaseAuth.getInstance();
            googleMap.clear();
            getEvents(new GetEventsCallback() {
                @Override
                public void onCallback(ArrayList<Event> events) {
                    for(Event event : events){
                        if(event.getPublicFlag() && !event.getUserID().equals(mAuth.getCurrentUser().getEmail())){
                            ArrayList<Address> addresses = null;
                            try {
                                addresses = (ArrayList<Address>) geocoder.getFromLocationName(event.getAddress(), 50);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            double longitude = addresses.get(0).getLongitude();
                            double latitude = addresses.get(0).getLatitude();
                            LatLng coords = new LatLng(latitude, longitude);
                            MarkerOptions mo = new MarkerOptions().position(coords);
                            Marker m = googleMap.addMarker(mo);
                            eventMap.put(m, event);
                        }
                    }
                }
            });
            // Iterate through events and place marker


            googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(34.018360, -118.286331),14.0f) );
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    // Open up event details
                    Event event = eventMap.get(marker);
                    Intent i = new Intent(getActivity(), EventActivity.class);
                    i.putExtra("name", event.getName());
                    i.putExtra("desc", event.getDesc());
                    i.putExtra("address", event.getAddress());
                    i.putExtra("time", event.getEventStart());
                    startActivity(i);
                    return true;

                }
            });

        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}