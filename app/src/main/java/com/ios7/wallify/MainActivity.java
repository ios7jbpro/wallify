package com.ios7.wallify;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {

	private LinearLayout linear1;
	private ViewPager viewpager1;
	private LinearLayout bottombarroot;
	private LinearLayout linear2;
	private TextView textview1;
	private LinearLayout linear4;
	private TextView button1;
	private TextView button2;
	// private View tab1bg;

	private PageLoaderInitFragmentAdapter pageLoaderInit;
	private SharedPreferences config;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}


	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		viewpager1 = findViewById(R.id.viewpager1);
		bottombarroot = findViewById(R.id.bottombarroot);
		linear2 = findViewById(R.id.linear2);
		textview1 = findViewById(R.id.textview1);
		linear4 = findViewById(R.id.linear4);
		// tab1bg = findViewById(R.id.tab1bg);
		// tab1bg.setBackgroundResource(R.drawable.activetab);
		 button1 = findViewById(R.id.button1);
		 button2 = findViewById(R.id.button2);
		 button1.setBackgroundResource(R.drawable.activetab);
		 button2.setBackgroundResource(R.drawable.roundedbgviolent);
		pageLoaderInit = new PageLoaderInitFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		config = getSharedPreferences("config", Activity.MODE_PRIVATE);
		// startActivity(new Intent(MainActivity.this, AppAbandoned.class));
		// finish();

			button1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					viewpager1.setCurrentItem((int)0);
				}
			});

		 	button2.setOnClickListener(new View.OnClickListener() {
		 		@Override
				public void onClick(View _view) {
				viewpager1.setCurrentItem((int)1);
				}
		 	});
		 }

		 // Handle back button press
		 @Override
		 public void onBackPressed() {
			 config.edit().putString("backSignal", "1").commit();

			 // Check if we are on tab 1
			 if (config.getString("currenttab", "").equals("1")) {
				 // Delay the viewpager switch to tab 0
				 new Handler().postDelayed(new Runnable() {
					 @Override
					 public void run() {
						 viewpager1.setCurrentItem(0);  // Set to the first tab with a delay
					 }
				 }, 150); // Delay of 75ms to allow fragment to handle back press

			 } else {
				 // Normal back press behavior
				 if (config.getString("fragmentCanExit", "").equals("0")) {
					 // Do nothing
				 } else {
					 super.onBackPressed();
					 finish();
				 }
			 }
		 }


	private void initializeLogic() {
		// This SharedPrefs section sets your desired repo.
		config.edit().putString("repo", "https://ihs.ios7.xyz/wallify-api/categories.json").commit();
		config.edit().putString("repo", "https://raw.githubusercontent.com/j1459863h/wallify-walls/refs/heads/main/").commit();
		// Enable or disable categories
		config.edit().putString("categories", "1").commit();
		config.edit().putString("directrepo", "https://altdisk.eimaen.pw/api/download/a69b5e5031f23e06cd1af7f885de5c0c/anime.json").commit();
		// Changes the default timeout.
		if (config.getString("timeout", "").equals("")) {
			config.edit().putString("timeout", "5000").commit();
		}
		String setupFlag = config.getString("setupcomplete", "");
		Log.d("DEBUG", "setupcomplete read: " + setupFlag);

		if (setupFlag.equals("")) {
			Log.d("DEBUG", "Launching setup activity");
			startActivity(new Intent(MainActivity.this, SetupActivity1.class));
		}
		// Disables color extraction.
		// ^^ This is no longer required as we added an exception catch to the extraction logic, the app won't fail anymore even if it fails.
		// So now we enable it by default.
		// nvm, we fucked up, disabled it.
		// FINALLY IT WORKS!
		if (config.getString("colorextraction", "").equals("")) {
			config.edit().putString("colorextraction", "1").commit();
		}
		// Check for debuuger/debug build
		if (android.os.Debug.isDebuggerConnected()) {
			config.edit().putString("debugMode", "1").commit();
			textview1.setText("Wallify(DEBUGGER DETECTED, DO NOT USE THIS IN PRODUCTION)");
			config.edit().putString("disableanims", "1").commit();
			config.edit().putString("disableblur", "1").commit();
		} else  {
			config.edit().putString("debugMode", "0").commit();
			config.edit().remove("disableanims").commit();
			config.edit().remove("disableblur").commit();
		}
		if (config.getString("disableblur", "").equals("")) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) { // API 31+
				config.edit().putString("disableblur", "0").commit();
			} else {
				config.edit().putString("disableblur", "1").commit();
			}
		} else {
			// Nothing
		}
		// This part sets the user statusbar color as same as the color pulled from the XMLs
		switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
			case Configuration.UI_MODE_NIGHT_YES:

				break;
			case Configuration.UI_MODE_NIGHT_NO:
				getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
				getWindow().setStatusBarColor(0xFFFFFFFF);
				break;
		}
		// Sets how many pages do you have in the ViewPager. IF YOURE A NEWBIE DO NOT TOUCH THIS!
		pageLoaderInit.setTabCount(2);
		viewpager1.setAdapter(pageLoaderInit);
		ViewPager viewPager = findViewById(R.id.viewpager1);
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// You can implement your code here if you want to do something when the page is scrolled
			}

			@Override
			public void onPageSelected(int position) {
				// This method is called when a new page is selected
				// 'position' is the index of the newly selected page
				// Toast.makeText(MainActivity.this, "page:" + position, Toast.LENGTH_SHORT).show();
				if (position == 0) {
					// tab1bg.setBackgroundResource(R.drawable.activetab);
					button1.setBackgroundResource(R.drawable.activetab);
					button2.setBackgroundResource(R.drawable.roundedbgviolent);
					config.edit().putString("currenttab", "0").commit();
				}
				if (position == 1) {
					// tab1bg.setBackgroundResource(R.drawable.roundedbgviolent);
					button2.setBackgroundResource(R.drawable.activetab);
					button1.setBackgroundResource(R.drawable.roundedbgviolent);
					config.edit().putString("currenttab", "1").commit();
				}

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
		window.setStatusBarColor(ContextCompat.getColor(this,R.color.backgroundviolent));
	}

	public class PageLoaderInitFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;

		public PageLoaderInitFragmentAdapter(Context context, FragmentManager manager) {
			super(manager);
			this.context = context;
		}

		public void setTabCount(int tabCount) {
			this.tabCount = tabCount;
		}

		@Override
		public int getCount() {
			return tabCount;
		}

		@Override
		public CharSequence getPageTitle(int _position) {

			return null;
		}

		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new WallpapersFragmentActivity();
			}
			if (_position == 1) {
				return new SettingsDialogFragmentActivity();
			}
			return null;
		}
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
				_result.add((double) _arr.keyAt(_iIdx));
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