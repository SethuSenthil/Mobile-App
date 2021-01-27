package com.example.gpsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private static final int REQUEST_LOCATION = 30;

    String tag = "SETHU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(tag, "Running");

        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Log.d(tag, "Location Permissions Already Granted");
        }else{
            Log.d(tag, "Location Permissions NOT Granted");

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
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