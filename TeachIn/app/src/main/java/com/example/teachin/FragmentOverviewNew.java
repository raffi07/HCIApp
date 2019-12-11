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

public class FragmentOverviewNew extends Fragment {
    private View v;

    private ArrayList<LetterListItem> childrenList;
    private ArrayList<LetterListItem> savedList;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton fab;
    private Button clearButton;
    private Button readButton;
    private Button completedButton;
    private Button sentButton;
    private Button returnedButton;
    final Handler handler = new Handler();

    public FragmentOverviewNew() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.new_overview_fragment,container,false);

        createItemList();
        buildRecyclerView(v);

        fab = v.findViewById(R.id.qrScan_floating_button_new);
        readButton = v.findViewById(R.id.readButtonResponseNew);
        completedButton = v.findViewById(R.id.completedButtonResponseNew);
        sentButton = v.findViewById(R.id.sentButtonResponseNew);
        returnedButton = v.findViewById(R.id.returnButtonResponseNew);
        clearButton = v.findViewById(R.id.clearButtonNew);

        fab.hide();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String oneOfSix = "1 of 6";
                returnedButton.setText(oneOfSix);
                String fiveOfSix = "5 of 6";
                sentButton.setText(fiveOfSix);
                childrenList.set(0, new LetterListItem(
                        R.drawable.ic_returned,
                        "Anna",
                        "A minute ago"
                ));
                mAdapter.notifyDataSetChanged();
                fab.show();
            }
        }, 30000);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(intent);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String noneOfSix = "0 of 6";
                        returnedButton.setText(noneOfSix);
                        String oneOfSix = "1 of 6";
                        completedButton.setText(oneOfSix);
                        childrenList.set(0, new LetterListItem(
                                R.drawable.ic_completed,
                                "Anna",
                                "Few seconds ago"
                        ));
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });

        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting(R.drawable.ic_sent);
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting(R.drawable.ic_read);
            }
        });
        returnedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting(R.drawable.ic_returned);
            }
        });
        completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting(R.drawable.ic_completed);
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
                R.drawable.ic_sent,
                "Anna",
                "30 seconds ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Bob",
                "30 seconds ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Catherina",
                "30 seconds ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_sent,
                "David",
                "30 seconds ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Evelyn",
                "30 seconds ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Fred",
                "30 seconds ago"));

        this.savedList= new ArrayList<>();
        savedList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Anna",
                "Few minutes ago"));
        savedList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Bob",
                "Few minutes ago"));
        savedList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Catherina",
                "Few minutes ago"));
        savedList.add(new LetterListItem(
                R.drawable.ic_sent,
                "David",
                "Few minutes ago"));
        savedList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Evelyn",
                "Few minutes ago"));
        savedList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Fred",
                "Few minutes ago"));
}

    public void buildRecyclerView(View v){
        recyclerView = v.findViewById(R.id.childrenList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new MyAdapter(childrenList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    public void sorting(int status){
        if(childrenList.size() == 6){
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
