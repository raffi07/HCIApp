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

public class FragmentOverviewResponse extends Fragment {
    private View v;

    private ArrayList<LetterListItem> childrenList;
    private ArrayList<LetterListItem> savedList;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton fab;
    private Button sortReadButton;
    private Button clearButton;
    final Handler handler = new Handler();

    public FragmentOverviewResponse() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.response_overview_fragment,container,false);

        createItemList();
        buildRecyclerView(v);

        fab = (FloatingActionButton) v.findViewById(R.id.qrScan_floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(intent);
            }
        });
        sortReadButton = (Button) v.findViewById(R.id.readButtonResponse);
        clearButton= v.findViewById(R.id.clearButton);
        sortReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFilter();
            }
        });

        return v;
    }

    public void createItemList(){
        this.childrenList= new ArrayList<>();
        childrenList.add(new LetterListItem(
                R.drawable.ic_read,
                "Anna",
                "2 days ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_read,
                "Bob",
                "3 days ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_returned,
                "Catherina",
                "4 days ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_sent,
                "David",
                "today"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_returned,
                "Evelyn",
                "today"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_returned,
                "Fred",
                "yesterday"));

        this.savedList= new ArrayList<>();
        savedList.add(new LetterListItem(
                R.drawable.ic_read,
                "Anna",
                "2 days ago"));
        savedList.add(new LetterListItem(
                R.drawable.ic_read,
                "Bob",
                "3 days ago"));
        savedList.add(new LetterListItem(
                R.drawable.ic_returned,
                "Catherina",
                "4 days ago"));
        savedList.add(new LetterListItem(
                R.drawable.ic_sent,
                "David",
                "today"));
        savedList.add(new LetterListItem(
                R.drawable.ic_returned,
                "Evelyn",
                "today"));
        savedList.add(new LetterListItem(
                R.drawable.ic_returned,
                "Fred",
                "yesterday"));
    }

    public void buildRecyclerView(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.childrenList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new MyAdapter(childrenList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    public void sorting(){
        childrenList.remove(0);
        childrenList.remove(0);
        childrenList.remove(1);
        clearButton.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
    }

    public void clearFilter(){
        childrenList.set(0, savedList.get(0));
        childrenList.set(1, savedList.get(1));
        childrenList.set(2, savedList.get(2));
        childrenList.add(savedList.get(3));
        childrenList.add(savedList.get(4));
        childrenList.add(savedList.get(5));
        clearButton.setVisibility(View.INVISIBLE);
        mAdapter.notifyDataSetChanged();
    }
}
