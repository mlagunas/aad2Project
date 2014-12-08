package com.example.aad2project.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class SendToServer extends AsyncTask<String, String, String> {

	JSONParser jsonParser = new JSONParser();

	private static String URL = "http://84.238.21.213/greenhub/index.php";

	private String json;
	private String Email;
	private String TypeRequest;
	private String Password;

	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	public SendToServer(String email, String password, String TypeRequette) {
		Email = email;
		Password = password;
		TypeRequest = TypeRequette;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	/**
	 * Creating product
	 * */
	protected String doInBackground(String... args) {

		String maRequette = "";

		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		if (TypeRequest.equals("insert")) {
			maRequette = "INSERT INTO User (email, password) values (\""
					+ Email + "\", \"" + Password + "\")";
			params.add(new BasicNameValuePair("type", "insert"));
		} else if (TypeRequest.equals("selectUser")) {
			maRequette = "SELECT * FROM User u WHERE email = \"" + Email
					+ "\" AND password = \"" + Password + "\"";
			params.add(new BasicNameValuePair("type", "select"));
		} else if (TypeRequest.equals("selectPlant")) {
			maRequette = "SELECT * FROM Plant u";
			params.add(new BasicNameValuePair("type", "select"));
		}

		params.add(new BasicNameValuePair("request", maRequette));

		// getting JSON Object
		// Note that create product url accepts POST method
		json = jsonParser.makeHttpRequest(URL, "POST", params);
		Log.d("uhvub", json);

		/**
		 * JSONObject windJSON = null;
		 * 
		 * Log.d("TAG", "FAZFDSF");
		 * 
		 * JSONArray myJSON = null; try { myJSON = new JSONArray(json); } catch
		 * (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } for (int i = 0; i < myJSON.length(); i++) {
		 * 
		 * try { windJSON = myJSON.getJSONObject(i); } catch (JSONException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } String id;
		 * try { id = windJSON.getString("id"); Log.d("ID", id); } catch
		 * (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } // Display in the LogCat
		 * 
		 * }
		 */

		return null;
	}

	/**
	 * After completing background task Dismiss the progress dialog
	 * **/
	protected void onPostExecute(String file_url) {
	}

	public String getReturn() {
		return json;
	}

}