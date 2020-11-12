package com.example.imageradioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

    RadioGroup radioGroup;

    Button button;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.textView);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.paper)
                    imageView.setImageResource(R.drawable.paper);
                if(checkedId == R.id.scissor)
                    imageView.setImageResource(R.drawable.scissor);
                if(checkedId == R.id.rock)
                    imageView.setImageResource(R.drawable.rock);

            }
        });
    }
}