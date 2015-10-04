package com.example.swaroop_sa2004.hackru;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Uri uri = Uri.parse("http://45.79.148.237:3000");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_map2);
        //setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mMap == null) {
            return;
        }
        // Initialize map options. For example:
        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
