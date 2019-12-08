package com.example.teachin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentOverview extends Fragment {
    private View v;

    private ArrayList<LetterListItem> childrenList;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public FragmentOverview() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.overview_fragment,container,false);

        createItemList();
        buildRecyclerView(v);

        return v;
    }

    public void createItemList(){
        this.childrenList= new ArrayList<>();
        childrenList.add(new LetterListItem(
                R.drawable.ic_completed,
                "Anna",
                "1 day ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_sent,
                "Bob",
                "5 days ago"));
        childrenList.add(new LetterListItem(
                R.drawable.ic_completed,
                "Catherina",
                "1 day ago"));
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
}
