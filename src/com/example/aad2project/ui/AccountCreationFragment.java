package com.example.aad2project.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.aad2project.R;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link AccountCreationFragment.OnAccountCreationFragmentInteractionListener} interface to
 * handle interaction events. Use the
 * {@link AccountCreationFragment#newInstance} factory method to create an
 * instance of this fragment.
 *
 */
public class AccountCreationFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private EditText  usernameView = null;
	private EditText  passwordView = null;
	private EditText passwordConfirmView = null;
	private Button Create;

	private OnAccountCreationFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 *
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment AccountCreationFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static AccountCreationFragment newInstance(String param1,
			String param2) {
		AccountCreationFragment fragment = new AccountCreationFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public AccountCreationFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_account_creation, container,
				false);

		// Store the different component of the UI
		usernameView = (EditText) view.findViewById (R.id.emailCreationAccount);
		passwordView = (EditText) view.findViewById (R.id.passwordCreationAccount);
		passwordConfirmView = (EditText) view.findViewById (R.id.confirmpasswordCreationAccount);
		Create = (Button) view.findViewById (R.id.buttonCreate);
		// Put OnClickListener on the login button
		Create.setOnClickListener(new OnClickListener() {

			// Create an account
			@Override
			public void onClick(View arg0) {
				// If both the password and the password confirmation EditText are the same
				if (passwordView.getText().toString().equals(passwordConfirmView.getText().toString())){
					// Use the addAccount(String, String) method from MainActivity
					((MainActivity)getActivity()).addAccount(usernameView.getText().toString(), passwordView.getText().toString());
				}
			}
		});

		return view;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnAccountCreationFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnAccountCreationFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}
