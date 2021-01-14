package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;


public class MainActivity extends AppCompatActivity {
    URL url;
    URLConnection connection;
    InputStream stream;
    BufferedReader bufferedReader;
    String info = "";
    JSONObject jsonObject;
    int numberOfCities = 3;

    //my location, for testing purposes
    double lat = 40.399999980505676;
    double lon = -74.51244475550735;


    String tag = "SETHU";
    String apiKey = "c4ec1292c4da1327e669ea1e143e73e2";


    public double getFarenheit(double kelvin) {
        double f = (((kelvin - 273) * 9 / 5) + 32);
        return f;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "INIT");

        (new AsyncThread()).execute();
    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            try {
                url = new URL("https://api.openweathermap.org/data/2.5/find?"+ "lat="+ lat + "&lon=" + lon +"&appid=" + apiKey + "&cnt=" + numberOfCities);
                Log.d(tag,url.toString());

                connection = url.openConnection();
                stream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                while ((line=bufferedReader.readLine()) != null) {
                    info +=line;
                }
                jsonObject = new JSONObject(info);

            } catch (Exception e) {
                Log.d(tag,e.toString());
            }

            //Log.d(tag, info);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                JSONObject jsonObject = new JSONObject(info);
                JSONArray cities = jsonObject.getJSONArray("list");

                for (int i = 0; i < cities.length(); i++) {
                   Log.d(tag, cities.getJSONObject(i).toString());
                   JSONObject thisCity = cities.getJSONObject(i);

                   JSONObject weatherInfo = thisCity.getJSONArray("weather").getJSONObject(0);


                    String name = thisCity.getString("name");
                    Log.d(tag, name);

                    Double temp = thisCity.getJSONObject("main").getDouble("temp");
                    temp = getFarenheit(temp);
                    Log.d(tag, temp.toString());


                    String des = weatherInfo.getString("description");
                    Log.d(tag, des);

                   String icon = weatherInfo.getString("icon");
                    Log.d(tag, icon);

                    int timeStamp = thisCity.getInt("dt");
                    Log.d(tag, timeStamp + "");

                    //long epoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(timeStamp + "").getTime() / 1000;
                   // Log.d(tag, epoch + "");


                }


                
            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }

        }
    }

}

