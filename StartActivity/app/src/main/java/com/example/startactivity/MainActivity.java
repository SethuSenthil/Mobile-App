package com.example.startactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView displayResultText;
    public static final int CODE_START = 1;
    public static final String INTENTCODE_EDITTEXT = "KEY1";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_START && resultCode == RESULT_OK){
            displayResultText.setText(data.getStringExtra(INTENTCODE_EDITTEXT));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.button);
        displayResultText = findViewById(R.id.id_text_return_value);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, Number.class);
                startIntent.putExtra("TEST", "This is a test");
                startActivityForResult(startIntent, CODE_START);
            }
        });
    }


}