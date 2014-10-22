package com.example.aad2project.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aad2project.R;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link PlantInformationFragment.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the
 * {@link PlantInformationFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class PlantInformationFragment extends Fragment {
	private int listId;
	private TextView textView1, textView2;
	private ImageView imageView;
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;


	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment PlantInformationFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static PlantInformationFragment newInstance(String param1,
			String param2) {
		PlantInformationFragment fragment = new PlantInformationFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
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
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
			listId = getArguments().getInt("id", 0);

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
		imageView = (ImageView) view.findViewById(R.id.imageView1);
		textView1 = (TextView) view.findViewById(R.id.textView1);
		textView2 = (TextView) view.findViewById(R.id.textView2);

		Toast.makeText(getActivity(), "The id is: " + listId,
				Toast.LENGTH_SHORT).show();

		switch (listId) {
		case 1:
			imageView.setImageResource(R.drawable.potatoes);
			textView1.setText(R.string.potatoes);
			textView2.setText("Potatoes are nice");
			break;
		case 2:
			imageView.setImageResource(R.drawable.tomatoes);
			textView1.setText(R.string.tomatoes);
			textView2.setText("Tomatoes are red");
			break;
		case 3:
			imageView.setImageResource(R.drawable.potatoes);
			textView1.setText(R.string.onions);
			textView2.setText("Onions make you cry");
			break;
		case 4:
			imageView.setImageResource(R.drawable.garlic);
			textView1.setText(R.string.garlic);
			textView2.setText("Garlic is very healthy");
			break;

		}
		return view;
	}

}
