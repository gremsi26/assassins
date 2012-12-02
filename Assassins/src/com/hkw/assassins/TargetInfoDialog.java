package com.hkw.assassins;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class TargetInfoDialog extends DialogFragment {
	SharedPreferences settings;

	private EditText mEditText;

	public TargetInfoDialog(SharedPreferences settings) {
		this.settings = settings;
		// Empty constructor required for DialogFragment
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.targetinfo_dialog, container);
		// mEditText = (EditText) view.findViewById(R.id.startgamecode);
		// Button button = (Button) view.findViewById(R.id.startGameButton);
		// settings = container.getSharedPreferences("assassins_preferences",
		// 0);
		getDialog().setTitle("Target Info");
		TextView targetInfo = (TextView) view.findViewById(R.id.targetname);

		targetInfo.setText("Target Name: "
				+ settings.getString("target_name", ""));
		// Show soft keyboard automatically
		// mEditText.requestFocus();
		// getDialog().getWindow().setSoftInputMode(1);
		// mEditText.setOnEditorActionListener(this);
		/*
		 * button.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { StartGame sg = new StartGame();
		 * sg.execute(mEditText.getText().toString());
		 * 
		 * StartGameDialog.dismiss(); } });
		 */

		return view;
	}

}