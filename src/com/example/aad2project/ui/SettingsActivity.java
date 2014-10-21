package com.example.aad2project.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.ArrayAdapter;

import com.example.aad2project.R;

public class SettingsActivity extends PreferenceActivity {

	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
		
	}
}
