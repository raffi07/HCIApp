package com.example.teachin;

import android.content.Intent;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LetterList extends AppCompatActivity {
    private ArrayList<LetterListItem> letterList;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_list);

        createItemList();
        buildRecyclerView();
    }

    public void insertItem(){
        this.letterList.add(0, new LetterListItem(
                R.drawable.ic_responsedocument,
                getResources().getString(R.string.listThirdEntry),
                getResources().getString(R.string.listThirdEntryUpdated)));
    }

    public void createItemList(){
        this.letterList= new ArrayList<>();
            letterList.add(new LetterListItem(
                    R.drawable.ic_infodocument,
                    getResources().getString(R.string.listFirstEntry),
                    getResources().getString(R.string.listFirstEntryCount)));
            letterList.add(new LetterListItem(
                    R.drawable.ic_responsedocument,
                    getResources().getString(R.string.listSecondEntry),
                    getResources().getString(R.string.listSecondEntryCount)));
    }

    public void buildRecyclerView(){
    recyclerView = findViewById(R.id.letterList);
    recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(this);
    mAdapter = new MyAdapter(letterList);

    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(mAdapter);

    mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(position == 0){
                Intent intent = new Intent(LetterList.this, LetterView.class);
                startActivity(intent);
            };
        }
    });
    }
}
