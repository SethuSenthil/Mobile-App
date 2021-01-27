package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity{
    JSONObject jsonObject;
    JSONArray cities;
    int numberOfCities = 3;
    Button city1;
    Button city2;
    Button city3;
    Button latlong;
    ImageView imageView;
    TextView tempTxt;
    TextView city;
    URL url;
    URLConnection connection;
    InputStream stream;
    BufferedReader bufferedReader;
    String info = "";
    TextView timeText;
    TextView dateText;
    TextView weatherDes;
    EditText latText;
    EditText longText;



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

        city1 = findViewById(R.id.btn1);
        city1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    setData(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        city2 = findViewById(R.id.btn2);

        city2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    setData(1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        city3 = findViewById(R.id.btn3);

        city3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    setData(2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        longText = findViewById(R.id.longt);
        latText = findViewById(R.id.lat);

        latlong = findViewById(R.id.latlongbtn);
        latlong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               if(longText.getText().length() > 1 && latText.getText().length() > 1){
                   //check if edit texts are not empty
                   (new AsyncThread()).execute();
                   lat = Double.valueOf(latText.getText().toString());
                   lon = Double.valueOf(longText.getText().toString());
               }
            }
        });



        tempTxt = findViewById(R.id.textView);
        city = findViewById(R.id.des);
        timeText = findViewById(R.id.time);
        dateText = findViewById(R.id.date);
        weatherDes = findViewById(R.id.wdes);

        imageView = findViewById(R.id.imageView);

        Log.d(tag, "INIT");

        (new AsyncThread()).execute();
    }

    public void setData(int i) throws JSONException {

        Log.d(tag, cities.getJSONObject(i).toString());
        JSONObject thisCity = cities.getJSONObject(i);

        JSONObject weatherInfo = thisCity.getJSONArray("weather").getJSONObject(0);

        String name = thisCity.getString("name");
        Log.d(tag, name);
        city.setText("\uD83D\uDCCD" + name);
        city.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + name));
                startActivity(browserIntent);
            }
        });

        Double temp = thisCity.getJSONObject("main").getDouble("temp");
        temp = getFarenheit(temp);
        Log.d(tag, temp.toString());
        tempTxt.setText(Math.round(temp) + "°F");



        String des =  weatherInfo.getString("description");
        weatherDes.setText(des.toUpperCase());
        Log.d(tag, des);

        String icon = weatherInfo.getString("icon");
        Log.d(tag, icon);
        Picasso.get().load("http://openweathermap.org/img/wn/" + icon +"@4x.png").into(imageView);


        int timeStamp = thisCity.getInt("dt");
        Log.d(tag, timeStamp + "");

        Date a = new Date(timeStamp * 1000L);
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM EEEE dd yyyy hh:mm");
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String date = DATE_FORMAT.format(a);

        String time = date.split(" ")[4];
        String dateTexter = date.split(" ")[1] + " , " + date.split(" ")[0] + " " + date.split(" ")[2];

        //remove 0
        if(time.substring(0, 1).equals("0")){
            time = time.substring(1);
        }
        timeText.setText("\uD83D\uDD53 " + time);
        Log.d(tag, time);

        dateText.setText(dateTexter);



         int orange = 0xFFEC6E4C;
         int defaultPurp = 0xFF5600E8;

         //reset color
        city1.setBackgroundColor(defaultPurp);
        city2.setBackgroundColor(defaultPurp);
        city3.setBackgroundColor(defaultPurp);

        if(i == 0){
            city1.setBackgroundColor(orange);
        }else if(i == 1){
            city2.setBackgroundColor(orange);
        }else if(i == 2){
            city3.setBackgroundColor(orange);
        }
    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            try {
                url = new URL("https://api.openweathermap.org/data/2.5/find?" + "lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&cnt=" + numberOfCities);
                Log.d(tag, url.toString());

                connection = url.openConnection();
                stream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));

                String line = "";
                info = "";

                while ((line = bufferedReader.readLine()) != null) {
                    info += line;
                }

                jsonObject = new JSONObject(info);

            } catch (Exception e) {
                Log.d(tag, e.toString());
            }

            //Log.d(tag, info);
            return null;
        }

        @Override
        protected void onPostExecute(Void dumbVoid) {
            super.onPostExecute(dumbVoid);

            try {
                JSONObject jsonObject = new JSONObject(info);
                cities = jsonObject.getJSONArray("list");

                city1.setText(cities.getJSONObject(0).getString("name"));
                city2.setText(cities.getJSONObject(1).getString("name"));
                city3.setText(cities.getJSONObject(2).getString("name"));

                setData(0);

                
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}

