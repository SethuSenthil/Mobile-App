package com.example.cookieclickerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView cookie;
    ConstraintLayout layout;
    TextView counterView;
    int counter;
    Button groguBtn;
    Context context = this;

    public static float getRandom(float[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cookie = findViewById(R.id.cookeImg);
        layout = findViewById(R.id.layout);
        counterView = findViewById(R.id.counterView);
        groguBtn = findViewById(R.id.groguBtn);



        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.15f, 1.0f, 1.15f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);



        cookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView textInCode = new TextView(context);
                textInCode.setId(View.generateViewId());
                textInCode.setText("+1");
                textInCode.setVisibility(View.INVISIBLE);
                textInCode.setTextColor(0xff8FD7E2);


                ScaleAnimation numberAnimation = new ScaleAnimation(2.0f, 3.0f, 2.0f, 3.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, 5f);
                numberAnimation.setDuration(250);
                numberAnimation.setAnimationListener(new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation arg0) {
                    }
                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }
                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        layout.removeView(textInCode);
                    }
                });


                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                textInCode.setLayoutParams(params);
                layout.addView(textInCode);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);

                scaleAnimation.setDuration(500);
                cookie.startAnimation(scaleAnimation);
                textInCode.setVisibility(View.VISIBLE);
                textInCode.startAnimation(numberAnimation);


                constraintSet.connect(textInCode.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
                constraintSet.connect(textInCode.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(textInCode.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
                constraintSet.connect(textInCode.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);

                constraintSet.setVerticalBias(textInCode.getId(), -0.5f);

                //random num for position
               float[] range = new float[]{0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f};

                constraintSet.setHorizontalBias(textInCode.getId(), getRandom(range));

                constraintSet.applyTo(layout);
                counter++;
                counterView.setText("Cookies: " + counter);
            }
        });



    }
}