package com.example.aad2project.ui;

import java.util.Calendar;
import java.util.Date;

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
import com.example.aad2project.model.PlantDao;
import com.example.aad2project.model.TaskDao;
import com.example.aad2project.model.TaskPlant;
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

	private OnTaskCalendarFragmentInteractionListener mListener;

	public TaskCalendarFragment() {
		// Required empty public constructor
	}

	public void refresh() {
		if (mAdapter != null && tp != null) {
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

		if (!noPlants) {
			t.addTask("Water " + p.getAddedPlants().get(0).getName());
			tp.createTaskPlant(p.getAddedPlants().get(0),
					t.getAllTask().get(0), new Date().getTime());
		}

		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

		// Hide the Expand button of the group
		mList.setGroupIndicator(null);

		// Set onChildClick behavior
		mList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// Get task's ID
				TaskPlant tp = (TaskPlant) mAdapter.getChild(groupPosition,
						childPosition);
				int taskId = tp.getTask().getId();

				// Send ID back to activity
				onItemPressed(taskId);

				return false;
			}
		});

		if (!noPlants) {
			mAdapter = new TaskCalendarAdapter(getActivity(),
					tp.getAllTaskPlant());
			mList.setAdapter(mAdapter);
			mList.setGroupIndicator(null);
			for (int i = 0; i < mAdapter.getGroupCount(); i++) {
				mList.expandGroup(i);
			}

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
