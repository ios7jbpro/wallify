package com.ios7.wallify;

import com.ios7.wallify.MyClasses.EzBlur;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ios7.wallify.MyClasses.EzTimer;
import com.ios7.wallify.MyClasses.EzTimerLooped;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import android.view.View;

public class WallpapersFragmentActivity extends Fragment {
	private boolean isGridVisible = false;
	private OnBackPressedCallback backPressedCallback;
	
	private Timer _timer = new Timer();
	
	private ArrayList<HashMap<String, Object>> walllist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> categorylist = new ArrayList<>();
	
	private LinearLayout rootlinear;
	private LinearLayout linear1;
	private CardView tempcardview;
	private ListView listview1;
	private LinearLayout gridlinear;
	private LinearLayout linear2;
	private GridView gridview1;
	private TextView textview1;
	private TextView textloading;
	private LinearLayout linearloading;
	private LinearLayout gridRounderLayout;
	private LinearLayout gridfadelinear;
	private LinearLayout gridloading;
	
	private RequestNetwork fetchwalljson;
	private RequestNetwork.RequestListener _fetchwalljson_request_listener;
	private TimerTask relay;
	private SharedPreferences selectedItemList;
	private Intent launchWallPreview = new Intent();
	private SharedPreferences config;
	private SharedPreferences temporaryCache;
	private TimerTask loadDelay;
	private RequestNetwork fetchcategoryjson;
	private RequestNetwork.RequestListener _fetchcategoryjson_request_listener;
	private String combinedOutput;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.wallpapers_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		rootlinear = _view.findViewById(R.id.rootlinear);
		linear1 = _view.findViewById(R.id.linear1);
		tempcardview = _view.findViewById(R.id.tempcardview);
		listview1 = _view.findViewById(R.id.listview1);
		gridlinear = _view.findViewById(R.id.gridlinear);
		gridfadelinear = _view.findViewById(R.id.gridfadelinear);
		gridloading = _view.findViewById(R.id.gridloading);
		linear2 = _view.findViewById(R.id.linear2);
		gridview1 = _view.findViewById(R.id.gridview1);
		textview1 = _view.findViewById(R.id.textview1);
		textloading = _view.findViewById(R.id.textloading);
		linearloading = _view.findViewById(R.id.linearloading);
		textloading.setVisibility(View.GONE);
		gridRounderLayout = _view.findViewById(R.id.gridRounderLayout);
		gridRounderLayout.setClipToOutline(true);
		gridfadelinear.setVisibility(View.GONE);
		fetchwalljson = new RequestNetwork((Activity) getContext());
		selectedItemList = getContext().getSharedPreferences("selectedItemList", Activity.MODE_PRIVATE);
		config = getContext().getSharedPreferences("config", Activity.MODE_PRIVATE);
		temporaryCache = getContext().getSharedPreferences("temporaryCache", Activity.MODE_PRIVATE);
		fetchcategoryjson = new RequestNetwork((Activity) getContext());
		// Create a timer that waits for 5 seconds
		relay = new TimerTask() {
			@Override
			public void run() {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						textloading.setVisibility(View.VISIBLE);
					}

				});
			}
		};
		_timer.schedule(relay, 5000);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				fetchwalljson.startRequestNetwork(RequestNetworkController.GET, config.getString("repo", "") + categorylist.get((int)_position).get("json").toString(), "", _fetchwalljson_request_listener);
				combinedOutput = config.getString("repo", "") + categorylist.get((int)_position).get("json").toString();
				temporaryCache.edit().putString("directrepo", categorylist.get((int)_position).get("json").toString()).commit();
				gridlinear.setVisibility(View.GONE);
				gridfadelinear.setVisibility(View.VISIBLE);
				listview1.setVisibility(View.GONE);
				gridlinear.setAlpha(0);
				gridfadelinear.setAlpha(0);
				isGridVisible = true;
				config.edit().putString("fragmentCanExit", "0").commit();
				config.edit().putString("categoryName", categorylist.get((int)_position).get("category").toString()).commit();
				if (config.getString("disableanims", "").equals("1")) {
					gridfadelinear.setVisibility(View.VISIBLE);
					gridlinear.setVisibility(View.GONE);
					gridloading.setVisibility(View.VISIBLE);
					listview1.setVisibility(View.GONE);
					gridlinear.setAlpha(1);
					gridfadelinear.setAlpha(1);
					listview1.setAlpha(1);
				} else {
					EzTimer.runWithDelay(50, () -> {
						EzTimerLooped loopedTimer = new EzTimerLooped();
						loopedTimer.start(1, () -> {
							if (Math.abs(gridfadelinear.getAlpha() - 1) < 0.01f) {
								loopedTimer.stop();
							} else {
								gridfadelinear.setAlpha(gridfadelinear.getAlpha() + 0.1f);
							}
						});
					});
				}
			}
		});

