package com.hkw.assassins;

import com.google.android.gcm.GCMBaseIntentService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
		Log.i(TAG, "Received message");
		String message = intent.getExtras().getString("alert");
		String function = intent.getExtras().getString("function");
		Log.i(TAG, "function: " + function + " new message: " + message);

		// notifies user
		// generateNotification(context, message);

	}

	private static void generateNotification(Context context, String message) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, message, when);

		String title = context.getString(R.string.app_name);

		Intent notificationIntent = new Intent(context, MainActivity.class);
		// set intent so it does not start a new activity
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// Play default notification sound
		notification.defaults |= Notification.DEFAULT_SOUND;

		// Vibrate if vibrate is enabled
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notificationManager.notify(0, notification);

	}

	public void onError(Context context, String errorId) {
		Log.i(TAG, "error ");
	}

	public boolean onRecoverableError(Context context, String errorId) {
		return false;

	}
}
