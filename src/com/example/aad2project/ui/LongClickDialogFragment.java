package com.example.aad2project.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.model.Plant;
import com.example.aad2project.model.PlantDao;

public class LongClickDialogFragment extends DialogFragment {

	private int listId;	
	private boolean function;
	private LongClickDialogFragment dialog;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int arrayId;		
		listId = getArguments().getInt("position");
		function = getArguments().getBoolean("function");
		dialog = this;
		
		
		if (function){
			arrayId = R.array.long_click_delete;
		} else {
			arrayId = R.array.long_click_add;
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setItems(arrayId, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               // The 'which' argument contains the index position
	               // of the selected item
	            	   
	            	   Intent i = new Intent();
	            	   
	            	   switch (which) {
	            	   case 0:
	            		   // add or delete
	            		   // we have to differentiate between them
	            		   // but there's only one listener for both popups
	            		   
	            		   PlantDao p = new PlantDao(getActivity());
	            		   if(!function){
	            		   	   Plant plant = new Plant();
	            		   	   plant.setDescription(getArguments().getString("description"));
	            		   	   plant.setName(getArguments().getString("name"));
	            		   	   plant.setTimeToGrow(getArguments().getInt("timeToGrow"));
	            		   	   plant.setNumber(getArguments().getInt("number"));
	            			   p.addPlant(plant);
	            			   
	            			   Toast.makeText(getActivity(), "Added",
	   	   							Toast.LENGTH_LONG).show();
	            			   
            				    
            				    i.putExtra("KEY", "refresh");
            				    getTargetFragment().onActivityResult(getTargetRequestCode(), 1, i);	
            				    
            				   /* setTargetFragment(new TaskCalendarFragment(),2);
            				    Intent a = new Intent();
            				    a.putExtra("REF","refresh");
            			   
            				    getTargetFragment().onActivityResult(getTargetRequestCode(), 2, a);*/
	            		   }
	            		   else{
	            			   p.deletePlant(getArguments().getInt("id"));
	            			   Toast.makeText(getActivity(), "Deleted",
		   	   							Toast.LENGTH_LONG).show();
	            			   i.putExtra("KEY", "refresh");
	            			   
	            			   getTargetFragment().onActivityResult(getTargetRequestCode(), 1, i);
	            			   TaskCalendarFragment f = new TaskCalendarFragment();
	            			   
	            			/*   setTargetFragment(((ManagerActivity) getActivity()).mSectionsPagerAdapter.getItem(0),2);
	            			   Intent a = new Intent();
	            			   a.putExtra("REF","refresh");
	            			   
	            			   getTargetFragment().onActivityResult(getTargetRequestCode(), 2, a);*/
	            		   }
	            		   //adapter.updatePlantList(plants.getAddedPlants(),plants.getAllPlants());
	            		   break;
	            	   case 1:
	            		   // this one will always be "show info"
	            		   // so we can implement one function for both
	            		   Intent intent = new Intent(getActivity(),PlantInformationActivity.class);
	            		   intent.putExtra("id",listId);
	            		   startActivity(intent);
	            		   //adapter.updatePlantList(plants.getAddedPlants(),plants.getAllPlants());
            			   getTargetFragment().onActivityResult(1, 1, getActivity().getIntent());
	            		   break;            		  
	            	   }
	           
               }
	    });
	    return builder.create();
	}
}
