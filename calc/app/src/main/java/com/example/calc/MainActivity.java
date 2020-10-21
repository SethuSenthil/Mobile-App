package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView output;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b10;
    Button b11;
    Button b12;
    Button b13;
    Button b14;
    Button b15;
    Button b16;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);

        b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b2 = findViewById(R.id.btn2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b3 = findViewById(R.id.btn3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b4 = findViewById(R.id.btn4);
        //b4.setOnClickListener(this);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b5 = findViewById(R.id.btn5);
        //b5.setOnClickListener(this);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b6 = findViewById(R.id.btn6);
        //b6.setOnClickListener(this);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b7 = findViewById(R.id.btn7);
        //b7.setOnClickListener(this);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b8 = findViewById(R.id.btn8);
        //b8.setOnClickListener(this);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b9 = findViewById(R.id.btn9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b10 = findViewById(R.id.btn10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b11 = findViewById(R.id.btn11);
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        b12 = findViewById(R.id.btn12);
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);

            }
        });

        //clean
        b13 = findViewById(R.id.btn13);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText("");

            }
        });
        b14 = findViewById(R.id.btn14);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);



            }
        });
//equal btn
        b15 = findViewById(R.id.btn15);
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                boolean noErr = true;

                //String str = "3.2*2*9.3";
                String str = prev;
                String regexDelemeters = "\\*|/|\\+|-";
                String[] splits = str.split(regexDelemeters);

                String finalResult = str;

                if(!Character.isDigit(str.charAt(0))) noErr = false;
                if(!Character.isDigit(str.charAt(str.length() - 1))) noErr = false;

                if(str.contains("++") || str.contains("--") || str.contains("//") || str.contains("**")) noErr = false;
                if(str.contains("+-") || str.contains("+/") || str.contains("+*")) noErr = false;
                if(str.contains("-+") || str.contains("-/") || str.contains("-*")) noErr = false;
                if(str.contains("/+") || str.contains("/-") || str.contains("/*")) noErr = false;
                if(str.contains("*/") || str.contains("*-") || str.contains("*+")) noErr = false;


                if(noErr){
                while(splits.length > 1) {
                    double num1 = Double.parseDouble(splits[0]);
                    double num2 = Double.parseDouble(splits[1]);

                    System.out.println("num1: " + num1);
                    System.out.println("num2: " + num2);

                    double thisResult = 0.0;
                    String delemeter = "";
                    int delemeterIndex = splits[0].length();

                    delemeter = String.valueOf(str.charAt(delemeterIndex));

                    System.out.println("delemeter: " + delemeter);

                    String calcuatedString = splits[0] + delemeter + splits[1];

                    System.out.println("calcedString: " + calcuatedString);


                    if (delemeter.equals("*"))
                        thisResult = num1 * num2;

                    if (delemeter.equals("/"))
                        thisResult = num1 / num2;

                    if (delemeter.equals("+"))
                        thisResult = num1 + num2;

                    if (delemeter.equals("-"))
                        thisResult = num1 - num2;

                    System.out.println("loopAnswer: " + thisResult);
                    finalResult = String.valueOf(thisResult);

                    System.out.println(str.substring(calcuatedString.length(), str.length()));

                    str = String.valueOf(thisResult) + str.substring(calcuatedString.length(), str.length());
                    splits = str.split("\\*|/|\\+|-");
                }

                }else{
                    finalResult = "Error";
                }

                System.out.println(finalResult);
                //clean unwanted decimals
                finalResult = finalResult.replace(".0", "");
                output.setText(finalResult);

            }
        });

        b16 = findViewById(R.id.btn16);
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String prev = (String) output.getText();
                prev += btn.getText();
                output.setText(prev);



            }
        });
    }

    @Override
    public void onClick(View v) {
         Button btn = (Button) v;
        //output.setText(btn);
    }
}