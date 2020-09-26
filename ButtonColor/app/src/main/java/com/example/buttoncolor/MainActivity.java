package com.example.buttoncolor;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.colorbtn);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ranNum = (int) (Math.random() * 3);

                switch(ranNum) {
                    case 0:
                         b.setTextColor(Color.GREEN);
                        ((Button) v).setText("Greenish");
                        break;
                    case 1:
                        ((Button) v).setBackgroundColor(0xffaa7b70);
                        ((Button) v).setText("Pinkish");
                        break;

                    case 2:
                        ((Button) v).setBackgroundColor(0xff8f260c);
                        ((Button) v).setText("Red");
                        break;

                    default:
                        ((Button) v).setBackgroundColor(0xffc503ff);
                        ((Button) v).setText("Purple");
                }

            }
        });
    }



}