package com.example.testbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(Broadcast.class.getSimpleName(), "Air Plane mode");
        Toast.makeText(context, "Air plane change", Toast.LENGTH_SHORT).show();
    }
}
