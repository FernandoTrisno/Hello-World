package com.example.helloworld.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReciver extends BroadcastReceiver {
private static final String TAG=BroadcastReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onRecived: from BroadcastReciver");
        Toast.makeText(context,"Broadcast Recived", Toast.LENGTH_LONG);
    }
}
