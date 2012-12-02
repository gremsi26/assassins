package com.hkw.assassins.asyctasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

public class StartGame extends AsyncTask<String, Integer, String> {
	String TAG = "GetUserFromServer";
	SharedPreferences settings;

	// String function = "default";

	public StartGame() {
		// this.settings = settings;
	}

	@Override
	protected String doInBackground(String... params) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(
				"http://assassinsgame.cloudapp.net/api/games");
		httpPost.addHeader("Accept", "application/json");

		ArrayList nameValuePairs = new ArrayList();
		// this is where you add your data to the post method
		nameValuePairs.add(new BasicNameValuePair("GameStartCode", params[0]));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(httpPost);

			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			Log.d(TAG, "Status Code:" + statusCode);
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(RegisterUserOnServer.class.toString(),
						"Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.d(TAG, "Data Recieved: " + builder.toString());
		return builder.toString();

	}

	public void writeJSON() {
		JSONObject object = new JSONObject();
		try {
			object.put("name", "Jack Hack");
			object.put("score", new Integer(200));
			object.put("current", new Double(152.32));
			object.put("nickname", "Hacker");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(object);
	}

	protected void onPostExecute(String results) {
		if (results != null) {
			// EditText et = (EditText) findViewById(R.id.my_edit);
			// ///et.setText(results);
		}
		// Button b = (Button) findViewById(R.id.my_button);
		// b.setClickable(true);
	}
}