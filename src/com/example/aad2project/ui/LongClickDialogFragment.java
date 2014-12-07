package com.example.aad2project.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.model.PlantDao;
import com.example.aad2project.object.Green;

public class LongClickDialogFragment extends DialogFragment {

	private boolean function;
	private int plantId;
	private int existingId;

	// Use this instance of the interface to deliver action events
	private LongClickDialogListener mListener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int arrayId;
		function = getArguments().getBoolean("function");
		plantId = getArguments().getInt("id");
		existingId = getArguments().getInt("eId");
		if (function) {
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

					PlantDao p = new PlantDao(getActivity());
					if (!function) {
						Green plant = new Green();
						plant.setId(plantId);
						plant.setExistingId(-1);
						plant.setDescription(getArguments().getString(
								"description"));
						plant.setName(getArguments().getString("name"));
						plant.setTimeToGrow(getArguments().getInt("timeToGrow"));
						plant.setNumber(getArguments().getInt("number"));
						
						p.addPlant(plant,true);
						
						Toast.makeText(getActivity(), "Added",
								Toast.LENGTH_LONG).show();

					} else {
						p.deletePlant(plantId);
						Toast.makeText(getActivity(), "Deleted",
								Toast.LENGTH_LONG).show();
					}
					mListener.onDialogItemClick();
					break;
				case 1:

					// this one will always be "show info"
					// so we can implement one function for both
					Intent intent = new Intent(getActivity(),
							PlantInformationActivity.class);
					if (existingId == -1)
						intent.putExtra("id", plantId);
					else
						intent.putExtra("id", existingId);
					
					intent.putExtra("UPPER_GROUP", function);
					startActivity(intent);
					break;
				}

			}
		});
		return builder.create();
	}

	public interface LongClickDialogListener {
		public void onDialogItemClick();
	}

	// Override the Fragment.onAttach() method to instantiate the
	// LongClickDialogListener
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the LongClickDialogListener so we can send events to
			// the host
			mListener = (LongClickDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement LongClickDialogListener");
		}
	}
}
