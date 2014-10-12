package com.example.aad2project.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.example.aad2project.R;

public class LongClickDialogFragment extends DialogFragment {

	private int listId;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int arrayId;
		listId = getArguments().getInt("id");
		Toast.makeText(getActivity(), "The id is: " + listId,
				Toast.LENGTH_SHORT).show();
		if (getArguments().getBoolean("added")){
			arrayId = R.array.long_click_delete;
		} else {
			arrayId = R.array.long_click_add;
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setItems(arrayId, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               // The 'which' argument contains the index position
	               // of the selected item
	            	   switch (which) {
	            	   case 0:
	            		   // add or delete
	            		   // we have to differentiate between them
	            		   // but there's only one listener for both popups
	            		   Toast.makeText(getActivity(), "Added or deleted",
	   							Toast.LENGTH_LONG).show();
	            		   break;
	            	   case 1:
	            		   // this one will always be "show info"
	            		   // so we can implement one function for both
	            		   Intent intent = new Intent(getActivity(),PlantInformationActivity.class);
	            		   intent.putExtra("id",listId);
	            		   startActivity(intent);
	            		   break;            		  
	            	   }
	           }
	    });
	    return builder.create();
	}

	
}
