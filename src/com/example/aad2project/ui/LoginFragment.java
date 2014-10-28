package com.example.aad2project.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aad2project.R;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link LoginFragment.OnLoginFragmentInteractionListener}
 * interface to handle interaction events.
 * 
 */
public class LoginFragment extends Fragment {

	private OnLoginFragmentInteractionListener mListener;

	private EditText usernameView = null;
	private EditText passwordView = null;
	private TextView newAccount = null;
	private Button login;

	public LoginFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_login, container, false);

		// Store the different component of the UI
		usernameView = (EditText) view.findViewById(R.id.email);
		passwordView = (EditText) view.findViewById(R.id.password);
		login = (Button) view.findViewById(R.id.buttonLogin);
		newAccount = (TextView) view.findViewById(R.id.newAccount);
		// Put OnClickListener on the login button
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Check is the account provided already exist in the database
				// (using the MainActivity method Login(String, String)
				if (mListener != null) {
					if (mListener.login(usernameView.getText().toString(),
							passwordView.getText().toString()) == 1) {
						mListener.successfulAuthentication(usernameView
								.getText().toString(), passwordView.getText()
								.toString());
					} else {
						// If the credentials were wrong, the wrongCredentials
						// method from MainActivity is called
						passwordView.setError(getResources().getString(
								R.string.wrong_credentials));
					}
				}
			}

		});
		// Put OnClickListener on the textView for the creation of a new account
		newAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// The newAccount method from MainActivity is called
				if (mListener != null) {
					mListener.newAccount();
				}
			}
		});

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnLoginFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					activity.toString()
							+ " must implement OnPlantManagerFragmentInteractionListener");
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
	public interface OnLoginFragmentInteractionListener {
		public void successfulAuthentication(String username, String password);

		public int login(String email, String password);

		public void newAccount();
	}

}
