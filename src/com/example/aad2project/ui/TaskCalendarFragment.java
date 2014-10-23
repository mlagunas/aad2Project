package com.example.aad2project.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.example.aad2project.R;
import com.example.aad2project.model.Plant;
import com.example.aad2project.model.Task;
import com.example.aad2project.model.TaskPlant;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class TaskCalendarFragment extends Fragment {

	private ExpandableListView mList;
	private TaskCalendarAdapter mAdapter;

	private OnTaskCalendarFragmentInteractionListener mListener;

	public TaskCalendarFragment() {
		// Required empty public constructor
	}

	// THIS WHOLE METHOD SHOULD BE DONE USING THE DATABASE
	private List<TaskPlant> getTasks() {
		List<TaskPlant> tasks = new ArrayList<TaskPlant>();
		Task t1 = new Task(0, "Water plants");
		Task t2 = new Task(1, "Harvest fruits");
		Task t3 = new Task(2, "Plant plant");
		Task t4 = new Task(3, "Cut grass");
		Plant p1 = new Plant();
		p1.setName("Potato");
		Plant p2 = new Plant();
		p2.setName("Tomato");
		Plant p3 = new Plant();
		p3.setName("Peas");
		Plant p4 = new Plant();
		p4.setName("Bananas");

		TaskPlant tp1 = new TaskPlant();
		tp1.setDate(new Date());
		tp1.setTask(t1);
		tp1.setPlant(p1);

		TaskPlant tp2 = new TaskPlant();
		tp2.setDate(new Date());
		tp2.setTask(t2);
		tp2.setPlant(p2);

		TaskPlant tp3 = new TaskPlant();
		tp3.setDate(new Date(System.currentTimeMillis() + 86400000));
		tp3.setTask(t3);
		tp3.setPlant(p3);

		TaskPlant tp4 = new TaskPlant();
		tp4.setDate(new Date(System.currentTimeMillis() + 86400000 * 2));
		tp4.setTask(t4);
		tp4.setPlant(p4);

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
		
		// Hide the Expand button of the group
		mList.setGroupIndicator(null);

		// Set onChildClick behavior
		mList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// Get task's ID
				TaskPlant tp = (TaskPlant) mAdapter.getChild(groupPosition, childPosition);
				int taskId = tp.getTask().getId();
				
				// Send ID back to activity
				onItemPressed(taskId);
				
				return false;
			}
		});
		
		// Expand all groups
		for (int i = 0; i < mAdapter.getGroupCount(); i++) {
			mList.expandGroup(i);
		}
		super.onViewCreated(view, savedInstanceState);
	}

	public void onItemPressed(int id) {
		if (mListener != null) {
			mListener.onTaskCalendarFragmentInteraction(id);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnTaskCalendarFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					activity.toString()
							+ " must implement OnTaskCalendarFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnTaskCalendarFragmentInteractionListener {
		public void onTaskCalendarFragmentInteraction(int id);
	}
}
