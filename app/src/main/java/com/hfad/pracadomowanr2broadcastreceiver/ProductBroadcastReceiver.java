package com.hfad.pracadomowanr2broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class ProductBroadcastReceiver extends BroadcastReceiver {

    private NotificationService notifier;
    private boolean bound = false;
    private Bundle productIntentExtras;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            System.out.println("OnServiceConnected");
            NotificationService.NotificationServiceBinder notifierBinder =
                    (NotificationService.NotificationServiceBinder) binder;
            if (! bound){
                notifier = notifierBinder.getNotifier();
            }

            notifier.makeNotification(productIntentExtras);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("tag1", "Service disconnected.");
            bound = false;
        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("tag1", "wartość bound: " + bound);
        Bundle extras = intent.getExtras();
        productIntentExtras = intent.getExtras();

        String msg = "NAME: " + extras.getString("NAME") + System.lineSeparator() +
                "PRICE: " + extras.getDouble("PRICE", 0.0) + System.lineSeparator() +
                "QUANTITY: " + extras.getDouble("QUANTITY", 0.0) + System.lineSeparator() +
                "UNIT: " + extras.getString("UNIT");
        System.out.println(msg);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

        Intent new_intent = new Intent(context, NotificationService.class);

        context.bindService(new_intent, connection, Context.BIND_AUTO_CREATE);

//
        if (bound) {
            context.unbindService(connection);
            bound = false;
        }
    }


}
