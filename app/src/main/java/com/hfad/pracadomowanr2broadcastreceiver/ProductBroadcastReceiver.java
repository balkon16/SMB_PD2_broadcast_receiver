package com.hfad.pracadomowanr2broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class ProductBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        String msg = "Action: " + intent.getAction() + System.lineSeparator() +
                "NAME: " + extras.getString("NAME") + System.lineSeparator() +
                "PRICE: " + extras.getDouble("PRICE", 0.0) + System.lineSeparator() +
                "QUANTITY: " + extras.getDouble("QUANTITY", 0.0) + System.lineSeparator() +
                "UNIT: " + extras.getString("UNIT");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
