package com.ios7.wallify;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class Setwall1Activity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private ImageView imageview1;
	
	private SharedPreferences wallLink;
	private TimerTask loadDelay;
	private Intent loadDialogIntent = new Intent();
	private SharedPreferences config;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.setwall1);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		imageview1 = findViewById(R.id.imageview1);
		wallLink = getSharedPreferences("wallLink", Activity.MODE_PRIVATE);
		config = getSharedPreferences("config", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		// Show a toast informing the user that the app will attempt to set wallpaper
		Toast.makeText(getApplicationContext(), "Loading in high-res and setting wallpaper...", Toast.LENGTH_SHORT).show();
		Glide.with(getApplicationContext())
				.load(Uri.parse(wallLink.getString("wallLink", "")))
				.into(new SimpleTarget<Drawable>() {
					@Override
					public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
						imageview1.setImageDrawable(resource); // Set the image into the ImageView
						Bitmap bitmapImg = ((BitmapDrawable) imageview1.getDrawable()).getBitmap();

						WallpaperManager wallManager = WallpaperManager.getInstance(getApplicationContext());
						try {
							wallManager.clear();
							wallManager.setBitmap(bitmapImg);


						} catch (IOException ex) {

						}
						finish();
					}

					@Override
					public void onLoadFailed(@Nullable Drawable errorDrawable) {
						// Calls the second error activity
						loadDialogIntent.setClass(getApplicationContext(), Setwall2Activity.class);
						startActivity(loadDialogIntent);
						// Waits the specified timeout then quits back to walldownload
						loadDelay = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										finish();
									}
								});
							}
						};
						_timer.schedule(loadDelay, (int)(Double.parseDouble(config.getString("timeout", ""))));
					}
				});
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
