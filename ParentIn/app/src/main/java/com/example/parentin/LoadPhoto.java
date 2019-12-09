package com.example.parentin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoadPhoto extends AppCompatActivity {
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
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(intent);

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
        finish();
    }
}
