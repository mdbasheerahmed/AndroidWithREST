package com.outdoorshows.scr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gson.objects.GoogleMainSearchResult;
import com.gson.objects.Items;
import com.gson.objects.MainSearchResponse;
import com.gson.objects.Results;
import com.gson.objects.Web;
import com.outdoorshows.model.ResultItems;

public class SearchResults extends Activity {

	//TextView httpStuff;
	BufferedReader lResultReader = null;
	String query = "";
	String searchEngine = "google";
	public static final String GOOGLE = "google";
	public static final String BING = "bing";
	public static final String YAHOO = "yahoo";
	public static final String plus = "+";
	List<ResultItems> data;
	ProgressDialog m_ProgressDialog;
	InputStream lInputStream;
	

	// "http://api.bing.net/xml.aspx?AppID=59ADECC9E1F0A04C4E64B7F0C57C825977950E8A&Query=codexperiments&Sources=web&Web.Count=3";
	String bingUrl = "http://api.bing.net/json.aspx?AppID=59ADECC9E1F0A04C4E64B7F0C57C825977950E8A&JsonType=raw&Sources=web&Web.Count=10&Query=";
	String pUrl2 = "https://www.googleapis.com/customsearch/v1?key=AIzaSyAAoPQprb6aAV-AfuVjoCdErKTiJHn-4uI&cx=017576662512468239146:omuauf_lfve&q="
			+ "cricket";
	String googleURL = "https://www.googleapis.com/customsearch/v1?key=AIzaSyBvJJjW7xHnKoavJCD0bxwK4zCGVbbsQuc&cx=009052445197992192564:vnf-6zbmwf4&alt=json&q=";
	String returned = "";

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.displayresults);
		//httpStuff = (TextView) findViewById(R.id.tvHttp);
		final TextView textView = (TextView) findViewById(R.id.textView);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			query = extras.getString("desc");
		}
		textView.setText("Welcomg to " + query + " expedition ");

		try {
			if (searchEngine.equalsIgnoreCase(GOOGLE)) {
				String newQueryString = "";
				String[] tempArray = query.split(" ");
				for (int i = 0; i < tempArray.length; i++) {
					newQueryString = newQueryString + tempArray[i];
					if (i != tempArray.length - 1)
						newQueryString = newQueryString + plus;
				}
				Log.v("newQueryString", newQueryString);

				/**
				 * Google Search working with ajax
				 */
				// httpStuff.setText(GoogleSearchUtil.getSearchResults(query));

				PerformTask task = new PerformTask();
				task.execute(googleURL + query);
				m_ProgressDialog
						.setOnCancelListener(new CancelTaskOnCancelListener(
								task));

			} else if (searchEngine.equalsIgnoreCase(BING)) {

				PerformTask task = new PerformTask();
				task.execute(bingUrl + query);
				m_ProgressDialog
						.setOnCancelListener(new CancelTaskOnCancelListener(
								task));

			} else if (searchEngine.equalsIgnoreCase(YAHOO)) {

				/**
				 * Yahoo Search
				 */
				// httpStuff.setText(YahooSearchUtil.getSearcResults(query));
			}

		}catch (Exception e) {

			Log.e("exception", e.toString());
			// TODO: handle exception
		}

	}

	public class PerformTask extends
			AsyncTask<String, Integer, List<ResultItems>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			m_ProgressDialog = ProgressDialog.show(SearchResults.this, "",
					"Loading ...", true);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected List<ResultItems> doInBackground(String... params) {
			String query = params[0];

			try {

				lInputStream = BingSearchUtility.get(query);

				lResultReader = new BufferedReader(new InputStreamReader(
						lInputStream));
				/*
				 * StringBuffer lResultBuffer = new StringBuffer(""); String
				 * lTmpResult = lResultReader.readLine(); while (lTmpResult !=
				 * null) { lResultBuffer.append(lTmpResult);
				 * lResultBuffer.append("\n"); lTmpResult =
				 * lResultReader.readLine(); } Log.v("json file",
				 * lResultBuffer.toString());
				 */

			}catch (Exception e) {
				// TODO: handle exception
				Log.e("e",e.toString());
				
			}

			Gson gson = new Gson();
			if (searchEngine.equalsIgnoreCase(GOOGLE)) {

				GoogleMainSearchResult response = gson.fromJson(lResultReader,
						GoogleMainSearchResult.class);
				Log.v("query", response.getKind());
				Log.v("total results", response.getSearchInformation()
						.getTotalResults());
				List<Items> items = response.getItems();
				data = new ArrayList(items.size());
				if (items != null) {
					for (Items eachItem : items) {
						ResultItems resultItems = new ResultItems();
						returned = returned + eachItem.getTitle() + "---"
								+ "\n" + eachItem.getHtmlSnippet() + "\n"
								+ ":::" + eachItem.getFormattedUrl() + "\n";
						resultItems.setTitle(eachItem.getTitle());
						resultItems.setDescription(eachItem.getHtmlSnippet());
						resultItems.setUrl(eachItem.getLink());
						resultItems.setImageUrl(eachItem.retrieveThumbnail());
						data.add(resultItems);
					}
				}

			} else if (searchEngine.equalsIgnoreCase(BING)) {

				MainSearchResponse response = gson.fromJson(lResultReader,
						MainSearchResponse.class);
				Log.v("query", response.getSearchResponse().getQuery()
						.getSearchTerms());
				// Toast.makeText(this, response.getSearchResponse().getQuery(),
				// Toast.LENGTH_SHORT).show();
				Log.v("query", response.getSearchResponse().getQuery()
						.toString());
				Web web = response.getSearchResponse().getWeb();
				List<Results> results = web.getResults();
				data = new ArrayList(results.size());
				if (results != null) {
					for (Results eachResult : results) {
						ResultItems resultItems = new ResultItems();
						returned = returned + eachResult.getTitle() + ":"
								+ "\n" + eachResult.getDescription() + "\n";
						resultItems.setTitle(eachResult.getTitle());
						resultItems.setDescription(eachResult.getDescription());
						resultItems.setUrl(eachResult.getUrl());
						resultItems.setImageUrl(null);
						data.add(resultItems);

					}
				}
			}

			return data;

		}

		protected void onProgressUpdate(String... progress) {
			Log.d("ANDRO_ASYNC", progress[0]);

		}

		@Override
		protected void onPostExecute(List<ResultItems> unused) {
			
			/*TextView textData = new TextView(SearchResults.this);
			for (int i = 0; i < unused.size(); i++) {
				textData.setText(unused.get(i).getTitle() + "\n"
						+ unused.get(i).getDescription() + "\n"
						+ unused.get(i).getDescription() + "");
				textData.setTextColor(Color.WHITE);
				textData.setGravity(Gravity.CENTER);
				setContentView(textData);
			}*/

				Intent myIntent = new Intent(SearchResults.this,
						 DisplayResultsActivity.class); 
				myIntent.putExtra("searchResults",
						(ArrayList<ResultItems>) unused);
				startActivity(myIntent);
				m_ProgressDialog.dismiss();
		}

		/*
		 * 
		 * @Override protected void onPostExecute(final List<ResultItems>
		 * unused) { runOnUiThread(new Runnable() {
		 * 
		 * @Override public void run() { if (m_ProgressDialog!=null) {
		 * m_ProgressDialog.dismiss(); m_ProgressDialog = null; } if
		 * (result!=null) { for (Movie movie : result) { longToast(movie.name +
		 * " - " + movie.rating); } } TextView textData = new
		 * TextView(SearchResults.this); for (int i = 0; i < unused.size(); i++)
		 * { textData.setText(unused.get(i).getTitle()+"\n"+unused.get(i).
		 * getDescription() +"\n"+unused.get(i).getDescription()+"");
		 * textData.setTextColor(Color.WHITE);
		 * textData.setGravity(Gravity.CENTER); setContentView(textData); }
		 * 
		 * Intent intent = new Intent(SearchResults.this,
		 * DisplayResultsActivity.class); intent.putExtra("searchResults",
		 * data.toArray()); startActivity(intent);
		 * 
		 * } }); }
		 */
	}

	private class CancelTaskOnCancelListener implements OnCancelListener {
		private AsyncTask<?, ?, ?> task;

		public CancelTaskOnCancelListener(AsyncTask<?, ?, ?> task) {
			this.task = task;
		}

		@Override
		public void onCancel(DialogInterface dialog) {
			if (task != null) {
				task.cancel(true);
			}
		}
	}
}
