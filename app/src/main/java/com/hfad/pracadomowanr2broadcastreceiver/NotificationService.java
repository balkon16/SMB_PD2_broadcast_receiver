package com.hfad.pracadomowanr2broadcastreceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.List;

public class NotificationService extends Service {

    private final IBinder binder = new NotificationServiceBinder();

    public class NotificationServiceBinder extends Binder {
        NotificationService getNotifier() {
            return NotificationService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void makeNotification(Bundle productInfo){
        Intent intent = new Intent();
        final ComponentName cn = new ComponentName("com.hfad.pracadomowanr2", "com.hfad.pracadomowanr2.produkty.ProductDetailsActivity");
        intent.setComponent(cn);
        intent.putExtra("notifyId", 123);
        intent.putExtras(productInfo);

        String productName = productInfo.getString("NAME");

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this.getApplicationContext(), "notify_001");
        mBuilder.setSmallIcon(android.R.drawable.sym_def_app_icon);
        mBuilder.setContentTitle(getResources().getString(R.string.new_product_added_notif) + " " + productName + ".");
        mBuilder.setContentText(getResources().getString(R.string.see_details_notif));
        mBuilder.setContentIntent(pIntent);
        mBuilder.setOngoing(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "channel";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "channel_title",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        notificationManager.notify(123, mBuilder.build());

    }

}
