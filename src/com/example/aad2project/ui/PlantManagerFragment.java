package com.example.aad2project.ui;

import java.util.ArrayList;



import com.example.aad2project.R;
import com.example.aad2project.model.Plant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class PlantManagerFragment extends ListFragment {
	
	ArrayList<String> data;
	ArrayAdapter<String> lvAdapter;

	public PlantManagerFragment() {
		// Required empty public constructor
	}
	/**
	 * This method return all the data stored in the Database regarding to the
	 * user who is using the application
	 * @return
	 */
	private ArrayList<String> getPlants(){
		//Need the data from the database to inicialize the Array
		ArrayList<String> plants = new ArrayList<String>();
		Plant p1 = new Plant(); p1.setName("potatoes");
		Plant p2 = new Plant(); p2.setName("tomatoes");
		Plant p3 = new Plant(); p3.setName("onions");
		Plant p4 = new Plant(); p4.setName("garlics");
		plants.add(p1.getName());plants.add(p2.getName());
		plants.add(p3.getName());plants.add(p4.getName());
		return null;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_plant_manager,
				container, false);
		lvAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, getPlants());
		setListAdapter(lvAdapter);
		return view;
		
	}

}