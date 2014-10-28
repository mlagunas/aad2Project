package com.example.aad2project.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.example.aad2project.R;

public class TaskInformationActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_information);
		
		int taskId = getIntent().getIntExtra("TASK_ID", 0);
		
		// Create fragment instance
		TaskInformationFragment fragment = TaskInformationFragment.newInstance(taskId);
		
		// Add fragment to the activity's layout
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.fragment_container, fragment );
		transaction.commit();
	}
}
