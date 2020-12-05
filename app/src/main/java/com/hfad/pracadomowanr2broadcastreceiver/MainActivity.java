package com.hfad.pracadomowanr2broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

public class MainActivity extends AppCompatActivity {

    ProductBroadcastReceiver receiver;
    IntentFilter intentFilter;
    private NotificationService notifier;
    private boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver = new ProductBroadcastReceiver();
        intentFilter = new IntentFilter("com.hfad.pracadomowanr2.produkty.NEW_PRODUCT_ADDED");

        // TODO: obsłuż intent z poziomu Service
    }

    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = new Intent(this, NotificationService.class);


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}