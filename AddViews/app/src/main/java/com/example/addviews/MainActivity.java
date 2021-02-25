package com.example.addviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);

        TextView textInCode = new TextView(this);
        textInCode.setId(View.generateViewId());
        textInCode.setText("Hello Again");

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        textInCode.setLayoutParams(params);
        layout.addView(textInCode);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        constraintSet.connect(textInCode.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
        constraintSet.connect(textInCode.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(textInCode.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
        constraintSet.connect(textInCode.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);

         constraintSet.setVerticalBias(textInCode.getId(), 0.75f);
         constraintSet.setHorizontalBias(textInCode.getId(), 0.5f);

         constraintSet.applyTo(layout);
    }
}