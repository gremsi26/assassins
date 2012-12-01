package com.hkw.assassins;

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

public class EditNameDialog extends DialogFragment implements
		OnEditorActionListener {
	SharedPreferences settings;

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText);
	}

	private EditText mEditText;

	public EditNameDialog(SharedPreferences settings) {
		this.settings = settings;
		// Empty constructor required for DialogFragment
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.editname_dialog, container);
		mEditText = (EditText) view.findViewById(R.id.editname_text);
		// settings = container.getSharedPreferences("assassins_preferences",
		// 0);
		getDialog().setTitle("Edit Name");

		// Show soft keyboard automatically
		mEditText.requestFocus();
		getDialog().getWindow().setSoftInputMode(1);
		mEditText.setOnEditorActionListener(this);

		return view;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_DONE == actionId) {
			// Return input text to activity
			// EditNameDialogListener activity = (EditNameDialogListener)
			// getActivity();
			// activity.onFinishEditDialog(mEditText.getText().toString());

			settings.edit().putString("name", mEditText.getText().toString())
					.commit();
			this.dismiss();
			return true;
		}
		return false;
	}
}