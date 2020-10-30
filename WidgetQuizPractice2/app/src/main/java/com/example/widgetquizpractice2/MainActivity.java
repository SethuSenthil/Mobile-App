package com.example.widgetquizpractice2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Switch one;
    Switch two;
    Switch three;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         one = findViewById(R.id.switch1);
         two = findViewById(R.id.switch2);
         three = findViewById(R.id.switch2);
         text = findViewById(R.id.textView);


         one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                 if(isChecked && two.isChecked() && three.isChecked())
                     text.setTextColor(Color.GREEN);
                 else if(isChecked && three.isChecked())
                     text.setTextColor(Color.RED);
                 else
                     text.setTextColor(Color.BLACK);
             }
         });


        two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked && one.isChecked() && three.isChecked())
                    text.setTextColor(Color.GREEN);
                else if(one.isChecked() && three.isChecked())
                    text.setTextColor(Color.RED);
                else
                    text.setTextColor(Color.BLACK);

            }
        });


        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked && two.isChecked() && one.isChecked())
                    text.setTextColor(Color.GREEN);
                else if(one.isChecked() && isChecked)
                    text.setTextColor(Color.RED);
                else
                    text.setTextColor(Color.BLACK);

            }
        });
    }
}