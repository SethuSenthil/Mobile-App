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
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    URL url;
    JSONObject jsonObject;
    Location globalLocation;
    String tag = "WANDAV";
    String apiKey = "pk.d13faca0c15908b09692b0e4d21c968a";
    InputStream stream;
    BufferedReader bufferedReader;
    URLConnection connection;
    String info = "";
    ArrayList<Location> coord = new ArrayList<Location>();
    ArrayList<Date> timeTracker = new ArrayList<Date>();
    String display_name;
    long overAllTime;
    long currentTime;
    TextView timeText, location_textView, distance_textView;
    double overallDis;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        distance_textView = findViewById(R.id.distance);
        location_textView = findViewById(R.id.addr);
        timeText = findViewById(R.id.timetravl);

        currentTime = Calendar.getInstance().getTimeInMillis();

        Log.d(tag, "curr" + currentTime);


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
                    getLocation();
                } else {
                    //
                }
                return;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            globalLocation = location;
            Log.d(tag, String.valueOf(location.getLatitude()));
            new AsyncThread().execute();

            coord.add(location);
            Log.d(tag, String.valueOf(location));

            if (coord.size() > 0) {
                double distance =  (coord.get(coord.size()-2).distanceTo(coord.get(coord.size()-1))*0.00062137119);

                overallDis += distance;

                distance_textView.setText("Travelled Distance (Overall in Miles) "+ Math.round(overallDis * 100.0)/ 100.0);

                long endTime = Calendar.getInstance().getTimeInMillis();
                Log.d(tag, "endFloat" + endTime);

                float seconds = TimeUnit.MILLISECONDS.toSeconds((endTime - currentTime));
                Log.d(tag, "endtime" + endTime);
                Log.d(tag, "seconds" + (endTime - currentTime));
                overAllTime += seconds;

                timeText.setText("Time Traveled (Overall in Seconds)" + overAllTime);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(tag, e.toString());
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            try {
                url = new URL("https://us1.locationiq.com/v1/reverse.php?key="+ apiKey + "&lat=" + globalLocation.getLatitude() + ",&lon=" + globalLocation.getLongitude() + "&format=json");
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