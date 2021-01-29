package com.example.gpsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity  {
    Location location;
    double longitude;
    double latitude;
    String userAddress;
    TextView fullAddr;
    TextView longTV;
    TextView latTV;

    String tag = "MANDOIDK";
    private static final int REQUEST_LOCATION = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fullAddr = findViewById(R.id.addr);
        longTV = findViewById(R.id.longg);
        latTV = findViewById(R.id.lat);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            //ask for permissions and stuff
            Log.d(tag, "Location Permissions NOT Granted");

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        }else{
            Log.d(tag, "Location Permissions Already Granted");
        }

        try {

            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        } catch (Exception e) {
            e.printStackTrace();
        }

        latitude = 0.0;
        longitude = 0.0;

        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        longTV.setText("Long: " + longitude);
        latTV.setText("Lat: " + latitude);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);

        try {

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            userAddress = addresses.get(0).getAddressLine(0);
            fullAddr.setText(userAddress);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode == REQUEST_LOCATION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //the method to get location
                Log.d(tag,"Permissions granted!");
            }else{
                Log.d(tag, "Permission rejected granted");
                Toast.makeText(this, "Permission was not granted", Toast.LENGTH_SHORT).show();
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}