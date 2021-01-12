package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {
    URL url;
    URLConnection connection;
    InputStream stream;
    BufferedReader bufferedReader;
    String info = "";
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            try {
                url = new URL("http://api.openweathermap.org/data/2.5/weather?q=monmouth%20junction&appid=c4ec1292c4da1327e669ea1e143e73e2");
                connection = url.openConnection();
                stream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                while ((line=bufferedReader.readLine()) != null) {
                    info +=line;
                }
                jsonObject = new JSONObject(info);

            } catch (Exception e) {
                Log.d("TAG",e.toString());
            }

            Log.d("TAG", info);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

           /* try {
                JSONObject jsonObject = new JSONObject(info);
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
    }

}

