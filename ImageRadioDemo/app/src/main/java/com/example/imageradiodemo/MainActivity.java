package com.example.imageradiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   ImageView imageView;
   RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView2);
        //imageView.setImageResource(R.drawable.child);
        radioGroup = findViewById(R.id.id_radio);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton2){
                    //do stuff
                    Toast myMessage = Toast.makeText(MainActivity.this, "Don't Fall Asleep!", Toast.LENGTH_SHORT);
                    myMessage.show();
                }

            }
        });
    }
}