package com.example.aad2project.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.example.aad2project.R;

public class SettingsActivity extends PreferenceActivity {
	
	private SharedPreferences sharedPrefs ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String syncConnPref = sharedPrefs.getString("pref_time", "");
	}
}
