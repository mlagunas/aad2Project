package com.example.aad2project.ui;

import android.os.Bundle;
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

	private TextView plantName, plantDescription, plantHumidity,
			plantTemperature, plantLightness;
	private ImageView plantImage;
	
	public static PlantInformationFragment newInstance(int plantId) {
		PlantInformationFragment fragment = new PlantInformationFragment();
		Bundle args = new Bundle();
		args.putInt("PLANT_ID", plantId);
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
		Log.d("INTENT","3. Id: "+plantId);

		PlantDao pDao = new PlantDao(getActivity());
		WeatherDao wDao = new WeatherDao(getActivity());
		
		Plant p = pDao.searchPlant(plantId);
		
		
		plantImage.setImageResource(getImageResource(p.getName()));
		plantName.setText(p.getName());
		plantDescription.setText(p.getDescription());
		
		
		Log.d("TAG","Description: "+p.getDescription());

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
