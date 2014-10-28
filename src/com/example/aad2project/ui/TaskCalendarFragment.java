package com.example.aad2project.ui;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.adapter.TaskCalendarAdapter;
import com.example.aad2project.model.PlantDao;
import com.example.aad2project.model.TaskDao;
import com.example.aad2project.model.TaskPlantDao;
import com.example.aad2project.model.TasksLoader;
import com.example.aad2project.object.Task;
import com.example.aad2project.object.TaskPlant;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class TaskCalendarFragment extends Fragment implements
		LoaderManager.LoaderCallbacks<List<TaskPlant>> {

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
		// if (mAdapter != null && tp != null) {
		// mAdapter.updateTaskList(tp.getAllTaskPlant());
		// }
		//
		// for (int i = 0; i < mAdapter.getGroupCount(); i++) {
		// mList.expandGroup(i);
		// }
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

		noPlants = p.getAddedPlants().isEmpty();

		// tp.deleteAllTaskPlant();
		// t.deleteAllTask();

		// if(!noPlants){
		// t.addTask("Water "+p.getAddedPlants().get(0).getName());
		// tp.createTaskPlant(p.getAddedPlants().get(0),
		// t.getAllTask().get(0), new Date(System.currentTimeMillis()));
		// }

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
				// onItemPressed(taskId);

				Toast.makeText(getActivity(), "Task done. Good job!",
						Toast.LENGTH_SHORT).show();

				return false;
			}
		});
		mAdapter = new TaskCalendarAdapter(getActivity(), tp.getAllTaskPlant());
		mList.setAdapter(mAdapter);
		mList.setGroupIndicator(null);
		for (int i = 0; i < mAdapter.getGroupCount(); i++) {
			mList.expandGroup(i);
		}

		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		getLoaderManager().initLoader(0, null, this);

		super.onActivityCreated(savedInstanceState);
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

	@Override
	public Loader<List<TaskPlant>> onCreateLoader(int arg0, Bundle arg1) {
		return new TasksLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<TaskPlant>> loader,
			List<TaskPlant> data) {
		if (mAdapter != null && tp != null) {
			mAdapter.updateTaskList(data);
		}

		for (int i = 0; i < mAdapter.getGroupCount(); i++) {
			mList.expandGroup(i);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<TaskPlant>> arg0) {
		// TODO Auto-generated method stub

	}

}
