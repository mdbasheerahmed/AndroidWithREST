package com.outdoorshows.scr;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class OutdoorShowsActivity extends Activity implements OnItemClickListener{
	
    ListView lview3;
    CustomAdapter adapter;
    private ArrayList<Object> itemList;
    private ItemBean bean;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
       
        prepareArrayLits();
        lview3 = (ListView) findViewById(R.id.listView1);
        adapter = new CustomAdapter(this, itemList);
        lview3.setAdapter(adapter);
 
        lview3.setOnItemClickListener(this);
    }
 
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
    	ItemBean bean = (ItemBean) adapter.getItem(position);
    	//Toast.makeText(this, "Title => "+bean.getTitle()+" \n Description => "+bean.getDescription(), Toast.LENGTH_SHORT).show();
    	
    	try {
			
    		Class myClass = Class.forName("com.outdoorshows.scr.SearchResults");
    		Intent myIntent = new Intent(this, myClass);
    		myIntent.putExtra("title",bean.getTitle());
    		myIntent.putExtra("index",Toast.LENGTH_SHORT);
    		myIntent.putExtra("desc",bean.getDescription());
    		startActivity(myIntent);
        	
    		
    	} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e);
		
    	} catch (Exception e) {
			// TODO: handle exception
			Log.i("MicroJobs", "Unable to animate map", e);
		}
    	
    }
    
    /* Method used to prepare the ArrayList,
     * Same way, you can also do looping and adding object into the ArrayList.
     */
    public void prepareArrayLits()
    {
    	itemList = new ArrayList<Object>();
    	 
    	AddObjectToList(R.drawable.ic_add, "Hunting", "hunting");
    	AddObjectToList(R.drawable.ic_delete, "Fishing", "fishing");
    	AddObjectToList(R.drawable.ic_down, "Camping-Rving-Loging", "camping");
    	AddObjectToList(R.drawable.ic_info, "Bicycling", "bicycling");
    	AddObjectToList(R.drawable.ic_help, "ATV/4x4", "atv");
    	AddObjectToList(R.drawable.ic_download, "Hiking", "hiking");
    	AddObjectToList(R.drawable.ic_settings, "Bird Watching", "bird Watching");
    	AddObjectToList(R.drawable.ic_mail, "Outdoor Activity", "Outdoor Activity");
    	AddObjectToList(R.drawable.ic_settings, "All", "Settings desc");
    }
    
    // Add one item into the Array List
    public void AddObjectToList(int image, String title, String desc)
    {
    	bean = new ItemBean();
    	bean.setDescription(desc);
    	bean.setImage(image);
    	bean.setTitle(title);
    	itemList.add(bean);
    }
}