package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    Button changeSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1 = findViewById(R.id.btn1); //red
        b2 =  findViewById(R.id.btn2); //blue
        changeSize = findViewById(R.id.sizeBtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setTextColor(Color.RED);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setTextColor(Color.BLUE);
                b1.setText(b2.getText());
            }
        });

        changeSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              b1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f);
              b2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f);
            }
        });
    }
}