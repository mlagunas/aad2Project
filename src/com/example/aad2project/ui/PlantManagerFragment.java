package com.example.aad2project.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.model.DatabaseHandler;
import com.example.aad2project.model.Plant;
import com.example.aad2project.model.PlantDao;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class PlantManagerFragment extends Fragment {

	ArrayList<String> data;
	ArrayAdapter<String> lvAdapter;
	PlantDao plants;
	private ExpandableListView list;
	private PlantListAdapter adapter;
	
	private Boolean lastExpandedTop = false;
	private Boolean lastExpandedBot = false;
	
	public PlantManagerFragment() {
		// Required empty public constructor
	}

	/**
	 * This method return all the data stored in the Database regarding to the
	 * user who is using the application
	 * 
	 * @return
	 */
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		PlantDao plants = new PlantDao(getActivity());
		View view = inflater.inflate(R.layout.fragment_plant_manager,
				container, false);		
		plants = new PlantDao(getActivity());
		/*	
		plants.addPlant("tomatoe", "red plant", 20, 0);
		plants.addPlant("potatoes", "grows underground", 50, 0);
		plants.addPlant("letucce", "green and white plant", 15, 0);
		plants.addPlant("onions", "white plant which grows underground", 25, 0);*/
		
		list = (ExpandableListView) view.findViewById(R.id.list);
		
		adapter = new PlantListAdapter(getActivity(),
				plants.getAddedPlants(), plants.getAllPlants());

		list.setAdapter(adapter);
		list.expandGroup(0);
		return view;
	}
	
	
	
	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);
		plants = new PlantDao(getActivity());
		
		list.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				Toast.makeText(getActivity(), "Short click", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getActivity(),PlantInformationActivity.class);
				intent.putExtra("id",childPosition);
	
				startActivity(intent);
				
				return false;
			}
		});
		
		list.setOnGroupExpandListener(new OnGroupExpandListener() {
	    @Override
	    public void onGroupExpand(int groupPosition) {
	    	if(groupPosition == 0)
	                lastExpandedTop = !lastExpandedTop;
	    	else
	                lastExpandedBot = !lastExpandedBot;		
	    	
		    }
		});
		
		list.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			 @Override
		    public void onGroupCollapse(int groupPosition) {
			    	if(groupPosition == 0)
				            lastExpandedTop = !lastExpandedTop;
			    	else
			                lastExpandedBot = !lastExpandedBot;			
			 }
		});
		
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (id < 0) {
					// Create dialog
					DialogFragment dialog = new LongClickDialogFragment();
					Plant p;
					
					// Put boolean to show Add or Delete
					Bundle bundle = new Bundle();
					if ( (Boolean) view.getTag()){
						ArrayList<Plant> data = plants.getAddedPlants();
						p = data.get(position-1);
					}
					else{
						ArrayList<Plant> data = plants.getAllPlants();
						if(!lastExpandedTop)
							p = data.get(position-plants.getAddedPlants().size()-2);
						else
							p = data.get(position-2);
					}
					
					bundle.putBoolean("function", (Boolean) view.getTag());
					bundle.putString("name", p.getName());
					bundle.putString("description", p.getDescription());
					bundle.putInt("number", 5);
					bundle.putInt("timeToGrow", p.getTimeToGrow());
					bundle.putInt("position",position);
					bundle.putInt("id", p.getId());
					dialog.setArguments(bundle);					
					// Show dialog
					dialog.show(getFragmentManager(), "longClickDialog");
				}
				return true;
			}
		});
	list.invalidate();
	}
	
	public class LongClickDialogFragment extends DialogFragment {

		private int listId;	
		private boolean function;
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			int arrayId;		
			listId = getArguments().getInt("position");
			
			function = getArguments().getBoolean("function");
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
		            	   
		            	   switch (which) {
		            	   case 0:
		            		   // add or delete
		            		   // we have to differentiate between them
		            		   // but there's only one listener for both popups
		            		   
		            		   PlantDao p = new PlantDao(getActivity());
		            		   if(!function){
		            		   		p.addPlant(getArguments().getString("name"), 
		            		   				getArguments().getString("description"), getArguments().getInt("timeToGrow")
		            		   				, getArguments().getInt("number"));
		            		   		Toast.makeText(getActivity(), "Added",
			   	   							Toast.LENGTH_LONG).show();
		            		   }
		            		   else{
		            			   p.deletePlant(getArguments().getInt("id"));
		            			   Toast.makeText(getActivity(), "Deleted",
			   	   							Toast.LENGTH_LONG).show();
		            		   }
		            		   adapter.updatePlantList(plants.getAddedPlants(),plants.getAllPlants());
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
}
