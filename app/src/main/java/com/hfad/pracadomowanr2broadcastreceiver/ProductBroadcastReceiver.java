package com.hfad.pracadomowanr2broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;


public class ProductBroadcastReceiver extends BroadcastReceiver {

    private NotificationService notifier;
    private boolean bound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            System.out.println("OnServiceConnected");
            NotificationService.NotificationServiceBinder notifierBinder =
                    (NotificationService.NotificationServiceBinder) binder;
            notifier = notifierBinder.getNotifier();
            //TODO: przekazać dane dla Intent (lub cały Intent) do service
            notifier.makeNotification("Testowo!");
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        String msg = "Action: " + intent.getAction() + System.lineSeparator() +
                "NAME: " + extras.getString("NAME") + System.lineSeparator() +
                "PRICE: " + extras.getDouble("PRICE", 0.0) + System.lineSeparator() +
                "QUANTITY: " + extras.getDouble("QUANTITY", 0.0) + System.lineSeparator() +
                "UNIT: " + extras.getString("UNIT");
        System.out.println(msg);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

        Intent new_intent = new Intent(context, NotificationService.class);
        new_intent.putExtra("NAME: ", extras.getString("NAME"));


//        context.startService(new_intent);

        context.bindService(new_intent, connection, Context.BIND_AUTO_CREATE);
//
//        final Handler handler = new Handler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
////                notifier.makeNotification("Testowo!");
//                handler.postDelayed(this, 1000);
//            }
//        });

        if (bound) {
            context.unbindService(connection);
            bound = false;
        }
    }


}
