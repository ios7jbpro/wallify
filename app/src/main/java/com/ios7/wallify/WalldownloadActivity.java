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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class WalldownloadActivity extends AppCompatActivity {
	
	private ArrayList<HashMap<String, Object>> walljsonlistmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear6;
	private LinearLayout linear2;
	private LinearLayout linear5;
	private LinearLayout linear4;
	private TextView textview1;
	private LinearLayout linear20;
	private TextView textview4;
	private LinearLayout linear7;
	private LinearLayout linear9;
	private FrameLayout linear8;
	private TextView textview3;
	private TextView textview2;
	private LinearLayout linear19;
	private ImageView imageview1;
	private FrameLayout linear10;
	private LinearLayout LinearLayout1;
	private ImageView imageview3;
	private LinearLayout Search_bg;
	private LinearLayout LinearLayout2;
	private LinearLayout linear14;
	private LinearLayout LinearLayout4;
	private ImageView ImageView4;
	private LinearLayout LinearLayout5;
	private ImageView ImageView5;
	private ImageView ImageView6;
	private LinearLayout LinearLayout3;
	private LinearLayout LinearLayout15;
	private TextView time2;
	private TextView Tarik;
	private TextView AM_PM;
	private LinearLayout linear11;
	private LinearLayout linear15;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private ImageView Phone;
	private ImageView Massage;
	private ImageView Game;
	private ImageView Translator;
	private ImageView More;
	private TextView button1;
	private TextView button2;
	
	private SharedPreferences selectedItemList;
	private RequestNetwork fetchJson;
	private RequestNetwork.RequestListener _fetchJson_request_listener;
	private RequestNetwork downloadWall;
	private RequestNetwork.RequestListener _downloadWall_request_listener;
	private Intent intentDownloadRemoteWall = new Intent();
	private SharedPreferences wallLink;
	private Intent setWallLoader = new Intent();
	private SharedPreferences config;
	private SharedPreferences temporaryCache;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.walldownload);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear6 = findViewById(R.id.linear6);
		linear2 = findViewById(R.id.linear2);
		linear5 = findViewById(R.id.linear5);
		linear4 = findViewById(R.id.linear4);
		textview1 = findViewById(R.id.textview1);
		linear20 = findViewById(R.id.linear20);
		textview4 = findViewById(R.id.textview4);
		linear7 = findViewById(R.id.linear7);
		linear9 = findViewById(R.id.linear9);
		linear8 = findViewById(R.id.linear8);
		textview3 = findViewById(R.id.textview3);
		textview2 = findViewById(R.id.textview2);
		linear19 = findViewById(R.id.linear19);
		imageview1 = findViewById(R.id.imageview1);
		linear10 = findViewById(R.id.linear10);
		LinearLayout1 = findViewById(R.id.LinearLayout1);
		imageview3 = findViewById(R.id.imageview3);
		Search_bg = findViewById(R.id.Search_bg);
		LinearLayout2 = findViewById(R.id.LinearLayout2);
		linear14 = findViewById(R.id.linear14);
		LinearLayout4 = findViewById(R.id.LinearLayout4);
		ImageView4 = findViewById(R.id.ImageView4);
		LinearLayout5 = findViewById(R.id.LinearLayout5);
		ImageView5 = findViewById(R.id.ImageView5);
		ImageView6 = findViewById(R.id.ImageView6);
		LinearLayout3 = findViewById(R.id.LinearLayout3);
		LinearLayout15 = findViewById(R.id.LinearLayout15);
		time2 = findViewById(R.id.time2);
		Tarik = findViewById(R.id.Tarik);
		AM_PM = findViewById(R.id.AM_PM);
		linear11 = findViewById(R.id.linear11);
		linear15 = findViewById(R.id.linear15);
		linear16 = findViewById(R.id.linear16);
		linear17 = findViewById(R.id.linear17);
		linear18 = findViewById(R.id.linear18);
		Phone = findViewById(R.id.Phone);
		Massage = findViewById(R.id.Massage);
		Game = findViewById(R.id.Game);
		Translator = findViewById(R.id.Translator);
		More = findViewById(R.id.More);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		selectedItemList = getSharedPreferences("selectedItemList", Activity.MODE_PRIVATE);
		fetchJson = new RequestNetwork(this);
		downloadWall = new RequestNetwork(this);
		wallLink = getSharedPreferences("wallLink", Activity.MODE_PRIVATE);
		config = getSharedPreferences("config", Activity.MODE_PRIVATE);
		temporaryCache = getSharedPreferences("temporaryCache", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intentDownloadRemoteWall.setAction(Intent.ACTION_VIEW);
				intentDownloadRemoteWall.setData(Uri.parse(walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString()));
				startActivity(intentDownloadRemoteWall);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				setWallLoader.setClass(getApplicationContext(), Setwall1Activity.class);
				startActivity(setWallLoader);
			}
		});
		
		_fetchJson_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				walljsonlistmap = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				Glide.with(getApplicationContext()).load(Uri.parse(walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("lowprew").toString())).into(imageview1);
				Glide.with(getApplicationContext()).load(Uri.parse(walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("lowprew").toString())).into(imageview3);
				textview1.setText(walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("name").toString());
				wallLink.edit().putString("wallLink", walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString()).commit();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_downloadWall_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		// Calls the specified repo
		fetchJson.startRequestNetwork(RequestNetworkController.GET, temporaryCache.getString("directrepo", ""), "", _fetchJson_request_listener);
		// Sets the statusbar color from XML and changes the close button color on light theme
		switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
			    case Configuration.UI_MODE_NIGHT_YES:
			 
			        break;
			    case Configuration.UI_MODE_NIGHT_NO:
			break; 
		}
		linear7.setClipToOutline(true);
		linear9.setClipToOutline(true);
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