// Handle back button press
		// Forget all this, it's jank. I'm building my own signal system.
		//backPressedCallback = new OnBackPressedCallback(true) {
		//	@Override
		//	public void handleOnBackPressed() {
		//		if (config.getString("currenttab", "").equals("0")) {
		//			if (isGridVisible) {
		//				gridlinear.setVisibility(View.GONE);
		//				listview1.setVisibility(View.VISIBLE);
		//				isGridVisible = false;
		//				config.edit().putString("fragmentCanExit", "1").commit();
		//			} else {
		//				setEnabled(false); // temporarily disable this callback
		//				requireActivity().getOnBackPressedDispatcher().onBackPressed(); // let activity handle it
		//			}
		//		}
		//		// else: do nothing, let activity handle it directly
		//	}
		//};
        //
		// requireActivity()
		//		.getOnBackPressedDispatcher()
		//		.addCallback(getViewLifecycleOwner(), backPressedCallback);

		// Create a timer that runs every 75 miliseconds
		Handler handler = new Handler();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if (config.getString("backSignal", "").equals("1")) {
					config.edit().putString("backSignal", "0").commit();
					if (config.getString("currenttab", "").equals("0")) {
						if (isGridVisible) {
							gridfadelinear.setVisibility(View.GONE);
							gridlinear.setVisibility(View.GONE);
							gridloading.setVisibility(View.VISIBLE);
							listview1.setVisibility(View.VISIBLE);
							listview1.setAlpha(0);
							isGridVisible = false;
							config.edit().putString("fragmentCanExit", "1").commit();
							if (config.getString("disableanims", "").equals("1")) {
								gridfadelinear.setVisibility(View.GONE);
								gridlinear.setVisibility(View.GONE);
								gridloading.setVisibility(View.GONE);
								listview1.setVisibility(View.VISIBLE);
								listview1.setAlpha(1);
							} else {
								EzTimer.runWithDelay(100, () -> {
									EzTimerLooped loopedTimer = new EzTimerLooped();
									loopedTimer.start(1, () -> {
										if (Math.abs(listview1.getAlpha() - 1) < 0.01f) {
											loopedTimer.stop();
										} else {
											listview1.setAlpha(listview1.getAlpha() + 0.05f);
										}
									});
								});
							}
						} else {
							// Do nothing
						}
					} else {
						// Do nothing
					}
				}
				handler.postDelayed(this, 75);
			}
		};
		handler.post(runnable);


		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});

		linear2.setClipToOutline(true);
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				gridfadelinear.setVisibility(View.GONE);
				gridlinear.setVisibility(View.GONE);
				gridloading.setVisibility(View.VISIBLE);
				listview1.setVisibility(View.VISIBLE);
				listview1.setAlpha(0);
				config.edit().putString("fragmentCanExit", "1").commit();
				if (config.getString("disableanims", "").equals("1")) {
					gridfadelinear.setVisibility(View.GONE);
					gridlinear.setVisibility(View.GONE);
					gridloading.setVisibility(View.GONE);
					listview1.setVisibility(View.VISIBLE);
					listview1.setAlpha(1);
				} else {
					EzTimer.runWithDelay(100, () -> {
						EzTimerLooped loopedTimer = new EzTimerLooped();
						loopedTimer.start(1, () -> {
							if (Math.abs(listview1.getAlpha() - 1) < 0.01f) {
								loopedTimer.stop();
							} else {
								listview1.setAlpha(listview1.getAlpha() + 0.05f);
							}
						});
					});
				}
			}
		});
		
		gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				selectedItemList.edit().putString("selectedWall", String.valueOf(_position)).commit();
				Log.d("WallpaperDebug", "Setting selected wall to = '" + String.valueOf((long)(_position)) + "'");
				// Set wallpaperName on config
				config.edit().putString("wallpaperName", walllist.get((int)_position).get("name").toString()).commit();
				launchWallPreview.setClass(getContext().getApplicationContext(), WalldownloadActivity.class);
				startActivity(launchWallPreview);
			}
		});
		
		gridview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (config.getString("debugMode", "").equals("1")) {
					Toast.makeText(getContext(), "Launching the new beta kotlin activity", Toast.LENGTH_SHORT).show();
					selectedItemList.edit().putString("selectedWall", String.valueOf(_position)).commit();
					Log.d("WallpaperDebug", "Setting selected wall to = '" + String.valueOf((long) (_position)) + "'");
					// Set wallpaperName on config
					config.edit().putString("wallpaperName", walllist.get((int) _position).get("name").toString()).commit();
					launchWallPreview.putExtra("wallpaperName", walllist.get((int) _position).get("name").toString());
					launchWallPreview.putExtra("wallpaperLink", walllist.get((int) _position).get("link").toString());
					launchWallPreview.setClass(getContext().getApplicationContext(), WalldownloadkotlinActivity.class);
					startActivity(launchWallPreview);
					return true;
				} else {
					return false;
				}
			}
		});
		
		_fetchwalljson_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				walllist = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
				gridview1.setAdapter(new Gridview1Adapter(walllist));
				gridview1.setNumColumns((int) 2);
				gridlinear.setAlpha(0);
				if (config.getString("disableanims", "").equals("1")) {
					gridlinear.setVisibility(View.VISIBLE);
					gridloading.setVisibility(View.GONE);
					gridlinear.setAlpha(1);
				} else {
					EzTimer.runWithDelay(500, () -> {
						gridlinear.setVisibility(View.VISIBLE);
						gridloading.setVisibility(View.GONE);
						EzTimerLooped loopedTimer = new EzTimerLooped();
						loopedTimer.start(1, () -> {
							if (Math.abs(gridlinear.getAlpha() - 1) < 0.01f) {
								loopedTimer.stop();
							} else {
								gridlinear.setAlpha(gridlinear.getAlpha() + 0.05f);
							}
						});
					});
				}
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				// Create a toast message saying there is no internet
				Toast.makeText(getContext(), "Failed to fetch, are you connected to the internet?", Toast.LENGTH_SHORT).show();
			}
		};
		
		_fetchcategoryjson_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				categorylist = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
				listview1.setAdapter(new Listview1Adapter(categorylist));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				linearloading.setVisibility(View.GONE);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		// First, checks if the app is being launched for the first time
		// If true, waits 250 ms so that the repo from Main Activity is properly set and loaded
		// Then calls the actual wallpaper loader
		// If false, directly loads the wallpaper loader
		gridlinear.setVisibility(View.GONE);
		if (temporaryCache.getString("firstTimeLoad", "").equals("")) {
			loadDelay = new TimerTask() {
				@Override
				public void run() {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (config.getString("categories", "").equals("1")) {
								fetchcategoryjson.startRequestNetwork(RequestNetworkController.GET, config.getString("repo", "") + "categories.json", "", _fetchcategoryjson_request_listener);
							} else {
								fetchwalljson.startRequestNetwork(RequestNetworkController.GET, config.getString("directrepo", ""), "", _fetchwalljson_request_listener);
							}
						}
					});
				}
			};
			_timer.schedule(loadDelay, (int)(250));
			temporaryCache.edit().putString("firstTimeLoad", "0").commit();
		} else {
			if (config.getString("categories", "").equals("1")) {
				fetchcategoryjson.startRequestNetwork(RequestNetworkController.GET, config.getString("repo", "") + "categories.json", "", _fetchcategoryjson_request_listener);
			} else {
				fetchwalljson.startRequestNetwork(RequestNetworkController.GET, config.getString("directrepo", ""), "", _fetchwalljson_request_listener);
				gridlinear.setVisibility(View.VISIBLE);
				linear2.setVisibility(View.GONE);
				listview1.setVisibility(View.GONE);
			}
		}
	}

	// The onDestroyView method should be OUTSIDE the above block
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		// Remove the callback when the view is destroyed
		if (backPressedCallback != null) {
			backPressedCallback.remove();
		}
	}


	public void _setViewSize(final View _view1, final double _width, final double _height) {
		_view1.setLayoutParams(new LinearLayout.LayoutParams((int)_width, (int)_height));
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.categorylist, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final FrameLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			linear1.setAlpha(0);

			if (config.getString("disableblur", "").equals("1")) {
				// Do nothing
			} else {
				EzBlur.setBlur(imageview1, 20);
			}

			textview1.setText(categorylist.get((int)_position).get("category").toString());
			if (config.getString("debugMode", "").equals("1")) {
				textview1.setText(categorylist.get((int)_position).get("category").toString()+"(index:"+_position+")");
			}
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(config.getString("repo", "") + categorylist.get((int)_position).get("preview").toString())).into(imageview1);
			linear2.setClipToOutline(true);
			if (config.getString("disableanims", "").equals("1")) {
				linear1.setVisibility(View.VISIBLE);
				linear1.setAlpha(1);
			} else {
				EzTimer.runWithDelay(((int) _position * 50) + 100, () -> {
					EzTimerLooped loopedTimer = new EzTimerLooped();
					loopedTimer.start(1, () -> {
						if (Math.abs(linear1.getAlpha() - 1) < 0.01f) {
							loopedTimer.stop();
						} else {
							linear1.setAlpha(linear1.getAlpha() + 0.05f);
						}
					});
				});
			}
			
			return _view;
		}
	}
	
	public class Gridview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.wallpaperlist, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final FrameLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView wallimage = _view.findViewById(R.id.wallimage);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView wallname = _view.findViewById(R.id.wallname);
			linear1.setAlpha(0);

			Glide.with(getContext().getApplicationContext()).load(Uri.parse(config.getString("repo", "") + walllist.get((int)_position).get("lowprew").toString())).into(wallimage);
			// If "name" equals a blank space, hide linear3
			if (walllist.get((int)_position).get("name").toString().equals("")) {
				linear3.setVisibility(View.GONE);
				// Also set linear3 background to transparent
				linear3.setBackgroundColor(Color.TRANSPARENT);
				// Set drawable to nothing as well
				linear3.setBackgroundDrawable(null);
				wallname.setText("");
				if (config.getString("debugMode", "").equals("1")) {
					wallname.setText(walllist.get((int)_position).get("name").toString()+"(index:"+_position+")");
					linear3.setVisibility(View.VISIBLE);
					linear3.setBackground(getResources().getDrawable(R.drawable.fade));
				}
			} else {
				linear3.setVisibility(View.VISIBLE);
				linear3.setBackgroundDrawable(getResources().getDrawable(R.drawable.fade));
				wallname.setText(walllist.get((int)_position).get("name").toString());
				if (config.getString("debugMode", "").equals("1")) {
					wallname.setText(walllist.get((int)_position).get("name").toString()+"(index:"+_position+")");
					linear3.setVisibility(View.VISIBLE);
					linear3.setBackground(getResources().getDrawable(R.drawable.fade));
				}
			}
			linear2.setClipToOutline(true);

			if (config.getString("disableanims", "").equals("1")) {
				linear1.setVisibility(View.VISIBLE);
				linear1.setAlpha(1);
			} else {
				EzTimer.runWithDelay(((int) _position) + 100, () -> {
					EzTimerLooped loopedTimer = new EzTimerLooped();
					loopedTimer.start(1, () -> {
						if (Math.abs(linear1.getAlpha() - 1) < 0.01f) {
							loopedTimer.stop();
						} else {
							linear1.setAlpha(linear1.getAlpha() + 0.05f);
						}
					});
				});
			}
			return _view;
		}
	}
}
