package com.outdoorshows.scr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class GoogleSearchUtil {
	
	static String SEARCH_URL = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	public static String getSearchResults(String searchString)throws MalformedURLException,IllegalStateException,
	IOException, JSONException, NoSuchAlgorithmException, IOException {
			
		String newFeed = SEARCH_URL + searchString;
		StringBuilder response = new StringBuilder();
		Log.e("gsearch", "gsearch url:" + newFeed);
		URL url = new URL(newFeed);
		HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
		if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					httpconn.getInputStream()), 8192);
			String strLine = null;
			while ((strLine = input.readLine()) != null) {
				response.append(strLine);
			}
			input.close();
		}
		String resp = response.toString();
		
		StringBuilder sb = new StringBuilder();
		Log.v("gsearch", "gsearch result:" + resp);
		JSONObject mResponseObject = new JSONObject(resp);
		JSONObject responObject = mResponseObject.getJSONObject("responseData");
		JSONArray array = responObject.getJSONArray("results");
		Log.v("gsearch", "number of resultst:" + array.length());
		for (int i = 0; i < array.length(); i++) {
			Log.v("result", i + "] " + array.get(i).toString());
			String title = array.getJSONObject(i).getString("title");
			String urllink = array.getJSONObject(i).getString("visibleUrl");
			sb.append(title);
			sb.append("\n");
			sb.append(urllink);
			sb.append("\n");
		}
		
		return sb.toString();
		
	}
	

}
