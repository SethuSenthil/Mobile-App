package com.example.radioimagequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView selectedText;
    TextView userScore;
    TextView pcScore;
    Button goButton;
    ImageView imageView;
    RadioButton one;
    RadioButton two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        selectedText = findViewById(R.id.selectedText);
        userScore = findViewById(R.id.userScore);
        pcScore = findViewById(R.id.pcScore);
        goButton = findViewById(R.id.goButton);
        imageView = findViewById(R.id.imageView);
        one = findViewById(R.id.radioButton);
        two = findViewById(R.id.radioButton2);


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedText.getText().equals("Select a number")) {

                //random num 1 -2
                int ranNum = (Math.random() <= 0.5) ? 1 : 2;  //1 or 2

                if(ranNum == 1){

                    imageView.setImageResource(R.drawable.one);

                }else{

                    imageView.setImageResource(R.drawable.two);

                }

                    if (selectedText.getText().equals("You Selected a One")) {
                        //one.setChecked(false);
                        if (ranNum == 1) {
                            String newScore = Integer.toString(Integer.parseInt((String) userScore.getText()) + 1);
                            userScore.setText(newScore);
                            Toast myMessage = Toast.makeText(MainActivity.this, "Win!", Toast.LENGTH_SHORT);
                            myMessage.show();

                        } else {
                            String newScore = Integer.toString(Integer.parseInt((String) pcScore.getText()) + 1);
                            pcScore.setText(newScore);
                            Toast myMessage = Toast.makeText(MainActivity.this, "Lose!", Toast.LENGTH_SHORT);
                            myMessage.show();
                        }

                    } else {
                        //two.setChecked(false);
                        if (ranNum == 2) {
                            String newScore = Integer.toString(Integer.parseInt((String) userScore.getText()) + 1);
                            userScore.setText(newScore);
                            Toast myMessage = Toast.makeText(MainActivity.this, "Win!", Toast.LENGTH_SHORT);
                            myMessage.show();

                        } else {
                            String newScore = Integer.toString(Integer.parseInt((String) pcScore.getText()) + 1);
                            pcScore.setText(newScore);
                            Toast myMessage = Toast.makeText(MainActivity.this, "Lose!", Toast.LENGTH_SHORT);
                            myMessage.show();
                        }
                    }
                    //selectedText.setText("Select a number");


                }else{
                    Toast myMessage = Toast.makeText(MainActivity.this, "Select a number first!", Toast.LENGTH_SHORT);
                    myMessage.show();
                }



            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton){
                    //1
                    selectedText.setText("You Selected a One");
                }

                if(checkedId == R.id.radioButton2){
                    //2
                    selectedText.setText("You Selected a Two");
                }

            }
        });


    }
}