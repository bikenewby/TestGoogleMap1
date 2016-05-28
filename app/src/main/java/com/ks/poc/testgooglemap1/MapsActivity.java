package com.ks.poc.testgooglemap1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.781025, 101.558450),5.6f));
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
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(resizeMapIcons("true_telco2",80,50));
        for(int i=1; i<=maxMarkers; i++){
            addMarker(doubleRandomInclusive(minLat,maxLat),doubleRandomInclusive(minLng,maxLng),("Shop#" + i),icon);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.781025, 101.558450),5.6f));
    }

    private Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    private LatLng addMarker(double lat, double lng, String markerTitle, BitmapDescriptor icon) {

        LatLng loc = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(loc).title(markerTitle).icon(icon));
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
