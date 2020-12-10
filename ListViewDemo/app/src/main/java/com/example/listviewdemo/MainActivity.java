package com.example.listviewdemo;

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
    ArrayList<Karacter> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_main_listview);

        arrayList = new ArrayList<>();
        arrayList.add(new Karacter("Hello", "Hello", "Des"));
        arrayList.add(new Karacter("Hello", "Hello", "Des"));
        arrayList.add(new Karacter("Hello", "Hello", "Des"));
        arrayList.add(new Karacter("Hello", "Hello", "Des"));
        arrayList.add(new Karacter("Hello", "Hello", "Des"));
        arrayList.add(new Karacter("Hello", "Hello", "Des"));

        listViewAdapter adapter = new listViewAdapter(this, R.layout.adapter_listview, arrayList);
        listView.setAdapter(adapter);

    }

    public class Karacter {
        public String url;
        public String name;
        public String des;

        public Karacter( String url, String name, String des){
            this.url = url;
            this.name = name;
            this.des = des;
        }
    }

    public class listViewAdapter extends ArrayAdapter<Karacter> {
        Context mainActivityContext;
        int xml;
        List<Karacter> list;

        public listViewAdapter(@NonNull Context context, int resource, @NonNull List<Karacter> objects) {
            super(context, resource, objects);

            mainActivityContext = context;
            xml = resource;
            list = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) mainActivityContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterLayout = inflater.inflate(xml, null);
            TextView textView = adapterLayout.findViewById(R.id.id_adapter_textview);

            textView.setText(list.get(position).name);
            return adapterLayout;

        }
    }
}