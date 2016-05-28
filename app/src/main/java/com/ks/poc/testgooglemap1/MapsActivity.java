package com.ks.poc.testgooglemap1;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

//        double minLat = 5.765377;
//        double maxLat = 20.126314;
//        double minLng = 97.642374;
//        double maxLng = 105.507685;
//        int maxMarkers = 400;

        // Add a markers and move the camera
//        LatLng ibm = new LatLng(13.781025, 100.545864);
//        mMap.addMarker(new MarkerOptions().position(ibm).title("IBM Thailand"));

//        LatLng phuket = new LatLng(7.860412, 98.389444);
//        mMap.addMarker(new MarkerOptions().position(phuket).title("Phuket Town"));

//        addMarker(13.781025, 100.545864,"IBM Thailand");
//        addMarker(7.860412, 98.389444,"Phuket Town");
//        addMarker(10.860412, 102.389444,"Somewhere");

//        mMap.clear();

//        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(resizeMapIcons("true_telco2",80,50));
        //BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(com.ks.poc.testgooglemap1.R.drawable.true_telco2);

//        for(int i=1; i<=maxMarkers; i++){
//           addMarker(doubleRandomInclusive(minLat,maxLat),doubleRandomInclusive(minLng,maxLng),("Shop#" + i),icon);
//        }
//
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setTiltGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Log.d(TAG," No Authority for ACCESS_FINE_LOCATION");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    999);
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.781025, 101.558450),5.6f));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 999: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    mMap.setMyLocationEnabled(true);
                }
                return;
            }
        }
    }

    public void drawMarker(View view) {
        // Do something in response to button
        EditText editText = (EditText) findViewById(R.id.editText);
        String inputText = editText.getText().toString();
        int num;
        try
        {
            num  = Integer.parseInt(inputText.trim());
        }
        catch (NumberFormatException nfe)
        {
            num = 0;
        }
        Log.d(TAG," Input: " + num);

        // Painting Markers
        double minLat = 5.765377;
        double maxLat = 20.126314;
        double minLng = 97.642374;
        double maxLng = 105.507685;
        int maxMarkers = num;

        mMap.clear();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(resizeMapIcons("true_telco2",90,60));
        for(int i=1; i<=maxMarkers; i++){
            addMarker(doubleRandomInclusive(minLat,maxLat),doubleRandomInclusive(minLng,maxLng),("Shop#" + i),icon);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.781025, 101.558450),5.6f));
    }

    public void drawCircle(View view) {
        // Do something in response to button
        EditText editText = (EditText) findViewById(R.id.editText);
        String inputText = editText.getText().toString();
        int num;
        try
        {
            num  = Integer.parseInt(inputText.trim());
        }
        catch (NumberFormatException nfe)
        {
            num = 0;
        }
        Log.d(TAG," Input: " + num);

        // Painting Circles
        double minLat = 5.765377;
        double maxLat = 20.126314;
        double minLng = 97.642374;
        double maxLng = 105.507685;
        int maxCircles = num;

        mMap.clear();
        for(int i=1; i<=maxCircles; i++){
            addCircle(doubleRandomInclusive(minLat,maxLat),doubleRandomInclusive(minLng,maxLng),5000);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.781025, 101.558450),5.6f));
    }

    public void drawHeatmap(View view) {
        // Do something in response to button
        EditText editText = (EditText) findViewById(R.id.editText);
        String inputText = editText.getText().toString();
        int num;
        try
        {
            num  = Integer.parseInt(inputText.trim());
        }
        catch (NumberFormatException nfe)
        {
            num = 0;
        }
        Log.d(TAG," Input: " + num);

        // Painting Circles
        double minLat = 5.765377;
        double maxLat = 20.126314;
        double minLng = 97.642374;
        double maxLng = 105.507685;
        int maxCircles = num;

        List<LatLng> list = null;
        list = new ArrayList<LatLng>();
        for(int i=1; i<=maxCircles; i++){
            list.add(new LatLng(doubleRandomInclusive(minLat,maxLat),doubleRandomInclusive(minLng,maxLng)));
        }
        mMap.clear();

        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();
        TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.781025, 101.558450),5.6f));
    }

    private Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    private LatLng addMarker(double lat, double lng, String markerTitle, BitmapDescriptor icon) {

        LatLng loc = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(loc).title(markerTitle).icon(icon).alpha(0.7f));
        return loc;
    }

    private LatLng addCircle(double lat, double lng, double radiusMeter) {

        LatLng loc = new LatLng(lat, lng);
        mMap.addCircle(new CircleOptions().center(loc).radius(radiusMeter).fillColor(0x2FFF0000).strokeColor(Color.TRANSPARENT).strokeWidth(0));
        return loc;
    }

    private double doubleRandomInclusive(double max, double min) {
        double r = Math.random();
        if (r < 0.5) {
            return ((1 - Math.random()) * (max - min) + min);
        }
        return (Math.random() * (max - min) + min);
    }
}
