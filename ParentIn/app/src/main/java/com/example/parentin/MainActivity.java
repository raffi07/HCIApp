package com.example.parentin;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import static com.example.parentin.App.CHANNEL_1_ID;
import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private ArrayList<CardItem> letterList;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    final Handler handler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = NotificationManagerCompat.from(this);
        notificationLauncher();
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
                    letterList.set(0, new CardItem(
                            R.drawable.ic_responsedocument,
                            R.drawable.ic_completed,
                            getResources().getString(R.string.listThirdEntry),
                            getResources().getString(R.string.listThirdEntryCountUpdated)));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                            String open = "0 of 3";
                            Button openButton = (Button) findViewById(R.id.sentButtonResponseNew);
                            openButton.setText(open);
                            String completed = "2 of 3";
                            Button completedButton = (Button) findViewById(R.id.completedButtonResponseNew);
                            completedButton.setText(open);
                        }
                    }, 500);

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
