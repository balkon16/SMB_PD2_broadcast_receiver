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
            Log.d("tag1", "Receiver - onServiceConnected.");
            System.out.println("OnServiceConnected");
            NotificationService.NotificationServiceBinder notifierBinder =
                    (NotificationService.NotificationServiceBinder) binder;
            notifier = notifierBinder.getNotifier();

            notifier.makeNotification(productIntentExtras);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {

        if (bound) {
            context.unbindService(connection);
            bound = false;
        }

        Bundle extras = intent.getExtras();
        productIntentExtras = intent.getExtras();

        Intent new_intent = new Intent(context, NotificationService.class);

        context.bindService(new_intent, connection, Context.BIND_AUTO_CREATE);

    }


}
