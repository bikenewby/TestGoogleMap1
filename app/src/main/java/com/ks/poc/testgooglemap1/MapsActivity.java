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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

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
        mMap.setOnMapClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.781025, 101.558450),5.6f));
    }

    @Override
    public void onMapClick(LatLng point) {
        Toast.makeText(getApplicationContext(),"Zoom Level: " + mMap.getCameraPosition().zoom, Toast.LENGTH_LONG).show();
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

    public void drawMarker2(View view) {
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

        ArrayList<LatLng> list = null;
        try {
            list = readItems(R.raw.nw_datapoints);
        } catch (Exception e) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Problem reading list of locations: " + e.getMessage());
        }

        int maxMarkers = num;

        mMap.clear();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(resizeMapIcons("true_telco2",90,60));
        for(int i=1; i<=list.size(); i++){
            addMarker(((LatLng) list.get(i-1)).latitude,((LatLng) list.get(i-1)).longitude,("Shop#" + i),icon);
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

    public void drawCircle2(View view) {
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

        ArrayList<LatLng> list = null;
        try {
            list = readItems(R.raw.nw_datapoints);
        } catch (Exception e) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Problem reading list of locations: " + e.getMessage());
        }

        int maxCircles = num;

        mMap.clear();
//        for(int i=1; i<=maxCircles; i++){
          for(int i=1; i<=list.size(); i++){
            addCircle(((LatLng) list.get(i-1)).latitude,((LatLng) list.get(i-1)).longitude,5000);
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

        double minLat = 5.765377;
        double maxLat = 20.126314;
        double minLng = 97.642374;
        double maxLng = 105.507685;
        int maxCircles = num;
//------------------
//        List<WeightedLatLng> list = null;
//        list = new ArrayList<WeightedLatLng>();
//        for(int i=1; i<=maxCircles; i++){
//            double wght = 0D;
//            if (i < (maxCircles/3)) wght = 1.0D;
//            if ((i >= (maxCircles/3)) && (i < ((maxCircles/3)*2))) wght = 10.0D;
//            if (i >= ((maxCircles/3)*2)) wght = 20.0D;
//            list.add(new WeightedLatLng(new LatLng(doubleRandomInclusive(minLat,maxLat),doubleRandomInclusive(minLng,maxLng)),wght));
//        }
// -----------------

//        ArrayList<LatLng> list = null;
//
//        list = new ArrayList<LatLng>();
//        for(int i=1; i<=maxCircles; i++){
//            list.add(new LatLng(doubleRandomInclusive(minLat,maxLat),doubleRandomInclusive(minLng,maxLng)));
//        }
// -----------------

        ArrayList<LatLng> list = null;
        try {
            list = readItems(R.raw.nw_datapoints);
            Log.d(TAG, "Read data successfully. Size: " + list.size());
        } catch (Exception e) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Problem reading list of locations: " + e.getMessage());
        }

        if (list == null) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "List of locations: " + list.size(), Toast.LENGTH_LONG).show();

            mMap.clear();

            HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                    .data(list)
                    .radius(50)
                    .build();
            TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.781025, 101.558450), 5.6f));
        }
    }

    public void drawHeatmap2(View view) {
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

//        double minLat = 5.765377;
//        double maxLat = 20.126314;
//        double minLng = 97.642374;
//        double maxLng = 105.507685;

        double minLat = 13.5119;
        double maxLat = 13.813;
        double minLng = 100.454;
        double maxLng = 100.737;
        int maxCircles = num;
        ArrayList<LatLng> list = null;

        list = new ArrayList<LatLng>();
        for(int i=1; i<=maxCircles; i++){
            list.add(new LatLng(doubleRandomInclusive(minLat,maxLat),doubleRandomInclusive(minLng,maxLng)));
            Log.d(TAG,i + " LatLng: " + ((LatLng) list.get(i-1)).latitude + ", " + ((LatLng) list.get(i-1)).longitude);
        }
        if (list == null) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "List of locations: " + list.size(), Toast.LENGTH_LONG).show();

            mMap.clear();

            HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                    //                .weightedData(list)
                    .data(list)
//                    .radius(50)
//                    .opacity(0.4D)
                    .build();
            TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.781025, 101.558450), 5.6f));
        }
    }

    public void drawGroundOverlay(View view) {
        mMap.clear();

        LatLngBounds thBounds = new LatLngBounds(
                new LatLng(5.346111, 96.884583),       // South west corner
                new LatLng(20.749153, 105.951673));      // North east corner
        GroundOverlayOptions thMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.th_coverage))
                .transparency(0.5f) //Range: 0-1
                .positionFromBounds(thBounds);

        mMap.addGroundOverlay(thMap);

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
        mMap.addCircle(new CircleOptions().center(loc).radius(radiusMeter).fillColor(0x2F0000FF).strokeColor(Color.TRANSPARENT).strokeWidth(0));
        // Fill Color = Transparent + R + G + B
        return loc;
    }

    private double doubleRandomInclusive(double max, double min) {
        double r = Math.random();
        if (r < 0.5) {
            return ((1 - Math.random()) * (max - min) + min);
        }
        return (Math.random() * (max - min) + min);
    }

    private ArrayList<LatLng> readItems(int resource) throws Exception {

        double minLat = 5;
        double maxLat = 21;
        double minLng = 96;
        double maxLng = 106;

        ArrayList<LatLng> list = new ArrayList<LatLng>();
        InputStream inputStream = getResources().openRawResource(resource);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        inputStream.close();
      for (int i = 0; i < array.length(); i++) {
//        for (int i = 0; i < 4740; i++) {
                JSONObject object = array.getJSONObject(i);
                double lat = object.getDouble("lat");
                double lng = object.getDouble("lng");
                if ((lat >= minLat) && (lat <= maxLat) && (lng >= minLng) && (lng <= maxLng)) {
                    list.add(new LatLng(lat, lng));
                    //Log.d(TAG,i + " LatLng: " + ((LatLng) list.get(i)).latitude + ", " + ((LatLng) list.get(i)).longitude);
                } else {
                    Log.d(TAG,i + "ERROR LatLng: " + lat + ", " + lng);
                }
          }
        return list;
    }
}
