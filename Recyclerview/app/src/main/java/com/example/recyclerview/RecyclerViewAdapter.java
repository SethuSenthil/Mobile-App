package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    Context parentContext;
    ArrayList<String> list;

    public RecyclerViewAdapter(Context context, ArrayList<String> list){
        parentContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate xml here
        //and return view holder
        View view = LayoutInflater.from(parentContext).inflate(R.layout.holder_view, parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textView.setText("Text" + position);
        holder.button.setText("Button " + position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        Button button;
        TextView textView;

            public RecyclerViewHolder(@NonNull View itemView){
                super(itemView);

                button = itemView.findViewById(R.id.id_holder_button);
                textView = itemView.findViewById(R.id.id_holder_textview);

            }
    }
}