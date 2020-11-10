package com.example.quiz3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     SeekBar seekBar1;
     SeekBar seekBar2;
     TextView seekBar1Text;
     TextView seekBar2Text;
     Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init view
        setContentView(R.layout.activity_main);
        seekBar1 = findViewById(R.id.seekBar1);
        seekBar1Text = findViewById(R.id.seekBar1Text);
        seekBar2 = findViewById(R.id.seekBar2);
        seekBar2Text = findViewById(R.id.seekBar2Text);
        aSwitch = findViewById(R.id.switch1);

        aSwitch.setClickable(false);

        seekBar1.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {

               seekBar1Text.setText("SeekBar 1: " + progress + "%");

                if(progress == seekBar2.getProgress()) {
                    if(!aSwitch.isChecked())
                        aSwitch.toggle();
                }else{
                    if(aSwitch.isChecked())
                        aSwitch.toggle();
                }

            }
        }));


        seekBar2.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {

                seekBar2Text.setText("SeekBar 2: " + progress + "%");

                if(progress == seekBar1.getProgress()) {
                    if(!aSwitch.isChecked())
                       aSwitch.toggle();
                }else{
                    if(aSwitch.isChecked())
                        aSwitch.toggle();
                }


            }
        }));

    }
}