package com.example.aad2project.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
	private PlantManagerAdapter adapter;
	
	private Boolean lastExpandedTop = false;
	private Boolean lastExpandedBot = false;
	
	private EditText filter;

	private OnPlantManagerFragmentInteractionListener mListener;

	// the parameters on the database of the database in the plant
	private int dbId;

	// these are trial ids
	/*
	 * private int id1 = 0 ; private int id2 = 1 ; private int id3 = 2 ; <<<<<<<
	 * HEAD private int id4 = 3 ; private PlantDao plants; ======= private int
	 * id4 = 3 ;
	 */

	public PlantManagerFragment() {
		// Required empty public constructor
	}

	/**
	 * This method return all the data stored in the Database regarding to the
	 * user who is using the application
	 * 
	 * @return
	 */
	private ArrayList<Plant> getPlants() {
		// Need the data from the database to initialize the Array
		// So we invent various plant objects and their parameters
		ArrayList<Plant> plants = new ArrayList<Plant>();
		/*
		 * Plant p1 = new Plant(); p1.setName("potatoes"); p1.setId(id1); Plant
		 * p2 = new Plant(); p2.setName("tomatoes"); p2.setId(id2); Plant p3 =
		 * new Plant(); p3.setName("onions"); p3.setId(id3); Plant p4 = new
		 * Plant(); p4.setName("garlics"); p4.setId(id4); plants.add(p1);
		 * plants.add(p2); plants.add(p3); plants.add(p4); return plants;
		 */

		DatabaseHandler supp = new DatabaseHandler(getActivity(), "try.db",
				null, 1);
		SQLiteDatabase db = supp.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM Plant", null);
		// We have to make sure that there's at least one register
		if (c.moveToFirst()) {
			// Move the cursor till we have no more registers
			do {
				Plant p = new Plant();
				p.setId(c.getInt(0));
				dbId = p.getId();
				p.setName(c.getString(1));
				p.setDescription(c.getString(2));
				p.setTimeToGrow(c.getInt(3));
				plants.add(p);
			} while (c.moveToNext());
		}
		db.close();
		return plants;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*
		 * plants.addPlant("tomatoe", "red plant", 20, 0);
		 * plants.addPlant("potatoes", "grows underground", 50, 0);
		 * plants.addPlant("letucce", "green and white plant", 15, 0);
		 * plants.addPlant("onions", "white plant which grows underground", 25,
		 * 0);
		 */
		
		// Inflate the layout for this fragment
		PlantDao plants = new PlantDao(getActivity());
		View view = inflater.inflate(R.layout.fragment_plant_manager,
				container, false);

		list = (ExpandableListView) view.findViewById(R.id.list);
		filter = (EditText) view.findViewById(R.id.filter);


		adapter = new PlantManagerAdapter(getActivity(), plants.getAddedPlants(),
				plants.getAllPlants());
		plants = new PlantDao(getActivity());
		
		list.setAdapter(adapter);
		list.expandGroup(0);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);
		plants = new PlantDao(getActivity());

		filter.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				adapter.filter(s.toString());
				adapter.notifyDataSetChanged();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		list.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Toast.makeText(getActivity(), "Short click", Toast.LENGTH_SHORT)
						.show();

				onItemPressed(childPosition);

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
		            		   onItemPressed(listId);
		            		   break;            		  
		            	   }
		           
	               }
		    });
		    return builder.create();
		}
	}

	public void onItemPressed(int id) {
		if (mListener != null) {
			mListener.onPlantManagerFragmentInteraction(id);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnPlantManagerFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnPlantManagerFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnPlantManagerFragmentInteractionListener {
		public void onPlantManagerFragmentInteraction(int id);
	}
}