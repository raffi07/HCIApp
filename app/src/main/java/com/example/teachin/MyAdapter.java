package com.example.teachin;

import android.app.LauncherActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private ArrayList<LetterListItem> itemArray;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView firstEntry;
        public TextView secondEntry;


        public MyViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.itemCard);
            firstEntry = itemView.findViewById(R.id.documentTitle);
            secondEntry = itemView.findViewById(R.id.studentCount);
        }
    }

    public MyAdapter(ArrayList<LetterListItem> itemArray){
        this.itemArray = itemArray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LetterListItem currentItem = this.itemArray.get(position);

        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.firstEntry.setText(currentItem.getTitle());
        holder.secondEntry.setText(currentItem.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return itemArray.size();
    }
}
