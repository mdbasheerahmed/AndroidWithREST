package com.outdoorshows.scr;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.outdoorshows.model.ResultItems;

public class DisplayResultsActivity  extends ListActivity{
	
	private List<ResultItems> resultSet;
	private ArrayAdapter<ResultItems> resultAdapter;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			
			setContentView(R.layout.finaldisplay);
			resultSet =  (ArrayList<ResultItems>)getIntent().getSerializableExtra("searchResults");
			resultAdapter = new ArrayAdapter<ResultItems>(this, android.R.layout.simple_list_item_1,resultSet);
			setListAdapter(resultAdapter);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("excpetion", e.toString());
		}
		
		
		/*Serializable s = this.getIntent().getSerializableExtra("searchResults");
		Object[] o = (Object[]) s;
		if (o != null) {
		    resultSet = new ArrayList<ResultItems>(o.length);
		    for (int i = 0; i < o.length; i++) {
		        if (o[i] instanceof ResultItems) {
		            resultSet.add((ResultItems) o[i]);
		        }
		    }
		}*/
		
		

	}
	
	 @Override
	 	    protected void onListItemClick(ListView l, View v, int position, long id) {
	 	         
	 	        super.onListItemClick(l, v, position, id);
	 	        ResultItems resultItems = resultAdapter.getItem(position);
	 	         
	 	       String url = resultItems.getUrl();
	 	       longToast(resultItems.getTitle());
	 	        if (url==null || url.length()==0) {
	 	            longToast(getString(R.string.no_result));
	 	            return;
	 	        }
	 	         
	 	       // String title = IMDB_BASE_URL + movie.imdbId;
	 	         
	 	      Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));               
	 	       try {
	 	    	  startActivity(myIntent);
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("e", e.toString());
			}
	 	         
	 	    }
	 	     
	 	    public void longToast(CharSequence message) {
	 	        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	     }

	 	 
}
