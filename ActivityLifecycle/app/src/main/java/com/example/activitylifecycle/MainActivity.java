package com.example.activitylifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b;
    int i = 0;
    public static final String INTKEY = "SOMEINT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button);
        b.setText("Value " + i);
        if(savedInstanceState != null)
         i = savedInstanceState.getInt(INTKEY);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                b.setText("Value " + i);
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("TAG", "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("TAG", "onResume");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("TAG", "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("TAG", "onDestroy");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("TAG", "onPause");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt(INTKEY, i);

    }



}