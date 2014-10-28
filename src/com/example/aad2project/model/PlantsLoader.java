package com.example.aad2project.model;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;

import com.example.aad2project.object.Plant;

public class PlantsLoader extends AsyncTaskLoader<List<List<Plant>>> {

	// We hold a reference to the Loader’s data here.
	private List<List<Plant>> mData;

	public PlantsLoader(Context ctx) {
		// Loaders may be used across multiple Activitys (assuming they aren't
		// bound to the LoaderManager), so NEVER hold a reference to the context
		// directly. Doing so will cause you to leak an entire Activity's
		// context.
		// The superclass constructor will store a reference to the Application
		// Context instead, and can be retrieved with a call to getContext().

		super(ctx);

	}

	/****************************************************/
	/** (1) A task that performs the asynchronous load **/
	/****************************************************/

	@Override
	public List<List<Plant>> loadInBackground() {
		// This method is called on a background thread and should generate a
		// new set of data to be delivered back to the client.
		List<List<Plant>> data = new ArrayList<List<Plant>>();

		// Perform the query here and add the results to 'data'.
		PlantDao pd = new PlantDao(getContext());
		data.add(pd.getAddedPlants());
		data.add(pd.getAllPlants());

		return data;
	}

	/********************************************************/
	/** (2) Deliver the results to the registered listener **/
	/********************************************************/

	@Override
	public void deliverResult(List<List<Plant>> data) {
		if (isReset()) {
			// The Loader has been reset; ignore the result and invalidate the
			// data.
			releaseResources(data);
			return;
		}

		// Hold a reference to the old data so it doesn't get garbage collected.
		// We must protect it until the new data has been delivered.
		List<List<Plant>> oldData = mData;
		mData = data;

		if (isStarted()) {
			// If the Loader is in a started state, deliver the results to the
			// client. The superclass method does this for us.
			super.deliverResult(data);
		}

		// Invalidate the old data as we don't need it any more.
		if (oldData != null && oldData != data) {
			releaseResources(oldData);
		}
	}

	/*********************************************************/
	/** (3) Implement the Loader’s state-dependent behavior **/
	/*********************************************************/

	@Override
	protected void onStartLoading() {
		if (mData != null) {
			// Deliver any previously loaded data immediately.
			deliverResult(mData);
		}

		// Begin monitoring the underlying data source.
		registerReceiver();

		if (takeContentChanged() || mData == null) {
			// When the observer detects a change, it should call
			// onContentChanged()
			// on the Loader, which will cause the next call to
			// takeContentChanged()
			// to return true. If this is ever the case (or if the current data
			// is
			// null), we force a new load.
			forceLoad();
		}
	}

	@Override
	protected void onStopLoading() {
		// The Loader is in a stopped state, so we should attempt to cancel the
		// current load (if there is one).
		cancelLoad();

		// Note that we leave the observer as is. Loaders in a stopped state
		// should still monitor the data source for changes so that the Loader
		// will know to force a new load if it is ever started again.
	}

	@Override
	protected void onReset() {
		// Ensure the loader has been stopped.
		onStopLoading();

		// At this point we can release the resources associated with 'mData'.
		if (mData != null) {
			releaseResources(mData);
			mData = null;
		}

		// The Loader is being reset, so we should stop monitoring for changes.
		unregisterReceiver();
	}

	@Override
	public void onCanceled(List<List<Plant>> data) {
		// Attempt to cancel the current asynchronous load.
		super.onCanceled(data);

		// The load has been canceled, so we should release the resources
		// associated with 'data'.
		releaseResources(data);
	}

	private void releaseResources(List<List<Plant>> data) {
		// For a simple List, there is nothing to do. For something like a
		// Cursor, we
		// would close it in this method. All resources associated with the
		// Loader
		// should be released here.

	}

	private void registerReceiver() {
		// Register mMessageReceiver to receive messages.
		LocalBroadcastManager.getInstance(getContext()).registerReceiver(
				mMessageReceiver, new IntentFilter("plant-database-changed"));

	}

	private void unregisterReceiver() {
		// The Loader is being reset, so we should stop monitoring for changes.
		LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(
				mMessageReceiver);
	}

	/*********************************************************************/
	/** (4) Observer which receives notifications when the data changes **/
	/*********************************************************************/

	// handler for received Intents for the "my-event" event
	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// Extract data included in the Intent
			onContentChanged();
		}
	};
	// NOTE: Implementing an observer is outside the scope of this post (this
	// example
	// uses a made-up "SampleObserver" to illustrate when/where the observer
	// should
	// be initialized).

	// The observer could be anything so long as it is able to detect content
	// changes
	// and report them to the loader with a call to onContentChanged(). For
	// example,
	// if you were writing a Loader which loads a list of all installed
	// applications
	// on the device, the observer could be a BroadcastReceiver that listens for
	// the
	// ACTION_PACKAGE_ADDED intent, and calls onContentChanged() on the
	// particular
	// Loader whenever the receiver detects that a new application has been
	// installed.
	// Please don’t hesitate to leave a comment if you still find this
	// confusing! :)
}