package com.outdoorshows.scr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.util.Log;

public class BingSearchUtility {

	public static InputStream get(String pUrl) throws IOException,
			JSONException {
		BufferedReader lResultReader = null;
		try {
			URI lUri = new URI(pUrl);

			// Prepares the request.
			HttpClient lHttpClient = new DefaultHttpClient();
			HttpGet lHttpGet = new HttpGet();
			lHttpGet.setURI(lUri);

			// Sends the request and read the response
			HttpResponse lHttpResponse = lHttpClient.execute(lHttpGet);
			int statusCode = lHttpResponse.getStatusLine().getStatusCode();
			Log.e("status Code", statusCode + "");
			InputStream lInputStream = lHttpResponse.getEntity().getContent();

			return lInputStream;

		} catch (URISyntaxException eURISyntaxException) {
			eURISyntaxException.printStackTrace();
			throw (IOException) new IOException()
					.initCause(eURISyntaxException);
		} catch (ClientProtocolException eClientProtocolException) {
			eClientProtocolException.printStackTrace();
			throw (IOException) new IOException()
					.initCause(eClientProtocolException);
		} catch (IOException eIOException) {
			eIOException.printStackTrace();
			throw eIOException;
		} finally {
			// Closes the input stream anyway
			if (lResultReader != null) {
				try {
					lResultReader.close();
				} catch (IOException e) {
					Log.e("WEBTAG", e.getMessage());
				}
			}
		}
	}

}
