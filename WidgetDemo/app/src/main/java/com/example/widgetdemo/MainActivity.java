package com.example.widgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

 TextView textSeek;
 SeekBar seekBar;
 TextView textSwitch;
 Switch aSwitch;
 TextView textShow;
 EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSeek = findViewById(R.id.textview);
        seekBar = findViewById((R.id.seekBar));
        textSwitch = findViewById(R.id.switchText);
        aSwitch = findViewById(R.id._switch);
        textShow = findViewById(R.id.id_editText)
        editText = findViewById(R.id.editTextTextPersonName);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {

                textSeek.setText("" + progress + "%");

            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    textSwitch.setText("ON");
                    aSwitch.setText("ON");
                }
                if(!isChecked) {
                    textSwitch.setText("OFF");
                    aSwitch.setText("OFF");
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 textShow.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}