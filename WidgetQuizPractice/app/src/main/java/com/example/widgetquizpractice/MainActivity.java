package com.example.widgetquizpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    TextView text;
    EditText input;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switch2);
        text = findViewById(R.id.textView);
        input = findViewById(R.id.editTextTextPersonName2);
        seekBar = findViewById(R.id.seekBar2);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    seekBar.setEnabled(true);
                else
                    seekBar.setEnabled(true);
            }
        });

        seekBar.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {

                text.setTextSize(progress);

            }
        }));

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String color = s.toString();
                if(color.equals("blue"))
                    text.setTextColor(Color.BLUE);
                if(color.equals("green"))
                    text.setTextColor(Color.GREEN);

            }
        });


    }
}