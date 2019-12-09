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

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton fab;
    final Handler handler = new Handler();

    public FragmentOverview() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.overview_fragment,container,false);

        createItemList();
        buildRecyclerView(v);

        fab = (FloatingActionButton) v.findViewById(R.id.qrScan_floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(intent);
                childrenList.set(1,new LetterListItem(
                        R.drawable.ic_completed,
                        "Bob",
                        "few seconds ago"));
                update();
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
    }

    public void buildRecyclerView(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.childrenList);
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
                Button sentButton = (Button) getActivity().findViewById(R.id.sentButton);
                sentButton.setText(sent);
                Button readButton = (Button) getActivity().findViewById(R.id.readButton);
                String read = "4 of 4";
                readButton.setText(read); }
        }, 1000);

    }
}
