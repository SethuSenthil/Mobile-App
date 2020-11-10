package com.example.radioimagepractice;

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

        imageView = findViewById(R.id.imageView);
        //imageView.setImageResource(R.drawable.child);
        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton)
                    imageView.setImageResource(R.drawable.child);

                if(checkedId == R.id.radioButton2)
                    imageView.setImageResource(R.drawable.mando);

                if(checkedId == R.id.radioButton3)
                    imageView.setImageResource(R.drawable.kuill);
            }
        });
    }

}