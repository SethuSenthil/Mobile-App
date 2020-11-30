package com.example.layoutexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
 Spinner spinner;
 Button btn;
 TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            textview = findViewById(R.id.textView);
            textview.setText("hello");
        }


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            btn = findViewById(R.id.button);
            btn.setText("ay yo");
        }

    }
}