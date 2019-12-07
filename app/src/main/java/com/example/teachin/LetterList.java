package com.example.teachin;

import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LetterList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_list);

        ArrayList<LetterListItem> letterList = new ArrayList<>();
        letterList.add(new LetterListItem(
                R.drawable.ic_infodocument,
                getResources().getString(R.string.listFirstEntry),
                getResources().getString(R.string.listFirstEntryCount)));
        letterList.add(new LetterListItem(
                R.drawable.ic_responsedocument,
                getResources().getString(R.string.listSecondEntry),
                getResources().getString(R.string.listSecondEntryCount)));

        recyclerView = findViewById(R.id.letterList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(letterList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}
