package com.hkw.assassins;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class TargetInfoDialog extends DialogFragment {
	SharedPreferences settings;

	public TargetInfoDialog(SharedPreferences settings) {
		this.settings = settings;

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		View view = inflater.inflate(R.layout.targetinfo_dialog, null);

		TextView targetInfo = (TextView) view.findViewById(R.id.targetname);
		targetInfo.setText(settings.getString("target_name", ""));
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(view)
		// Add action buttons
				.setPositiveButton("Locate",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// sign in the user ...
							}
						})
		/*
		 * .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int id) {
		 * TargetInfoDialog.this.getDialog().cancel(); } })
		 */;
		return builder.create();
	}
}