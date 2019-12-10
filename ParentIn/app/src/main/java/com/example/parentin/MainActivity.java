package com.example.parentin;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import static com.example.parentin.App.CHANNEL_1_ID;
import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private ArrayList<CardItem> letterList;
    private ArrayList<CardItem> savedList;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button receivedButton;
    private Button pendingButton;
    private Button completedButton;
    private Button clearButton;
    final Handler handler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);
        notificationLauncher();

        receivedButton = findViewById(R.id.sentButtonResponseNew);
        pendingButton = findViewById(R.id.returnButtonResponseNew);
        completedButton = findViewById(R.id.completedButtonResponseNew);
        clearButton = findViewById(R.id.clearButton);

        receivedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sorting(R.drawable.ic_sent);
            }
        });

        pendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sorting(R.drawable.ic_returned);
            }
        });

        completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sorting(R.drawable.ic_completed);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFilter();
            }
        });

        createItemList();
        buildRecyclerView();
    }

    public void createItemList(){
        this.letterList= new ArrayList<>();
        this.letterList.add(new CardItem(
                R.drawable.ic_responsedocument,
                R.drawable.ic_sent,
                getResources().getString(R.string.listThirdEntry),
                getResources().getString(R.string.listThirdEntryCount)));
        letterList.add(new CardItem(
                R.drawable.ic_infodocument,
                R.drawable.ic_completed,
                getResources().getString(R.string.listFirstEntry),
                getResources().getString(R.string.listFirstEntryCount)));
        letterList.add(new CardItem(
                R.drawable.ic_responsedocument,
                R.drawable.ic_returned,
                getResources().getString(R.string.listSecondEntry),
                getResources().getString(R.string.listSecondEntryCount)));

        this.savedList= new ArrayList<>();
        this.savedList.add(new CardItem(
                R.drawable.ic_responsedocument,
                R.drawable.ic_sent,
                getResources().getString(R.string.listThirdEntry),
                getResources().getString(R.string.listThirdEntryCount)));
        savedList.add(new CardItem(
                R.drawable.ic_infodocument,
                R.drawable.ic_completed,
                getResources().getString(R.string.listFirstEntry),
                getResources().getString(R.string.listFirstEntryCount)));
        savedList.add(new CardItem(
                R.drawable.ic_responsedocument,
                R.drawable.ic_returned,
                getResources().getString(R.string.listSecondEntry),
                getResources().getString(R.string.listSecondEntryCount)));

    }

    public void sorting(int status){
        if(letterList.size() == 3){
            ArrayList<CardItem> sortedArray = new ArrayList<>();
            for(CardItem item : letterList){
                if(item.getBackground() == status){
                    sortedArray.add(item);
                }
            }
            letterList.clear();
            letterList.addAll(sortedArray);
            clearButton.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        }
        else{
            clearFilter();
        }
    }

    public void clearFilter(){
        letterList.clear();
        letterList.addAll(savedList);
        clearButton.setVisibility(View.INVISIBLE);
        mAdapter.notifyDataSetChanged();
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
                    Intent intent = new Intent(MainActivity.this, LoadPhoto.class);
                    startActivity(intent);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            letterList.set(0, new CardItem(
                                    R.drawable.ic_responsedocument,
                                    R.drawable.ic_returned,
                                    getResources().getString(R.string.listThirdEntry),
                                    getResources().getString(R.string.listThirdEntryCountUpdated)));
                            mAdapter.notifyDataSetChanged();
                            String open = "0 of 3";
                            receivedButton.setText(open);
                            String signed = "2 of 2";
                            pendingButton.setText(signed);
                        }}, 5000);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            letterList.set(0, new CardItem(
                                    R.drawable.ic_responsedocument,
                                    R.drawable.ic_completed,
                                    getResources().getString(R.string.listThirdEntry),
                                    getResources().getString(R.string.listThirdEntryCountUpdated)));
                            mAdapter.notifyDataSetChanged();
                            String signed = "1 of 2";
                            receivedButton.setText(signed);
                            String completed = "2 of 3";
                            pendingButton.setText(completed);
                        }}, 60000);

                };
                if(position == 1){
                    Intent intent = new Intent(MainActivity.this, LoadPhoto.class);
                    startActivity(intent);
                };
                if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, LoadPhoto.class);
                    startActivity(intent);
                }
            }
        });
    }



    public void notificationLauncher(){
        String message = "A Letter 'HCI Presentation' waits for your response";

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("confirmMessage", "Confirm");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                MainActivity.this, CHANNEL_1_ID
        )
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("ParentIn")
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);

        NotificationManagerCompat.from(MainActivity.this).cancel("confirmMessage",0 );

        Intent intent = new Intent(MainActivity.this,
                NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message", message);

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, builder.build());
    }
}
