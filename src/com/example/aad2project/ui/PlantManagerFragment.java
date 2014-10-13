package com.example.aad2project.ui;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.model.DaoBase;
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

	
	// the id of the database in the plant
	// private long dbId;
	
	// these are trial ids
	private int id1 = 0 ;
	private int id2 = 1 ;
	private int id3 = 2 ;
	private int id4 = 3 ;
	private PlantDao plants;

	public PlantManagerFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		plants = new PlantDao(getActivity());
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
		plants = new PlantDao(getActivity());
		
		list.setOnItemClickListener(new OnItemClickListener() {

			// seems like it doesn't get the short click
			// like it doesn't initialize this method
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// it doesn't show the toast
				Toast.makeText(getActivity(), "Short click", Toast.LENGTH_SHORT)
						.show();
				
				// it doesn't start the new activity
				Intent intent = new Intent(getActivity(),PlantInformationActivity.class);
				intent.putExtra("id",position);
	
				startActivity(intent);

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
