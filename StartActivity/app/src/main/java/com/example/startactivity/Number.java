package com.example.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Number extends AppCompatActivity {

    Button finish;
    TextView sentValue;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        finish = findViewById(R.id.id_button_finish);
        sentValue = findViewById(R.id.id_text_test);
        editText = findViewById(R.id.id_edit);

        sentValue.setText(getIntent().getStringExtra("TEST"));

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendback = new Intent();
                sendback.putExtra(MainActivity.INTENTCODE_EDITTEXT, editText.getText().toString());
                setResult(RESULT_OK, sendback);
                finish();
            }
        });
    }
}