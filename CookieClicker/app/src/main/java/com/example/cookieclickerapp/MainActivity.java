package com.example.cookieclickerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    ImageView cookie;
    ConstraintLayout layout;
    TextView counterView;
    int grogus;
    Button groguBtn;
    AtomicInteger counter = new AtomicInteger(0);
    Context context = this;
    String tag = "GROGUUU";

    public static float getRandom(float[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public void syncText() {
        counterView.setText("Cookies: " + counter.get());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cookie = findViewById(R.id.cookeImg);
        layout = findViewById(R.id.layout);
        counterView = findViewById(R.id.counterView);
        groguBtn = findViewById(R.id.groguBtn);

        new AsyncThread().execute();

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.15f, 1.0f, 1.15f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);

        groguBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.getAndAdd(-20);
                grogus++;

                counterView.setText("Cookies: " + counter);

                if(counter.get() >= 20){
                    groguBtn.setEnabled(true);
                }else{
                    groguBtn.setEnabled(false);
                }
            }
        });


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

                //render Grogus

                for (int i = 0; i < grogus; i++) {
                    ImageView groguImg = new ImageView(context);
                    groguImg.setId(View.generateViewId());
                    groguImg.setImageResource(R.drawable.grogu);
                    groguImg.getLayoutParams().height = 50;
                    constraintSet.connect(groguImg.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
                    constraintSet.connect(groguImg.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
                    constraintSet.connect(groguImg.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
                    constraintSet.connect(groguImg.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
                    constraintSet.setVerticalBias(groguImg.getId(), -0.5f);
                    constraintSet.setHorizontalBias(textInCode.getId(), 0.5f);
                    groguImg.setVisibility(View.VISIBLE);
                }

                //random num for position
               float[] range = new float[]{0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f};

                constraintSet.setHorizontalBias(textInCode.getId(), getRandom(range));

                constraintSet.applyTo(layout);
                counter.getAndAdd(1);
                counterView.setText("Cookies: " + counter.get());

                if(counter.get() >= 20){
                    groguBtn.setEnabled(true);
                }else{
                    groguBtn.setEnabled(false);
                }
            }
        });


    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {

        private final static int INTERVAL = 2000;
        Handler mHandler = new Handler();

        Runnable mHandlerTask = new Runnable()
        {
            @Override
            public void run() {
                counter.getAndIncrement();
                syncText();
                mHandler.postDelayed(mHandlerTask, INTERVAL);
            }
        };

        void startRepeatingTask()
        {
            mHandlerTask.run();
        }

        void stopRepeatingTask()
        {
            mHandler.removeCallbacks(mHandlerTask);
        }


        @Override
        protected Void doInBackground(String... voids) {
            try {
                startRepeatingTask();
            } catch (Exception e) {
                Log.d(tag, e.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}