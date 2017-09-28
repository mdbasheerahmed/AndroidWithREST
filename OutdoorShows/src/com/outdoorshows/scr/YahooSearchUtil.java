package com.outdoorshows.scr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class YahooSearchUtil {
	
	// Yahoo API key
	 private static final String API_KEY = "SO2mNubV34Eo_KGTOhYrujnmNTPRxGuFuTa768sGeLsvPL4Qg4bQmkOw_xQrAEZW";
	
	 
	/* public YahooSearchUtil() {
	  
	  makeQuery("questio verum");
	  makeQuery("url:http://frankmccown.blogspot.com/");
	  makeQuery("site:frankmccown.blogspot.com");
	 }*/
	 
	 public static String getSearcResults(String query) {
	  StringBuilder sb = new StringBuilder();
	  System.out.println("\nQuerying for " + query);
	     
	  try
	  {
	   // Convert spaces to +, etc. to make a valid URL
	  // query = URLEncoder.encode(query, "UTF-8");
	   
	   // Give me back 10 results in JSON format
	   URI url = new URI("http://boss.yahooapis.com/ysearch/web/v1/" + query +
	     "?appid=" + API_KEY + "&count=10&format=json");
	   
	// Prepares the request.
		HttpClient lHttpClient = new DefaultHttpClient();
		HttpGet lHttpGet = new HttpGet();
		lHttpGet.setURI(url);
	   
	// Sends the request and read the response
		HttpResponse lHttpResponse = lHttpClient.execute(lHttpGet);
		int statusCode = lHttpResponse.getStatusLine().getStatusCode();
		Log.e("status Code", statusCode + "");
		InputStream lInputStream = lHttpResponse.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				lHttpResponse.getEntity().getContent()));
		
	  // URLConnection connection = url.openConnection();
	  
	   String line;
	   StringBuilder builder = new StringBuilder();
	   //BufferedReader reader = new BufferedReader(
			//new InputStreamReader(connection.getInputStream()));
	   while((line = reader.readLine()) != null) {
	    builder.append(line);
	   }
	   
	   String response = builder.toString();
	     
	   JSONObject json = new JSONObject(response);
	 
	   Log.v("","\nResults:");
	   Log.v("","Total results = " +
	           json.getJSONObject("ysearchresponse")
	           .getString("deephits"));

	   			
	           JSONArray ja = json.getJSONObject("ysearchresponse")
	            .getJSONArray("resultset_web");
	          
	           Log.v("","\nResults:");
	           for (int i = 0; i < ja.length(); i++) {
	             System.out.print((i+1) + ". ");
	             JSONObject j = ja.getJSONObject(i);
	             String title = j.getString("title"); 
	             String urllink = j.getString("url");
	             sb.append(title);
	 			 sb.append("\n");
	 			 sb.append(urllink);
	 			 sb.append("\n");
	            }
	            
	  }
	  catch (Exception e) {
	   Log.v("","Something went wrong...");
	   Log.e("exeption",e.toString());
	  }
	  
	  return sb.toString();
	 }
	 
	
	


}
