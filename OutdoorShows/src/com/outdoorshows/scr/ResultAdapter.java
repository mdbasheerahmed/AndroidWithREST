package com.outdoorshows.scr;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.outdoorshows.helper.FlushedInputStream;
import com.outdoorshows.helper.HttpRetriever;
import com.outdoorshows.model.ResultItems;

public class ResultAdapter extends ArrayAdapter<ResultItems> {

	private HttpRetriever httpRetriever = new HttpRetriever();

	private ArrayList<ResultItems> resultItems;

	private Activity context;

	public ResultAdapter(Activity context, int textViewResourceId,
			ArrayList<ResultItems> resultItems) {
		super(context, textViewResourceId, resultItems);
		this.context = context;
		this.resultItems = resultItems;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (view == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R.layout.display, null);
		}

		ResultItems result = resultItems.get(position);

		if (result != null) {

			// title
			TextView nameTextView = (TextView) view.findViewById(R.id.toptext);
			nameTextView.setText(result.getTitle());

			// desc
			TextView ratingTextView = (TextView) view
					.findViewById(R.id.bottomtext);
			ratingTextView.setText("Rating: " + result.getDescription());

			// thumb image
			ImageView imageView = (ImageView) view.findViewById(R.id.icon);
			String url = result.getImageUrl();

			if (url != null) {
				Bitmap bitmap = fetchBitmapFromCache(url);
				if (bitmap == null) {
					new BitmapDownloaderTask(imageView).execute(url);
				} else {
					imageView.setImageBitmap(bitmap);
				}
			} else {
				imageView.setImageBitmap(null);
			}

		}

		return view;

	}

	private LinkedHashMap<String, Bitmap> bitmapCache = new LinkedHashMap<String, Bitmap>();

	private void addBitmapToCache(String url, Bitmap bitmap) {
		if (bitmap != null) {
			synchronized (bitmapCache) {
				bitmapCache.put(url, bitmap);
			}
		}
	}

	private Bitmap fetchBitmapFromCache(String url) {

		synchronized (bitmapCache) {
			final Bitmap bitmap = bitmapCache.get(url);
			if (bitmap != null) {
				// Bitmap found in cache
				// Move element to first position, so that it is removed last
				bitmapCache.remove(url);
				bitmapCache.put(url, bitmap);
				return bitmap;
			}
		}

		return null;

	}

	private class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {

		private String url;
		private final WeakReference<ImageView> imageViewReference;

		public BitmapDownloaderTask(ImageView imageView) {
			imageViewReference = new WeakReference<ImageView>(imageView);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			url = params[0];
			InputStream is = httpRetriever.retrieveStream(url);
			if (is == null) {
				return null;
			}
			return BitmapFactory.decodeStream(new FlushedInputStream(is));
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (isCancelled()) {
				bitmap = null;
			}

			addBitmapToCache(url, bitmap);

			if (imageViewReference != null) {
				ImageView imageView = imageViewReference.get();
				if (imageView != null) {
					imageView.setImageBitmap(bitmap);
				}
			}
		}
	}

}
