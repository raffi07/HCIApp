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
                Intent intent = new Intent(MainActivity.this, CreateNewLetter.class);
                startActivity(intent);
            }
        });
        responseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoadQR.class);
                startActivity(intent);
            }
        });
        seeLettersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LetterList.class);
                startActivity(intent);
            }
        });

    }
}
