package com.example.aad2project.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.example.aad2project.R;

public class PlantInformationActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_information);

		if (getIntent() == null) {
			Log.d("TAG", "Intent es null");
		} else {
			Log.d("TAG", "Intent no es null");

		}

		// Retrieve the id of the plant that has been pressed
		int listId = getIntent().getIntExtra("id", 0);

		// Creation of the first fragment
		PlantInformationFragment loginFragment = new PlantInformationFragment();
		Bundle args = new Bundle();
		args.putInt("id", listId);
		loginFragment.setArguments(args);

		// Fragment transaction with fragment manager
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		// Fragment add into the frame_layout
		fragmentTransaction.replace(R.id.frame_content, loginFragment);

		// Actions displayed
		fragmentTransaction.commit();
	}

}
