package com.example.listviewexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     ListView listView;
     ArrayList<String>  arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview_holder);
        ListViewAdapter adapter;
        listView.setAdapter(this, R.layout.adapter );
    }

    public class ListViewAdapter extends ArrayAdapter<String> {
        Context mainContext;
        int xml;
        List<String> list;

        public ListViewAdapter(@NonNull Context context, int resources, @NonNull List<String> objects){
            super(context, resources, objects);

            mainContext = context;
            xml = resources;
            list = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterLayout = inflater.inflate(xml, null);
            TextView textView = adapterLayout.findViewById(R.id.id_adapter_textview);
            Button button = adapterLayout.findViewById(R.id.id_adapterbutton);




                    return super.getView(position, convertView, parent);
        }

    }
}