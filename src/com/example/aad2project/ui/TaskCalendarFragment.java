package com.example.aad2project.ui;

import java.sql.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.aad2project.R;
import com.example.aad2project.model.PlantDao;
import com.example.aad2project.model.TaskDao;
import com.example.aad2project.model.TaskPlantDao;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class TaskCalendarFragment extends Fragment {
	
	private ExpandableListView mList;
	private TaskCalendarAdapter mAdapter;
	private PlantDao p;
	private TaskDao t;
	private TaskPlantDao tp;
	private boolean noPlants;
	
	
	public TaskCalendarFragment() {
		// Required empty public constructor
	}
	
	public void refresh(){
		mAdapter.updateTaskList(tp.getAllTaskPlant());
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		String text = "";
		if (requestCode == 2) //make sure fragment codes match up {
			text = data.getStringExtra("REF");
			if (text.equals("refresh")){
				mAdapter.updateTaskList(tp.getAllTaskPlant());
			}
			
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_task_calendar,
				container, false);
		
		mList = (ExpandableListView) view.findViewById(R.id.list);
		
		p = new PlantDao(getActivity());
		tp = new TaskPlantDao(getActivity());
		t = new TaskDao(getActivity());
		
		p.setFragment(this);
		
		noPlants = p.getAddedPlants().isEmpty();
		
		tp.deleteAllTaskPlant();
		t.deleteAllTask();
		
		
		if(!noPlants){
				t.addTask("Water "+p.getAddedPlants().get(0).getName());
				tp.createTaskPlant(p.getAddedPlants().get(0), 
					t.getAllTask().get(0), new Date(System.currentTimeMillis()));
		}
		
		
		return view;
	}

	
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		
		if(!noPlants){
			mAdapter = new TaskCalendarAdapter(getActivity(), tp.getAllTaskPlant());
			mList.setAdapter(mAdapter);
			mList.setGroupIndicator(null);		
			for (int i = 0; i < mAdapter.getGroupCount(); i++) {
				mList.expandGroup(i);
			}
		}
		super.onViewCreated(view, savedInstanceState);
	}

}
