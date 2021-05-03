package com.example.fragmentdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View v = inflater.inflate(R.layout.fragment_blank, container, false);
     Button b = v.findViewById(R.id.fragment_button);
     b.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             b.setText("TEXT");
         }
     });
     return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    public interface ReciveString{
        public void receive (String str);
    }
}