package com.hkw.assassins;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	private static final String TAG = "===GCMIntentService===";

	public GCMIntentService() {
		super("138142482844");
		Log.d(TAG, "[GCMIntentService] end");
	}

	public void onRegistered(Context context, String regId) {
		Log.i(TAG, "Device registered: regId = " + regId);
	}

	public void onUnregistered(Context context, String regId) {

	}

	public void onMessage(Context context, Intent intent) {

	}

	public void onError(Context context, String errorId) {
		Log.i(TAG, "error ");
	}

	public boolean onRecoverableError(Context context, String errorId) {
		return false;

	}
}
