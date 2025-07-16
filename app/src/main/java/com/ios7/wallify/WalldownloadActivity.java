package com.ios7.wallify;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipboardManager;
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
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.attribute.FileTime;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import androidx.palette.graphics.Palette;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

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
	private TextView button3;
	private TextView button4;
	private TextView textViewLoading;
	private ProgressBar progress_bar_loading;
	private LinearLayout color1;
	private LinearLayout color2;
	private LinearLayout color3;
	private LinearLayout color4;
	private LinearLayout color5;
	private LinearLayout color6;
	private TextView textView5;
	private LinearLayout colorpreviews;
	private LinearLayout colorpreviewsloading;
	
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
	private TextView textViewCrop;
	private Intent intentCrop;
	private TextView textView3;
	private String mixedUrl;
	private String allhexvals;
	private Intent legacyWallLauncher;
	
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
		textViewLoading = findViewById(R.id.textViewLoading);
		progress_bar_loading = findViewById(R.id.progress_bar_loading);
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
		button3 = findViewById(R.id.button3);
		button4 = findViewById(R.id.button4);
		color1 = findViewById(R.id.color1);
		color2 = findViewById(R.id.color2);
		color3 = findViewById(R.id.color3);
		color4 = findViewById(R.id.color4);
		color5 = findViewById(R.id.color5);
		color6 = findViewById(R.id.color6);
		textView5 = findViewById(R.id.textView5);
		textView3 = findViewById(R.id.textView3);
		colorpreviews = findViewById(R.id.colorpreviews);
		textViewCrop = findViewById(R.id.textViewCrop);
		colorpreviewsloading = findViewById(R.id.colorpreviewsloading);
		selectedItemList = getSharedPreferences("selectedItemList", Activity.MODE_PRIVATE);
		fetchJson = new RequestNetwork(this);
		downloadWall = new RequestNetwork(this);
		wallLink = getSharedPreferences("wallLink", Activity.MODE_PRIVATE);
		config = getSharedPreferences("config", Activity.MODE_PRIVATE);
		temporaryCache = getSharedPreferences("temporaryCache", Activity.MODE_PRIVATE);

		textViewCrop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// Launch the crop activity
				Intent intentCrop = new Intent(getApplicationContext(), CropWallpaper.class);
				// Set an activity key that contains the sharedprefs so it can be loaded in the next activity directly
				intentCrop.putExtra("link", Uri.parse(config.getString("repo", "") + walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString()));
				startActivity(intentCrop);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intentDownloadRemoteWall.setAction(Intent.ACTION_VIEW);
				intentDownloadRemoteWall.setData(Uri.parse(config.getString("repo", "") + walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString()));
				startActivity(intentDownloadRemoteWall);
			}
		});

		button1.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				if (config.getString("debugMode", "").equals("1")) {
					ClipboardUtils.copyTextToClipboard(getApplicationContext(), config.getString("repo", "") + wallLink.getString("wallLink", ""));
					return true;
				} else {
					return false;
				}
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Glide.with(getApplicationContext())
						.asBitmap() // Directly load the image as a Bitmap
						.load(Uri.parse(config.getString("repo", "") + wallLink.getString("wallLink", "")))
						.into(new SimpleTarget<Bitmap>() {
							@Override
							public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
								WallpaperManager wallManager = WallpaperManager.getInstance(getApplicationContext());
								try {
									Toast.makeText(getApplicationContext(), "Loading in high-res and setting wallpaper...", Toast.LENGTH_SHORT).show();
									wallManager.clear(); // Clear the existing wallpaper
									wallManager.setBitmap(resource); // Set the loaded Bitmap as wallpaper
								} catch (IOException ex) {
									ex.printStackTrace(); // Handle exceptions
									Toast.makeText(getApplicationContext(),  "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
								}
							}

							@Override
							public void onLoadFailed(@Nullable Drawable errorDrawable) {
								// Handle load failure (optional: show a toast or log the error)
							}
						});

			}
		});

		button2.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				if (config.getString("debugMode", "").equals("1")) {
					ClipboardUtils.copyTextToClipboard(getApplicationContext(), allhexvals);
					return true;
				} else {
					Toast.makeText(getApplicationContext(), "Using the legacy wallpaper loader", Toast.LENGTH_SHORT).show();
					wallLink.edit().putString("wallLink", config.getString("repo", "")+walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString()).commit();
					Intent legacyWallLauncher = new Intent(getApplicationContext(), Setwall1Activity.class);
					startActivity(legacyWallLauncher);
					return true;
				}
			}
		});

		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view){
				Log.d("SHARE", "Attempting to share:"+config.getString("repo", "")+getIntent().getStringExtra("wallpaperLink"));
				ShareCompat.IntentBuilder
						.from(WalldownloadActivity.this)
						.setText(config.getString("repo", "")+getIntent().getStringExtra("wallpaperLink"))
						.setType("text/plain")
						.setChooserTitle("Share URL with")
						.startChooser();
			}
		});

		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v){
				String imageUrl = config.getString("repo", "")+getIntent().getStringExtra("wallpaperLink");

				Glide.with(v.getContext())
						.asFile()
						.load(imageUrl)
						.into(new CustomTarget<File>() {
							@Override
							public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
								Uri contentUri = FileProvider.getUriForFile(
										v.getContext(),
										"com.ios7.wallify.fileprovider",
										resource
								);

								if (contentUri != null) {
									Intent shareIntent = new Intent(Intent.ACTION_SEND);
									shareIntent.setType("image/jpeg");
									shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
									shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
									v.getContext().startActivity(Intent.createChooser(shareIntent, "Share Image"));
								}
							}

							@Override
							public void onLoadCleared(@Nullable Drawable placeholder) {
								// no cleanup needed here for this use case
							}
						});
			}
		});

		_fetchJson_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				walljsonlistmap = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				Log.d("WallpaperDebug", "Name = '" + config.getString("wallpaperName", "") + "'");
				// Check if categoryName under config equals nothing
				if (config.getString("wallpaperName", "").equals("")) {
					textview1.setText(config.getString("categoryName", ""));
				}
				// mixedUrl = config.getString("repo", "") + walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString();
				// Log.d("WallpaperDebug", "Mixed URL = '" + mixedUrl + "'");
				try {
					Glide.with(getApplicationContext())
							.load(Uri.parse(config.getString("repo", "") + walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", "0"))).get("lowprew").toString()))
							.into(new SimpleTarget<Drawable>() {
								@Override
								public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
									imageview1.setImageDrawable(resource);

									Drawable drawable = imageview1.getDrawable();
									if (!(drawable instanceof BitmapDrawable)) {
										Log.e("WallpaperDebug", "Drawable is not a BitmapDrawable");
										showColorExtractionFailed();
										return;
									}

									Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
									if (bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
										Log.e("WallpaperDebug", "Bitmap is null or empty");
										showColorExtractionFailed();
										return;
									}

									Palette.from(bitmap).generate(palette -> {
										int defaultColor = Color.DKGRAY;

										int vibrant = palette.getDominantColor(defaultColor);
										int muted = palette.getMutedColor(defaultColor);
										int vibrantLight = palette.getLightVibrantColor(defaultColor);
										int vibrantDark = palette.getDarkVibrantColor(defaultColor);
										int mutedLight = palette.getLightMutedColor(defaultColor);
										int mutedDark = palette.getDarkMutedColor(defaultColor);

										if (config.getString("debugMode", "").equals("1")) {
											textview4.setText(vibrant + " " + muted + " " + mutedDark + " " + mutedLight + " " + vibrantLight + " " + vibrantDark);
											textview4.setTextSize(8);
											String vibrantHex = String.format("#%08X", vibrant);
											String mutedHex = String.format("#%08X", muted);
											String vibrantLightHex = String.format("#%08X", vibrantLight);
											String vibrantDarkHex = String.format("#%08X", vibrantDark);
											String mutedLightHex = String.format("#%08X", mutedLight);
											String mutedDarkHex = String.format("#%08X", mutedDark);

											textview4.setText("INTERNAL:"+ vibrant + " " + muted + " " + mutedDark + " " + mutedLight + " " + vibrantLight + " " + vibrantDark+"\nHEX:" +vibrantHex + " " + mutedHex + " " + mutedDarkHex + " " + mutedLightHex + " " + vibrantLightHex + " " + vibrantDarkHex+"\n*VALUE AUTOMATICALLY COPIED TO CLIPBOARD*");
											// Copy all of the values to clipboard
											ClipboardUtils.copyTextToClipboard(getApplicationContext(), "INTERNAL:"+ vibrant + " " + muted + " " + mutedDark + " " + mutedLight + " " + vibrantLight + " " + vibrantDark+"\nHEX:" +vibrantHex + " " + mutedHex + " " + mutedDarkHex + " " + mutedLightHex + " " + vibrantLightHex + " " + vibrantDarkHex);
											allhexvals = ("INTERNAL:"+ vibrant + " " + muted + " " + mutedDark + " " + mutedLight + " " + vibrantLight + " " + vibrantDark+"\nHEX:" +vibrantHex + " " + mutedHex + " " + mutedDarkHex + " " + mutedLightHex + " " + vibrantLightHex + " " + vibrantDarkHex);
											button1.setText("Download\nHOLD FOR URL");
											button2.setText("Set wallpaper\nHOLD FOR HEX");
										}

										int[] colors = {vibrant, muted, mutedDark, mutedLight, vibrantLight, vibrantDark};

										// Check if any extracted color is fully transparent (alpha == 0)
										for (int c : colors) {
											if (Color.alpha(c) == 0) {
												Log.e("WallpaperDebug", "Color extraction failed: transparent color detected");
												showColorExtractionFailed();
												return;
											}
										}

										// Set background colors
										color1.setBackgroundColor(vibrant);
										color2.setBackgroundColor(muted);
										color3.setBackgroundColor(mutedDark);
										color4.setBackgroundColor(mutedLight);
										color5.setBackgroundColor(vibrantLight);
										color6.setBackgroundColor(vibrantDark);

										// Set text colors
										textview2.setTextColor(vibrantLight);
										// time2.setTextColor(vibrantLight); // Uncomment if you want

										// Show color previews and hide loading/failure messages
										colorpreviews.setVisibility(View.VISIBLE);
										colorpreviewsloading.setVisibility(View.GONE);
										textView5.setVisibility(View.GONE);

										// Copy to clipboard on color click
										setupColorClick(color1, vibrant);
										setupColorClick(color2, muted);
										setupColorClick(color3, mutedDark);
										setupColorClick(color4, mutedLight);
										setupColorClick(color5, vibrantLight);
										setupColorClick(color6, vibrantDark);

										// Handle user preference for disabling color extraction
										if (config.getString("colorextraction", "").equals("0")) {
											colorpreviews.setVisibility(View.GONE);
											colorpreviewsloading.setVisibility(View.GONE);
											textView3.setVisibility(View.GONE);
											textview2.setTextColor(Color.WHITE);
											// time2.setTextColor(Color.WHITE); // Uncomment if used
										}
									});
								}

								@Override
								public void onLoadFailed(@Nullable Drawable errorDrawable) {
									Log.e("WallpaperDebug", "Glide image load failed");
									showColorExtractionFailed();
								}

								private void showColorExtractionFailed() {
									// Hide all color views
									color1.setVisibility(View.GONE);
									color2.setVisibility(View.GONE);
									color3.setVisibility(View.GONE);
									color4.setVisibility(View.GONE);
									color5.setVisibility(View.GONE);
									color6.setVisibility(View.GONE);

									// Show failure message
									textView5.setVisibility(View.VISIBLE);

									// Set default text colors to visible state
									textview2.setTextColor(Color.WHITE);
									// time2.setTextColor(Color.WHITE); // Uncomment if used

									// Hide previews and loading spinner if visible
									colorpreviews.setVisibility(View.GONE);
									colorpreviewsloading.setVisibility(View.GONE);
								}

								private void setupColorClick(View colorView, int color) {
									colorView.setOnClickListener(v -> {
										String hexColor = String.format("#%06X", (0xFFFFFF & color));
										ClipboardUtils.copyTextToClipboard(getApplicationContext(), hexColor);
									});
								}
							});

				} catch (Exception e) {
					Log.e("WallpaperDebug", "Exception: " + e.getMessage());
					//Log.e("WallpaperDebug", "Tried to load in:" + config.getString("repo", "") + walljsonlistmap.get((int)Integer.parseInt(selectedItemList.getString("selectedWall", "0"))).get("lowprew").toString());
					Log.e("WallpaperDebug", "Detected values:");
					Log.e("WallpaperDebug", "repo:" + config.getString("repo", ""));
					Log.e("WallpaperDebug", "selectedWall:" + selectedItemList.getString("selectedWall", ""));
				}

				try {
					Glide.with(getApplicationContext())
							.load(Uri.parse(config.getString("repo", "") + walljsonlistmap.get((int) Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("lowprew").toString()))
							.into(new SimpleTarget<Drawable>() {
								@Override
								public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
									imageview3.setImageDrawable(resource); // Set the image resource into the ImageView
								}

								@Override
								public void onLoadFailed(@Nullable Drawable errorDrawable) {
								}
							});
				} catch (Exception e) {
					Log.e("WallpaperDebug", "Exception: " + e.getMessage());
					//Log.e("WallpaperDebug", "Tried to load in:" + config.getString("repo", "") + walljsonlistmap.get((int)Integer.parseInt(selectedItemList.getString("selectedWall", "0"))).get("lowprew").toString());
					Log.e("WallpaperDebug", "Detected values:");
					Log.e("WallpaperDebug", "repo:" + config.getString("repo", ""));
					Log.e("WallpaperDebug", "selectedWall:" + selectedItemList.getString("selectedWall", ""));
				}
				try {
					textview1.setText(walljsonlistmap.get((int) Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("name").toString());
				} catch (Exception e) {
					Log.e("WallpaperDebug", "Exception: " + e.getMessage());
					//Log.e("WallpaperDebug", "Tried to load in:" + config.getString("repo", "") + walljsonlistmap.get((int)Integer.parseInt(selectedItemList.getString("selectedWall", "0"))).get("lowprew").toString());
					Log.e("WallpaperDebug", "Detected values:");
					Log.e("WallpaperDebug", "repo:" + config.getString("repo", ""));
					Log.e("WallpaperDebug", "selectedWall:" + selectedItemList.getString("selectedWall", ""));
				}
				// Check if categoryName under config equals nothing
				if (config.getString("wallpaperName", "").equals("")) {
					textview1.setText(config.getString("categoryName", ""));
				}
				try {
					wallLink.edit().putString("wallLink", walljsonlistmap.get((int) Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString()).commit();
				} catch (Exception e) {
					Log.e("WallpaperDebug", "Exception: " + e.getMessage());
					//Log.e("WallpaperDebug", "Tried to load in:" + config.getString("repo", "") + walljsonlistmap.get((int)Integer.parseInt(selectedItemList.getString("selectedWall", "0"))).get("lowprew").toString());
					Log.e("WallpaperDebug", "Detected values:");
					Log.e("WallpaperDebug", "repo:" + config.getString("repo", ""));
					Log.e("WallpaperDebug", "selectedWall:" + selectedItemList.getString("selectedWall", ""));
				}
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
		fetchJson.startRequestNetwork(RequestNetworkController.GET, config.getString("repo", "") + temporaryCache.getString("directrepo", ""), "", _fetchJson_request_listener);
		Log.d("WallpaperDebug", "Starting request to:" + config.getString("repo", "") + temporaryCache.getString("directrepo", ""));
		linear7.setClipToOutline(true);
		linear9.setClipToOutline(true);

		// Palette palette = Palette.from(imageview1.getDrawable()).getBitmap());
		// int vibrantColor = palette.getVibrantColor(0x000000);
		// color1.setBackgroundColor(vibrantColor);

		// Clip the croptext so the content inside clips to the rounded drawable bg
		textViewCrop.setClipToOutline(true);

		colorpreviews.setVisibility(View.GONE);

		/* new Handler(Looper.getMainLooper()).postDelayed(() -> {
		Bitmap bitmap = ((BitmapDrawable) imageview1.getDrawable()).getBitmap();
			Palette.from(bitmap).generate(palette -> {
				int vibrant = palette.getDominantColor(0x000000); // <=== color you want
				int vibrantLight = palette.getLightVibrantColor(0x000000);
				int vibrantDark = palette.getDarkVibrantColor(0x000000);
				int muted = palette.getMutedColor(0x000000);
				int mutedLight = palette.getLightMutedColor(0x000000);
				int mutedDark = palette.getDarkMutedColor(0x000000);
				color1.setBackgroundColor(vibrant);
				color2.setBackgroundColor(muted);
				textview2.setTextColor(vibrantLight);
				// time2.setTextColor(vibrantLight);
				color3.setBackgroundColor(mutedDark);
				color4.setBackgroundColor(mutedLight);
				color5.setBackgroundColor(vibrantLight);
				color6.setBackgroundColor(vibrantDark);
				color1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String hexColor = String.format("#%06X", (0xFFFFFF & vibrant));
						ClipboardUtils.copyTextToClipboard(getApplicationContext(), String.valueOf(hexColor));
					}
				});
				color2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String hexColor = String.format("#%06X", (0xFFFFFF & muted));
						ClipboardUtils.copyTextToClipboard(getApplicationContext(), String.valueOf(hexColor));
					}
				});
				color3.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String hexColor = String.format("#%06X", (0xFFFFFF & mutedDark));
						ClipboardUtils.copyTextToClipboard(getApplicationContext(), String.valueOf(hexColor));
					}
				});
				color4.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String hexColor = String.format("#%06X", (0xFFFFFF & mutedLight));
						ClipboardUtils.copyTextToClipboard(getApplicationContext(), String.valueOf(hexColor));
					}
				});
				color5.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String hexColor = String.format("#%06X", (0xFFFFFF & vibrantLight));
						ClipboardUtils.copyTextToClipboard(getApplicationContext(), String.valueOf(hexColor));
					}
				});
				color6.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String hexColor = String.format("#%06X", (0xFFFFFF & vibrantDark));
						ClipboardUtils.copyTextToClipboard(getApplicationContext(), String.valueOf(hexColor));
					}
				});

				// Hide the entire layout if colorextraction sharedPrefs is set to 0
				if (config.getString("colorextraction", "").equals("0")) {
					colorpreviews.setVisibility(View.GONE);
					colorpreviewsloading.setVisibility(View.GONE);
					textView3.setVisibility(View.GONE);
					// Set the clock widget to be white again as color extraction is ignored
					textview2.setTextColor(Color.WHITE);
					time2.setTextColor(Color.WHITE);
				}
			});
			Log.d("DelayExample", "This code runs after 5 seconds");
			colorpreviews.setVisibility(View.VISIBLE);
			colorpreviewsloading.setVisibility(View.GONE);
			// Hide the entire layout if colorextraction sharedPrefs is set to 0
			if (config.getString("colorextraction", "").equals("0")) {
				colorpreviews.setVisibility(View.GONE);
				colorpreviewsloading.setVisibility(View.GONE);
				textView3.setVisibility(View.GONE);
				// Set the clock widget to be white again as color extraction is ignored
				textview2.setTextColor(Color.WHITE);
				time2.setTextColor(Color.WHITE);
			}
		}, (int)(Double.parseDouble(config.getString("timeout", "")))); */

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
