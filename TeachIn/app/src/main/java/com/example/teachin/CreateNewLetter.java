package com.example.teachin;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CreateNewLetter extends AppCompatActivity {
    private Button sendToAllButton;
    private Button doneButton;
    private Switch child1;
    private Switch child2;
    private Switch child3;
    private Switch child4;
    private Switch child5;
    private Switch child6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_letter);

        sendToAllButton = findViewById(R.id.sendToAllButton);
        doneButton = findViewById(R.id.continueButton);
        child1 = findViewById(R.id.annaSwitch);
        child2 = findViewById(R.id.bobSwitch);
        child3 = findViewById(R.id.catherinaSwitch);
        child4 = findViewById(R.id.davidSwitch);
        child5 = findViewById(R.id.evelynSwitch);
        child6 = findViewById(R.id.fredSwitch);

        sendToAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                child1.setChecked(true);
                child2.setChecked(true);
                child3.setChecked(true);
                child4.setChecked(true);
                child5.setChecked(true);
                child6.setChecked(true);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewLetter.this, LetterList.class);
                startActivity(intent);
            }
        });
    }
}
