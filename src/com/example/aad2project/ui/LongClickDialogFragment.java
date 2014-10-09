package com.example.aad2project.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.aad2project.R;

public class LongClickDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int id;
		if (getArguments().getBoolean("add")){
			id = R.array.long_click_add;
		} else {
			id = R.array.long_click_delete;
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setItems(id, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               // The 'which' argument contains the index position
	               // of the selected item
	           }
	    });
	    return builder.create();
	}

	
}
