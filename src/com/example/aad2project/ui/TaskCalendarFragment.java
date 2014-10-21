package com.example.aad2project.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.aad2project.R;
import com.example.aad2project.model.Task;
import com.example.aad2project.model.TaskPlant;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class TaskCalendarFragment extends Fragment {

	private ExpandableListView mList;
	private TaskCalendarAdapter mAdapter;

	public TaskCalendarFragment() {
		// Required empty public constructor
	}
	
	
	// THIS WHOLE CLASS SHOLD BE DONE USING TASKPLANTS INSTEAD OF TASKS. MUST FIX
	private List<TaskPlant> getTasks() {
		List<TaskPlant> tasks = new ArrayList<TaskPlant>();
		Task t1 = new Task(0, "Water plants");
		Task t2 = new Task(1, "Harvest fruits");
		Task t3 = new Task(2, "Plant plant");
		Task t4 = new Task(3, "Cut grass");
		
		TaskPlant tp1 = new TaskPlant();
		tp1.setDate(new Date());
		tp1.setTask(t1);
		
		TaskPlant tp2 = new TaskPlant();
		tp2.setDate(new Date());
		tp2.setTask(t2);
		
		TaskPlant tp3 = new TaskPlant();
		tp3.setDate(new Date(System.currentTimeMillis()+86400000));
		tp3.setTask(t3);
		
		TaskPlant tp4 = new TaskPlant();
		tp4.setDate(new Date(System.currentTimeMillis()+86400000*2));
		tp4.setTask(t4);
		
		
		tasks.add(tp1);
		tasks.add(tp2);
		tasks.add(tp3);
		tasks.add(tp4);
		return tasks;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_task_calendar,
				container, false);

		mList = (ExpandableListView) view.findViewById(R.id.list);

		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		mAdapter = new TaskCalendarAdapter(getActivity(), getTasks());
		mList.setAdapter(mAdapter);
		
		mList.setGroupIndicator(null);		
		for (int i = 0; i < mAdapter.getGroupCount(); i++) {
			mList.expandGroup(i);
		}
		super.onViewCreated(view, savedInstanceState);
	}

}
