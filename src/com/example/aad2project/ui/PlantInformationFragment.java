package com.example.aad2project.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aad2project.R;
import com.example.aad2project.model.Plant;
import com.example.aad2project.model.PlantDao;
import com.example.aad2project.model.WeatherDao;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link PlantInformationFragment.OnPlantManagerFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link PlantInformationFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class PlantInformationFragment extends Fragment {
	private int plantId;
	private boolean upperGroup;

	private TextView plantName, plantDescription, plantHumidity,
			plantTemperature, plantLightness;
	private ImageView plantImage;
	
	public static PlantInformationFragment newInstance(int plantId, boolean upperGroup) {
		PlantInformationFragment fragment = new PlantInformationFragment();
		Bundle args = new Bundle();
		args.putInt("PLANT_ID", plantId);
		args.putBoolean("UPPER_GROUP", upperGroup);
		fragment.setArguments(args);
		return fragment;
	}

	public PlantInformationFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			plantId = getArguments().getInt("PLANT_ID", 0);
			upperGroup = getArguments().getBoolean("UPPER_GROUP");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Retrieve the id of the plant that has been pressed
		// and get the references to the xml
		// Inflate the layout for this fragment

		View view = inflater.inflate(R.layout.fragment_plant_information,
				container, false);
		
		plantImage = (ImageView) view.findViewById(R.id.plant_image);
		plantName = (TextView) view.findViewById(R.id.plant_name);
		plantDescription = (TextView) view.findViewById(R.id.plant_description);
		plantHumidity = (TextView) view.findViewById(R.id.humidity_text);
		plantTemperature = (TextView) view.findViewById(R.id.temperature_text);
		plantLightness = (TextView) view.findViewById(R.id.lightness_text);

		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

		PlantDao pDao = new PlantDao(getActivity());
		WeatherDao wDao = new WeatherDao(getActivity());
		
		Plant p = pDao.searchPlant(plantId, upperGroup);
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		String temperature = sharedPref.getString("pref_temperature", "");
		Log.d("TEMPERATURE",":"+temperature);
		plantImage.setImageResource(getImageResource(p.getName()));
		plantName.setText(p.getName());
		plantDescription.setText(p.getDescription());
		
		plantHumidity.setText(wDao.getWeather(p.getWeatherId()).getMinHumi() + "% - " +
									wDao.getWeather(p.getWeatherId()).getMaxHumi()+"%");
		
		//Setting Kilo Lumens if it's necessary
		String brMin = wDao.getWeather(p.getWeatherId()).getMinLightness()+" ";
		if(brMin.length() > 5){
			brMin = (String) brMin.subSequence(0, brMin.length()-4)+" k";
		}
		String brMax = wDao.getWeather(p.getWeatherId()).getMaxLightnesss()+" ";
		if(brMax.length() > 5){
			brMax = (String) brMax.subSequence(0, brMax.length()-4)+" k";
		}
		plantLightness.setText(brMin + "Lm - "+ brMax + "Lm");
		
		// Difference between Celsius and Fahrenheit
		if(temperature.equals("0"))
			plantTemperature.setText(wDao.getWeather(p.getWeatherId()).getMinTemp()+ "ºC - "+
									wDao.getWeather(p.getWeatherId()).getMaxTemp()+"ºC");
		else
			plantTemperature.setText((wDao.getWeather(p.getWeatherId()).getMinTemp()*9/5+32)+ "ºF - "+
					(wDao.getWeather(p.getWeatherId()).getMaxTemp()*9/5+32)+"ºF");
		
		
		super.onViewCreated(view, savedInstanceState);
	}
	
	private int getImageResource(String name){
		
		if (name.equalsIgnoreCase("potatoes")){
			return R.drawable.ic_potatoes_big;
		} else if (name.equalsIgnoreCase("carrots")){
			return R.drawable.ic_carrot_big;
		} else if (name.equalsIgnoreCase("tomatoes")){
			return R.drawable.ic_tomatoes_big;
		} else if (name.equalsIgnoreCase("lettuces")){
			return R.drawable.ic_lettuce_big;
		} else if (name.equalsIgnoreCase("sweet peas")){
			return R.drawable.ic_sweat_pea_big;
		} else {
			return R.drawable.ic_lillies_big;
		}
	}

}
