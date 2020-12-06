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
import android.os.IBinder;

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

    public void makeNotification(String textContent){

//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(android.R.mipmap.sym_def_app_icon)
////                        .setSmallIcon(android.R.drawable.sym_def_app_icon)
//                        .setContentTitle("title")
//                        .setContentText(textContent)
//                        .setPriority(NotificationCompat.PRIORITY_HIGH)
//                        .setVibrate(new long[]{0, 1000})
//                        .setAutoCancel(true);

        // Tworzymy akcję
//        Intent actionIntent = new Intent(this, MainActivity.class);
//        PendingIntent actionPendingIntent = PendingIntent.getActivity(
//                this,
//                0,
//                actionIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(actionPendingIntent);

        // Wysyłamy powiadomienie

//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        int importance = NotificationManager.IMPORTANCE_HIGH;
//        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//        notificationManager.notify(123, builder.build());
//        System.out.println("makeNotification ended!");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this.getApplicationContext(), "notify_001");
//        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setSmallIcon(android.R.drawable.sym_def_app_icon);
        mBuilder.setContentTitle("Your Title");
        mBuilder.setContentText("Your text");
        mBuilder.setPriority(Notification.PRIORITY_HIGH);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
//            System.out.println("ugabuga");
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        notificationManager.notify(123, mBuilder.build());

        Intent intent = new Intent();
        final ComponentName cn = new ComponentName("com.hfad.pracadomowanr2", "com.hfad.pracadomowanr2.produkty.ProductDetailsActivity");
        intent.setComponent(cn);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

//        Intent intent = new Intent();
//        intent.setClassName("com.hfad.pracadomowanr2", ".produkty.ProductDetailsActivity");

//        intent.setComponent(new ComponentName("com.hfad.pracadomowanr2."))

//        intent.setComponent(new ComponentName("com.hfad.pracadomowanr2.produkty.ProductDetailsActivity"));
//        startActivity(intent);

//        Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.hfad.pracadomowanr2.produkty.ProductDetailsActivity");
//        startActivity(launchIntent);

//        Uri number = Uri.parse("tel:5551234");
//        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
//
//        PackageManager packageManager = getPackageManager();
//        List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent,
//                PackageManager.MATCH_DEFAULT_ONLY);
//        boolean isIntentSafe = activities.size() > 0;
//
//        System.out.println("Size: " + activities.size());
//
//        // Always use string resources for UI text.
//// This says something like "Share this photo with"
//        String title = getResources().getString(R.string.chooser_title);
//// Create intent to show chooser
//        Intent chooser = Intent.createChooser(callIntent, title);
//
//// Verify the intent will resolve to at least one activity
//        if (callIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(chooser);
//        }


//        if (isIntentSafe) {
//            startActivity(callIntent);
//        }




    }

}
