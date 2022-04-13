package com.example.easy_team_up;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.easy_team_up.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    HashMap<Marker, Event2> eventMap = new HashMap<Marker, Event2>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<Event2> events = new ArrayList<Event2>();
        events.add(new Event2("Study", "Studying for midterms.",
                "3115 Orchard Avenue, Los Angeles, CA", LocalTime.of(10,43), 1 ,1));
        events.add(new Event2("Birthday", "Bob's birthday.",
                "3335 S Figueroa St, Los Angeles, CA", LocalTime.of(9,50), 1, 1));

        Geocoder geocoder = new Geocoder(this);
        // Iterate through events and place marker
        for(Event2 event : events){
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

        googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(34.018360, -118.286331),14.0f) );
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                // Open up event details
                viewEvent(marker);
                return true;

            }
        });
    }

    public void viewEvent(Marker m){
        Event2 event = eventMap.get(m);
        Intent i = new Intent(this, EventActivity.class);
        i.putExtra("name", event.getName());
        i.putExtra("desc", event.getDesc());
        i.putExtra("address", event.getAddress());
        i.putExtra("time", event.getTime().toString());
        startActivity(i);
    }
}