package com.example.aad2project.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aad2project.R;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link TaskInformationFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class TaskInformationFragment extends Fragment {

	private int taskId;

	public static TaskInformationFragment newInstance(int id) {
		TaskInformationFragment fragment = new TaskInformationFragment();
		Bundle args = new Bundle();
		args.putInt("TASK_ID", id);
		fragment.setArguments(args);
		return fragment;
	}

	public TaskInformationFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			taskId = getArguments().getInt("TASK_ID");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_task_information, container,
				false);
		TextView tv = (TextView) view.findViewById(R.id.test);
		tv.setText(""+taskId);
		return view;
	}

}
