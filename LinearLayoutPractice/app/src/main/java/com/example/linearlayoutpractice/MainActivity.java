package com.example.linearlayoutpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
   View parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeBG(View v) {
        String getColor = (String) ((Button) v).getText();
        parent = findViewById(R.id.parentView);
        switch(getColor.toUpperCase()) {
            case "CYAN":
                parent.setBackgroundColor(Color.CYAN);
                break;
            case "GRAY":
                parent.setBackgroundColor(Color.GRAY);
                break;

            case "MAGENTA":
                parent.setBackgroundColor(Color.MAGENTA);
                break;
        }

    }

    public void changeBtnTxtColor(View v) {
        String getColor = (String) ((Button) v).getText();
        Button btn7 = (Button)findViewById(R.id.button7);
        Button btn8 = (Button)findViewById(R.id.button8);
        Button btn9 = (Button)findViewById(R.id.button9);

        switch(getColor.toUpperCase()) {
            case "RED":

                btn7.setTextColor(Color.RED);
                btn8.setTextColor(Color.RED);
                btn9.setTextColor(Color.RED);

                break;
            case "BLUE":

                btn7.setTextColor(Color.BLUE);
                btn8.setTextColor(Color.BLUE);
                btn9.setTextColor(Color.BLUE);

                break;

            case "GREEN":

                btn7.setTextColor(Color.GREEN);
                btn8.setTextColor(Color.GREEN);
                btn9.setTextColor(Color.GREEN);

                break;
        }

    }
}