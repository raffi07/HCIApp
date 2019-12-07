package com.example.teachin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button informationButton;
    private Button responseButton;
    private Button seeLettersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.informationButton = findViewById(R.id.informationButton);
        this.responseButton = findViewById(R.id.responseButton);
        this.seeLettersButton = findViewById(R.id.alreadyCreatedButton);

        informationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoadScreen("information");
            }
        });
        responseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoadScreen("response");
            }
        });
        seeLettersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LetterList.class);
                startActivity(intent);
            }
        });

    }

    private void openLoadScreen(String type){
        Intent intent = new Intent(this, LoadQR.class);
        intent.putExtra("letterType", type);
        startActivity(intent);
    }
}
