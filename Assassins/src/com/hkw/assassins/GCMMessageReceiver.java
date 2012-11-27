package com.hkw.assassins;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class GCMMessageReceiver extends BroadcastReceiver {
private String TAG = "GCMMessageReceiver";
	  @Override
	  public void onReceive(Context context, Intent intent) {
	    String action = intent.getAction();
	    Log.w(TAG, "Message Receiver called");
	    if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
	      Log.w(TAG, "Received message");
	      final String payload = intent.getStringExtra("payload");
	      Log.d(TAG, "dmControl: payload = " + payload);
	      // Send this to my application server
	    }
	  }
	} 