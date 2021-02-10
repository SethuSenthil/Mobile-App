package com.example.gpsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationListener {
    TextView location_textView, distance_textView;
    Button getDistance_Button;
    LocationManager locationManager;
    Location globalLocation;
    JSONObject jsonObject;
    URL url;
    String tag = "WANDAV";
    URLConnection connection;
    InputStream stream;
    BufferedReader bufferedReader;
    String info = "";
    ArrayList<Location> coord = new ArrayList<Location>();
    String display_name;
    double overallDis;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location_textView = findViewById(R.id.addr);
        distance_textView = findViewById(R.id.distance);
       getDistance_Button = findViewById(R.id.button);

        getDistance_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (coord.size() > 0) {
                        double distance =  (coord.get(coord.size()-2).distanceTo(coord.get(coord.size()-1))*0.00062137119);

                        overallDis += distance;

                        distance_textView.setText("Travelled Distance (Ovarall) "+overallDis);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        getLocation();

    }

    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5, MainActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 100:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    getLocation();
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            globalLocation = location;
            Log.d(tag, String.valueOf(location.getLatitude()));
            new AsyncThread().execute();

            coord.add(location);
            Log.d(tag, String.valueOf(location));

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(tag, e.toString());
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public class AsyncThread extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            try {
                url = new URL("https://us1.locationiq.com/v1/reverse.php?key=pk.91acf7fa0435257013dd7955d9cab46b&lat=" + globalLocation.getLatitude() + ",&lon=" + globalLocation.getLongitude() + "&format=json");
                Log.d(tag, url.toString());
                connection = url.openConnection();
                stream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                info = "";
                if ((line = bufferedReader.readLine()) != null) {
                    do {
                        info += line;
                        Log.d(tag, info);
                    } while ((line = bufferedReader.readLine()) != null);
                }
                jsonObject = new JSONObject(info);

            } catch (Exception e) {
                Log.d(tag, e.toString());
            }

            Log.d(tag, info);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONObject jsonObject = new JSONObject(info);
                display_name = jsonObject.getString("display_name");
                location_textView.setText(display_name);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}