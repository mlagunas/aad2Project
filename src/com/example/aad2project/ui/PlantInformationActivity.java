package com.example.aad2project.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.example.aad2project.R;

public class PlantInformationActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_information);

		// Retrieve the id of the plant that has been pressed
		int listId = getIntent().getIntExtra("id", 0);
		boolean upperGroup = getIntent().getBooleanExtra("upper_group", false);
		// Creation of the first fragment
		PlantInformationFragment fragment = PlantInformationFragment
				.newInstance(listId, upperGroup);

		// Fragment transaction with fragment manager
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		// Fragment add into the frame_layout
		fragmentTransaction.replace(R.id.frame_content, fragment);

		// Actions displayed
		fragmentTransaction.commit();
	}

}
