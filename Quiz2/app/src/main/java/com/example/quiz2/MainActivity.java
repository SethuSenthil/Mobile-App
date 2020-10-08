package com.example.quiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b1;
    TextView t1;
    Button b2;
    TextView t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.btn1);  //top btn
        b2 = findViewById(R.id.button3);  //top btn
        t1 = findViewById(R.id.txt1);  //top txt
        t2 = findViewById(R.id.textView3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(t1.getText() == "Not Clicked"){
                   t1.setText("Clicked");
               }else {
                   t1.setText("Not Clicked");
               }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t2.getText() == "Not Clicked"){
                    t2.setText("Clicked");
                }else {
                    t2.setText("Not Clicked");
                }
            }
        });

    }

    public void myButtonClick(View v){
        //b.setText("clicked");
        ((Button) v).setText("hi");


    }

}