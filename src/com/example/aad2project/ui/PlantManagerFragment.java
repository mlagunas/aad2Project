package com.example.aad2project.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

	private EditText filter;

	ArrayList<String> data;
	ArrayAdapter<String> lvAdapter;
	PlantDao plants;
	
	private ExpandableListView list;
	private PlantManagerAdapter adapter;
	
	private Boolean lastExpandedTop = false;
	private Boolean lastExpandedBot = false;
	
	private FragmentManager fm;
	private DialogFragment dialog;
	
	public PlantManagerFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		dialog = new LongClickDialogFragment();
		//fm = getActivity().getSupportFragmentManager();
		dialog.setTargetFragment(this,1);
		// Inflate the layout for this fragment
		plants = new PlantDao(getActivity());
		View view = inflater.inflate(R.layout.fragment_plant_manager,
				container, false);
		list = (ExpandableListView) view.findViewById(R.id.list);
		filter = (EditText) view.findViewById(R.id.filter);

		adapter = new PlantManagerAdapter(getActivity(), plants.getAddedPlants(),
				plants.getAllPlants());
		plants = new PlantDao(getActivity());
		
		list.setAdapter(adapter);
		list.expandGroup(0);
		
		TaskCalendarFragment tcf = (TaskCalendarFragment) getFragmentManager().findFragmentById(R.layout.fragment_task_calendar);
		plants.setFragment(tcf);
		
		return view; 
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		String text = "";
		if (requestCode == 1) //make sure fragment codes match up {
			text = data.getStringExtra("KEY");
			if (text.equals("refresh")){
				adapter.updatePlantList(plants.getAddedPlants(), plants.getAllPlants());
			}
			
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

				Intent intent = new Intent(getActivity(),
						PlantInformationActivity.class);
				intent.putExtra("id", childPosition);

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
					
					
					Plant p;
					
					// Put boolean to show Add or Delete
					Bundle bundle = new Bundle();
					
					/*
					 * Search the correct position regarding if the list are Collapsed or Expanded
					 * and regarding if the user is pressing in the top or bottom list 
					 */
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
					
					// Send all the data through a Bundle object
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
	
	}
