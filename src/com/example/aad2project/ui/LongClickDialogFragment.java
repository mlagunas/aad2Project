package com.example.aad2project.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;
import com.example.aad2project.model.Plant;
import com.example.aad2project.R;
import com.example.aad2project.model.PlantDao;

public class LongClickDialogFragment extends DialogFragment {

	private int listId,position;
	private ArrayList<Plant> allPlants,plantsAdd;
	private boolean add;
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int arrayId;		
		position = 0;
		listId = getArguments().getInt("id");
		Toast.makeText(getActivity(), "The id is: " + listId,
				Toast.LENGTH_SHORT).show();
		
		if (getArguments().getBoolean("added")){
			arrayId = R.array.long_click_delete;
			add = false;
		} else {
			arrayId = R.array.long_click_add;
			add = true;
		}
		
		allPlants = (ArrayList<Plant>) getArguments().get("all");
		plantsAdd = (ArrayList<Plant>) getArguments().get("add");

		position = getArguments().getInt("id");
		
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
	            		   if(add){
	            		   		PlantDao p = new PlantDao(getActivity());
	            		   		// Real position in the Array
	            		   		position = position - plantsAdd.size();
	            		   		p.addPlant(allPlants.get(position));
	            		   }
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
