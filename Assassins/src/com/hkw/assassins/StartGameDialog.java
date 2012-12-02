package com.hkw.assassins;

import com.hkw.assassins.asyctasks.StartGame;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class StartGameDialog extends DialogFragment implements
		OnEditorActionListener {
	SharedPreferences settings;

	private EditText mEditText;

	public StartGameDialog(SharedPreferences settings) {
		this.settings = settings;
		// Empty constructor required for DialogFragment
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.startgame_dialog, container);
		mEditText = (EditText) view.findViewById(R.id.startgamecode);
		// Button button = (Button) view.findViewById(R.id.startGameButton);
		// settings = container.getSharedPreferences("assassins_preferences",
		// 0);
		getDialog().setTitle("Start Game");

		// Show soft keyboard automatically
		mEditText.requestFocus();
		getDialog().getWindow().setSoftInputMode(1);
		mEditText.setOnEditorActionListener(this);
		/*
		 * button.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { StartGame sg = new StartGame();
		 * sg.execute(mEditText.getText().toString());
		 * 
		 * StartGameDialog.dismiss(); } });
		 */

		return view;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_DONE == actionId) {
			// Return input text to activity
			// EditNameDialogListener activity = (EditNameDialogListener)
			// getActivity();
			// activity.onFinishEditDialog(mEditText.getText().toString());
			StartGame sg = new StartGame();
			sg.execute(v.getText().toString());

			this.dismiss();
			return true;
		}
		return false;
	}
}