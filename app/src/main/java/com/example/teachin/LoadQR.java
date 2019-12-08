package com.example.teachin;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoadQR extends AppCompatActivity {
    private Button chooseLetterButton;
    private String type;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_qr);

        Intent launchIntent = getIntent();
        this.type = launchIntent.getStringExtra("letterType");

        this.chooseLetterButton = findViewById(R.id.loadDocumentButton);

        chooseLetterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setContentView(R.layout.activity_loaded_qr);
                    }
                }, 500);
            }
        });
    }
    public void redoClickHandler(View view){
        finish();
        startActivity(getIntent());
    }

    public void doneClickHandler(View view){
        Intent intent = new Intent(this, CreateNewLetter.class);
        startActivity(intent);
    }
}
