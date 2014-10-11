package com.example.aad2project.ui;

import java.util.ArrayList;

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
import com.example.aad2project.model.Plant;

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
		Plant p1 = new Plant();
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
		return plants;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_plant_manager,
				container, false);

		list = (ExpandableListView) view.findViewById(R.id.list);

		PlantListAdapter adapter = new PlantListAdapter(getActivity(),
				getPlants(), getPlants());

		list.setAdapter(adapter);
		list.expandGroup(0);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

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
				int listId = position;
				Intent intent = new Intent(getActivity(),PlantInformationActivity.class);
				intent.putExtra("id",listId);
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
					int listId = position;
					bundle.putInt("id", listId);
					
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
