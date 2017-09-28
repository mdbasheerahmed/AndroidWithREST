package com.outdoorshows.scr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class SearchResults extends Activity {

	
	
	
	@Override
	public void onCreate(Bundle icicle) {
	super.onCreate(icicle);
	setContentView(R.layout.displayresults );
	final EditText edittext = (EditText)findViewById(R.id.testEditText);
	String value = "";
	Bundle extras = getIntent().getExtras();
	if(extras !=null) {
	 value = extras.getString("title");
	}
	edittext.setText("Welcomg to "+value+" expedition ");
	
	}

    
}
