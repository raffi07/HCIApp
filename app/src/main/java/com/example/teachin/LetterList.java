package com.example.teachin;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LetterList extends AppCompatActivity {
    private ArrayList<LetterListItem> letterList;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_list);

        createItemList();
        buildRecyclerView();

        fab = findViewById(R.id.add_floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LetterList.this, CreateNewLetter.class);
                startActivity(intent);
            }
        });
    }

    public void insertItem(){
        this.letterList.add(0, new LetterListItem(
                R.drawable.ic_responsedocument,
                getResources().getString(R.string.listThirdEntry),
                getResources().getString(R.string.listThirdEntryCount)));
    }

    public void createItemList(){
        this.letterList= new ArrayList<>();
            this.letterList.add(new LetterListItem(
                    R.drawable.ic_responsedocument,
                    getResources().getString(R.string.listThirdEntry),
                    getResources().getString(R.string.listThirdEntryCount)));
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
                Intent intent = new Intent(LetterList.this, NewLetterView.class);
                startActivity(intent);
            };
            if(position == 1){
                Intent intent = new Intent(LetterList.this, InformationLetterView.class);
                startActivity(intent);
            };
            if (position == 2) {
                Intent intent = new Intent(LetterList.this, ResponseLetterView.class);
                startActivity(intent);
            }
        }
    });
    }
}
