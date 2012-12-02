package com.hkw.assassins;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gcm.GCMBaseIntentService;
import com.hkw.assassins.asyctasks.GetUserFromServer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
		String alert = intent.getExtras().getString("alert");
		String function = intent.getExtras().getString("function");
		Log.i(TAG, "function: " + function + " alert: " + alert);

		// notifies user
		generateNotification(context, alert);

		SharedPreferences settings = getSharedPreferences(
				"assassins_preferences", 0);

		if (function.equals("gamestart") || function.equals("newtarget")) {
			String targetname = intent.getExtras().getString("targetname");
			settings.edit().putString("target_name", targetname).commit();
			Log.d(TAG, "Target Name:" + targetname);

			GetUserFromServer gufs = new GetUserFromServer(settings, "");
			try {
				String JSONString = gufs.execute(targetname).get();
				Log.d(TAG, "Target info RCVD");
				JSONObject jsonObject = new JSONObject(JSONString);
				settings.edit()
						.putString("target_latitude",
								jsonObject.getString("latitude")).commit();

				settings.edit()
						.putString("target_longitude",
								jsonObject.getString("longitude")).commit();

				settings.edit()
						.putString("target_updatetime",
								jsonObject.getString("LastLocationUpdate"))
						.commit();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (function.equals("targetupdate")) {
			String latitude = intent.getExtras().getString("latitude");
			settings.edit().putString("target_latitude", latitude).commit();

			String longitude = intent.getExtras().getString("longitude");
			settings.edit().putString("target_longitude", longitude).commit();

			Log.d(TAG, "Latitude: " + latitude + " Longitude:" + longitude);

		} else if (function.equals("gameend")) {

		}

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
