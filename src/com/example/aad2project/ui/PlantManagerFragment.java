package com.example.aad2project.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
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

	private ExpandableListView list;

	
	// the parameters on the database of the database in the plant
	private int dbId;
	
	// these are trial ids
	/* private int id1 = 0 ;
	private int id2 = 1 ;
	private int id3 = 2 ;
<<<<<<< HEAD
	private int id4 = 3 ;
	private PlantDao plants;
=======
	private int id4 = 3 ; */
	

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
		/*Plant p1 = new Plant();
		p1.setName("potatoes");
		p1.setId(id1);
		Plant p2 = new Plant();
		p2.setName("tomatoes");
		p2.setId(id2);
		Plant p3 = new Plant();
		p3.setName("onions");
		p3.setId(id3);
		Plant p4 = new Plant();
		p4.setName("garlics");
		p4.setId(id4);
		plants.add(p1);
		plants.add(p2);
		plants.add(p3);
		plants.add(p4);
		return plants; */
		
	
		DatabaseHandler supp = new DatabaseHandler(getActivity(),"try.db", null, 1);
		SQLiteDatabase db = supp.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM Plant",null);
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
		     } while(c.moveToNext());
		}
		db.close();
		return plants;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		PlantDao plants = new PlantDao(getActivity());
		View view = inflater.inflate(R.layout.fragment_plant_manager,
				container, false);		
		
		/*	
		plants.addPlant("tomatoe", "red plant", 20, 0);
		plants.addPlant("potatoes", "grows underground", 50, 0);
		plants.addPlant("letucce", "green and white plant", 15, 0);
		plants.addPlant("onions", "white plant which grows underground", 25, 0);*/
		
		list = (ExpandableListView) view.findViewById(R.id.list);
		
		PlantListAdapter adapter = new PlantListAdapter(getActivity(),
				plants.getAddedPlants(), plants.getAllPlants());

		list.setAdapter(adapter);
		list.expandGroup(0);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);
		PlantDao plants = new PlantDao(getActivity());
		
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

		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (id < 0) {
					// Create dialog
					DialogFragment dialog = new LongClickDialogFragment();

					// Put boolean to show Add or Delete
					Bundle bundle = new Bundle();
					bundle.putBoolean("added", (Boolean) view.getTag());
					bundle.putInt("id", position);
					boolean kind = (Boolean) view.getTag();
					bundle.putBoolean("type", kind);
					//bundle.putStringArrayList("add", plants.getAddedPlants());
					//bundle.put("all",plants.getAllPlants());
					
					
					// bundle.putLong("id", dbId);
					dialog.setArguments(bundle);

					// Show dialog
					dialog.show(getFragmentManager(), "longClickDialog");

					//Toast.makeText(getActivity(), "On long click listener",
						//	Toast.LENGTH_SHORT).show();
				}
				return true;

			}
		});
	}
}
