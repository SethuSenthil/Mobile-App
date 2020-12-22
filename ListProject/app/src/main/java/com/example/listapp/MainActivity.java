package com.example.listapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Karacter> arrayList;

    public class Karacter {
        public Boolean selected;
        public int url;
        public String name;
        public String des;
        public double rating;
        public String videoURL;

        public Karacter( Boolean selected, int url, String name, String des, double rating, String videoURL){
            this.selected = selected;
            this.url = url;
            this.name = name;
            this.des = des;
            this.rating = rating;
            this.videoURL = videoURL;

        }

        void setSelected(Boolean bool){
            selected = bool;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("KEY_LIST", arrayList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        arrayList = (ArrayList<Karacter>) savedInstanceState.getSerializable("KEY_LIST");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_main_listview);

        if(savedInstanceState == null) {
            arrayList = new ArrayList<>();
            arrayList.add(new Karacter(false, R.drawable.anakin, "Anakin Skywalker", "Many know him as 'the chosen one'. Aspiring dark lord of the Sith!",4.3, "https://sethusenthil.com/Sample-Videos-For-Mobile/Anakin.mp4"));
            arrayList.add(new Karacter(false, R.drawable.ahsoka, "Ahsoka Tano", "The Padawan of Anakin Skywalker who fought in the clonewars until she was falsely accused of treason and terrorism.",3.1, "https://sethusenthil.com/Sample-Videos-For-Mobile/Ahsoka.mp4"));
            arrayList.add(new Karacter(false, R.drawable.obiwan, "Obi-Wan Kenobi", "A nobel jedi who plays a big role in the skywalker saga, training both Anakin and his son. ", 3.3, "https://sethusenthil.com/Sample-Videos-For-Mobile/obiwan.mp4"));
            arrayList.add(new Karacter(false, R.drawable.yoda, "Yoda", "Known for his unique way to articulate sentences backwards, he is one of the most powerful and wisest jedis to exist during the fall of the republic", 5.0, "https://sethusenthil.com/Sample-Videos-For-Mobile/Yoda.mp4"));

        }else{
            arrayList = (ArrayList<Karacter>)  savedInstanceState.getSerializable("KEY_LIST");
        }

        listViewAdapter adapter = new listViewAdapter(this, R.layout.adapter_listview, arrayList);
        listView.setAdapter(adapter);

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

            if(list.get(position).selected) {
                try {
                    TextView des = findViewById(R.id.textView);
                    des.setText(list.get(position).des);

                    VideoView videoView = findViewById(R.id.videoView);
                    videoView.setVideoPath(list.get(position).videoURL);
                    videoView.start();
                } catch (Exception e) {
                    Toast toast = Toast.makeText(MainActivity.this, list.get(position).des, Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            adapterLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        TextView des = findViewById(R.id.textView);
                        des.setText(list.get(position).des);

                        VideoView videoView = findViewById(R.id.videoView);
                        videoView.setVideoPath(list.get(position).videoURL);
                        videoView.start();
                    }catch(Exception e) {
                        Toast toast = Toast.makeText(MainActivity.this, list.get(position).des, Toast.LENGTH_LONG);
                        toast.show();
                    }

                    //track selected
                    for(Karacter kar : list){
                        kar.setSelected(false);
                    }

                    list.get(position).setSelected(true);



                }
            });

           Button remove = adapterLayout.findViewById(R.id.button_rem);
           RatingBar ratingBar = adapterLayout.findViewById(R.id.ratingBar);

            ratingBar.setRating((float) list.get(position).rating);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(list.get(position));
                }
            });


            TextView textView = adapterLayout.findViewById(R.id.id_adapter_textview);
            textView.setText(list.get(position).name);

            ImageView image = adapterLayout.findViewById(R.id.imageView);
            image.setImageResource(list.get(position).url);




            return adapterLayout;

        }
    }
}