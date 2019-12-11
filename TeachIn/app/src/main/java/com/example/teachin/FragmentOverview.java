package com.example.teachin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentOverview extends Fragment {
    private View v;

    private ArrayList<LetterListItem> childrenList;
    private ArrayList<LetterListItem> savedList;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button sentButton;
    private Button readButton;
    private Button clearButton;
    final Handler handler = new Handler();

    public FragmentOverview() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.overview_fragment,container,false);

        createItemList();
        buildRecyclerView(v);

        readButton = v.findViewById(R.id.readButton);
        sentButton = v.findViewById(R.id.sentButton);
        clearButton = v.findViewById(R.id.clearButtonOverview);

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sorting(R.drawable.ic_completed);
            }
        });

        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sorting(R.drawable.ic_sent);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFilter();
            }
        });

        return v;
    }

    public void createItemList(){
        this.childrenList= new ArrayList<>();
        childrenList.add(new LetterListItem(
                R.drawable.ic_completed,
                "Anna",
                "yesterday"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Bob",
                "5 days ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_completed,
                "Catherina",
                "yesterday"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_completed,
                "David",
                "today"));

        this.savedList= new ArrayList<>();
        savedList.add(new LetterListItem(
                R.drawable.ic_completed,
                "Anna",
                "yesterday"));
        savedList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Bob",
                "5 days ago"));
        savedList.add(new LetterListItem(
                R.drawable.ic_completed,
                "Catherina",
                "yesterday"));
        savedList.add(new LetterListItem(
                R.drawable.ic_completed,
                "David",
                "today"));
    }

    public void buildRecyclerView(View v){
        recyclerView = v.findViewById(R.id.childrenList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new MyAdapter(childrenList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    public void update(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                String sent = "0 of 4";
                Button sentButton = getActivity().findViewById(R.id.sentButton);
                sentButton.setText(sent);
                Button readButton = getActivity().findViewById(R.id.readButton);
                String read = "4 of 4";
                readButton.setText(read); }
        }, 1000);

    }

    public void sorting(int status){
        if(childrenList.size() == 4){
            ArrayList<LetterListItem> sortedArray = new ArrayList<>();
            for(LetterListItem item : childrenList){
                if(item.getImageResource() == status){
                    sortedArray.add(item);
                }
            }
            childrenList.clear();
            childrenList.addAll(sortedArray);
            clearButton.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        }
        else{
            clearFilter();
        }
    }

    public void clearFilter(){
        childrenList.clear();
        childrenList.addAll(savedList);
        clearButton.setVisibility(View.INVISIBLE);
        mAdapter.notifyDataSetChanged();
    }

}
