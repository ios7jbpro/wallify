package com.ios7.wallify;

import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.DialogFragment;

import com.ios7.wallify.MyClasses.EzTimer;
import com.ios7.wallify.MyClasses.EzTimerLooped;

import de.hdodenhof.circleimageview.*;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.*;

public class SettingsDialogFragmentActivity extends DialogFragment {

	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview5;
	private TextView textview2;
	private TextView textview3;
	private LinearLayout linear5;
	private CircleImageView circleimageview1;
	private TextView textview4;
	private TextView textviewtipsloading;
	private Switch switchColorPreviews;
	private Switch switchDisableAnims;
	private Switch switchDisableBlur;
	private ListView listView;
	private LinearLayout linear30;
	private LinearLayout linearReinitSetup;

	private SharedPreferences config;
	private Intent repolauncher;
	private int totalTips;

	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.settings_dialog_fragment, _container, false);
		listView = _view.findViewById(R.id.listView);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}

	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		textview5 = _view.findViewById(R.id.textview5);
		textview2 = _view.findViewById(R.id.textview2);
		textview3 = _view.findViewById(R.id.textview3);
		textviewtipsloading = _view.findViewById(R.id.textviewtipsloading);
		textview3.setVisibility(View.GONE);
		linear5 = _view.findViewById(R.id.linear5);
		switchColorPreviews = _view.findViewById(R.id.switchColorPreviews);
		switchDisableAnims = _view.findViewById(R.id.switchDisableAnims);
		switchDisableBlur = _view.findViewById(R.id.switchDisableBlur);
		circleimageview1 = _view.findViewById(R.id.circleimageview1);
		textview4 = _view.findViewById(R.id.textview4);
		linear30 = _view.findViewById(R.id.linear30);
		linearReinitSetup = _view.findViewById(R.id.linearReinitSetup);
		config = getContext().getSharedPreferences("config", Activity.MODE_PRIVATE);

	}

	private void initializeLogic() {
		linear30.setVisibility(View.GONE);
		if (config.getString("colorextraction", "").equals("1")) {
			switchColorPreviews.setChecked(true);
		}
		if (config.getString("disableanims", "").equals("1")) {
			switchDisableAnims.setChecked(true);
		}
		if (config.getString("disableblur", "").equals("1")) {
			switchDisableBlur.setChecked(true);
		}

		// Listen for switchColorPreviews on check changed
		switchColorPreviews.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					config.edit().putString("colorextraction", "1").commit();
				} else {
					config.edit().putString("colorextraction", "0").commit();
				}
			}
		});
		switchDisableAnims.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b) {
					config.edit().putString("disableanims", "1").commit();
				} else {
					config.edit().putString("disableanims", "0").commit();
				}
			}
		});

		switchDisableBlur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b) {
					config.edit().putString("disableblur", "1").commit();
				} else {
					config.edit().putString("disableblur", "0").commit();
				}
			}
		});

		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (config.getString("debugMode", "").equals("1")) {
					ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
					ClipData clipData = ClipData.newPlainText("DEBUG", textview3.getText());
					clipboardManager.setPrimaryClip(clipData);
				} else {
					String remoterepo = "https://github.com/j1459863h/wallify-walls/";
					Intent repolauncher = new Intent(Intent.ACTION_VIEW, Uri.parse(remoterepo));
					startActivity(repolauncher);
				}
			}
		});

		// Set textview2 to app's current version
		// Check the current app version via package name first
		//try {
		//	String versionName = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
		//	textview2.setText("Wallify " + versionName);
		//} catch (PackageManager.NameNotFoundException e) {
		// Forget the above, lets extend it a bit more.
		// Get the app name from strings and then the version number like above then set textview2 to be so using the same try and catch condition
		try {
			String appName = getContext().getString(R.string.app_name);
			String versionName = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
			textview2.setText(appName + " " + versionName);
		} catch (PackageManager.NameNotFoundException e) {
		}

		String url = config.getString("repo", "") + "devs.json";
		Request request = new Request.Builder().url(url).build();
		OkHttpClient client = new OkHttpClient();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(@NonNull Call call, @NonNull IOException e) {
				Log.e("FETCH_ERROR", e.getMessage());
			}

			@Override
			public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
				// Ensure the response is successful
				if (!response.isSuccessful()) {
					Log.e("FETCH_ERROR", "Request failed");
					return;
				}

				// Get the JSON string from the response
				String json = response.body().string();

				try {
					JSONArray jsonArray = new JSONArray(json);
					List<Developer> developerList = new ArrayList<>();

					// Iterate over the JSON array and create Developer objects
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String name = jsonObject.getString("name");
						String imageUrl = jsonObject.getString("pfp");
						setListViewHeightBasedOnChildren(listView);
						try {
							String devUrl = jsonObject.getString("url");
						} catch (JSONException e) {
							Log.e("FETCH_ERROR", "JSON parsing error. Setting devurl to nothing." + "Error at position:" + i, e);
							String devUrl = "";
						}
						Developer developer = new Developer(name, imageUrl);
						developerList.add(developer);
						setListViewHeightBasedOnChildren(listView);
					}

					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							DeveloperAdapter adapter = new DeveloperAdapter(getContext(), developerList);
							listView.setAdapter(adapter);
							listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
								@Override
								public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
									// Get the clicked Developer object from the adapters
									Developer clickedDeveloper = (Developer) parent.getItemAtPosition(position);

									// Now you have access to the clickedDeveloper's data
									// For example, you can get their name or image URL:
									String name = clickedDeveloper.getName();
									String imageUrl = clickedDeveloper.getImageUrl();
									String devUrl = clickedDeveloper.getDevUrl();
									Toast.makeText(getContext(), "Clicked on: " + name + ", URL:" + devUrl, Toast.LENGTH_SHORT).show();
									if (devUrl == null || devUrl.isEmpty()) {
										Toast.makeText(getContext(), "No URL found for this developer.", Toast.LENGTH_SHORT).show();
									} else {
										Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(devUrl));
										startActivity(intent);
									}

								}
							});
						}
					});
					setListViewHeightBasedOnChildren(listView);

				} catch (JSONException e) {
					Log.e("FETCH_ERROR", "JSON parsing error", e);
				}
				setListViewHeightBasedOnChildren(listView);
				EzTimer.runWithDelay(100, () -> {
					setListViewHeightBasedOnChildren(listView);
				});
			}
		});
		setListViewHeightBasedOnChildren(listView);

		// Listen for linear30 clicks
		linear30.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// Inflate the custom layout
				LayoutInflater inflater = LayoutInflater.from(requireContext());
				View customView = inflater.inflate(R.layout.abandon_dialog, null);

				// Find views inside the custom layout
				TextView closebtn = customView.findViewById(R.id.closebtn);
				TextView repobtn = customView.findViewById(R.id.repobtn);

				// Create a dialog and set the custom view
				AlertDialog.Builder _builder = new AlertDialog.Builder(requireContext());
				_builder.setView(customView);

				// Show the dialog
				AlertDialog dialog = _builder.create();
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				dialog.show();

				closebtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				// Handle button click to open the GitHub repository
				repobtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Uri uri = Uri.parse("https://github.com/ios7jbpro/wallify");
						// Launch to the url using Intent
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
						dialog.dismiss();

					}
				});
			}
		});

		linearReinitSetup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent intent = new Intent(getContext(), SetupActivity1.class);
				startActivity(intent);
			}
		});

		if (config.getString("debugMode", "").equals("1")) {
			textview2.setText("DEBUG");
			linear30.setVisibility(View.GONE);
			listView.setVisibility(View.GONE);
			textviewtipsloading.setVisibility(View.GONE);
			textview3.setVisibility(View.VISIBLE);
			textview3.setText("Loading debug values...\nStarting a timer...");
			EzTimerLooped loopedTimer15 = new EzTimerLooped();
			loopedTimer15.start(50, () -> {
				String repoval = config.getString("repo", "");
				String timeoutval = config.getString("timeout", "");
				String colorextractionval = ("(enforced on debug)" + config.getString("colorextraction", ""));
				String disableanimsval = ("(enforced on debug)" + config.getString("disableanims", ""));
				String setupcompleteval = config.getString("setupcomplete", "");
				String debugmodeval = config.getString("debugMode", "");
				String endOutput = ("repo:" + repoval + "\ntimeoutval:" + timeoutval + "\ncolorextraction:" + colorextractionval + "\ndisableanims:" + disableanimsval + "\nsetupcomplete:" + setupcompleteval + "\ndebugMode:" + debugmodeval + "\n*USING DEBUG WILL RESET SOME OF THE FLAGS*");
				textview3.setText(endOutput);
				switchDisableAnims.setChecked(true);
				switchColorPreviews.setChecked(true);
			});
		} else {
			linearReinitSetup.setVisibility(View.GONE);
		}
		tipsLoader();
		textview3.setClipToOutline(true);
	}

	private void tipsLoader() {
		textview3.findViewById(R.id.textview3);
		textview3.setText("TipsLoader service started.\nWaiting for the remote fetch, this text will update itself...\n\nClick me to see the wallpapers repository");
		OkHttpClient client = new OkHttpClient();
		String url = (config.getString("repo", "") + "tips/total");
		Request request = new Request.Builder()
					.url(url)
					.build();

		// Try to counteract the issue of hireachy by using try and catch
		try {
			client.newCall(request).enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					// Sub-workaround
					try {
						textviewtipsloading.setText("Cannot reach tips service");
						textview3.setText("Cannot reach tips service");
					} catch (Exception ex) {
						textviewtipsloading.setText("TipsLoader at SettingsDialogFragmentActivity has failed. Check logs and report this.");
						textview3.setText("TipsLoader at SettingsDialogFragmentActivity has failed. Check logs and report this.");
						Log.e("TipsLoader","Crash detected:" + ex);
					}
					e.printStackTrace();
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					if (response.isSuccessful()) {
						final String responseBody = response.body().string();
						try {
							totalTips = Integer.parseInt(responseBody.trim());
						} catch (NumberFormatException e) {
							Log.e("NumberFormatException", "Error parsing totalTips: " + e.getMessage());
							Log.e("NumberFormatException", "Response body: " + responseBody);
							Log.e("NumberFormatException", "Setting totalTips to 5 as a fallback");
							totalTips = 5;
						}
						Random rand = new Random();
						int randomNum = rand.nextInt(totalTips) + 1;
						Request request = new Request.Builder()
								.url((config.getString("repo", "") + "tips/" + randomNum))
								.build();

						client.newCall(request).enqueue(new Callback() {
							@Override
							public void onFailure(Call call, IOException e) {
								textviewtipsloading.setText("Cannot reach tips service");
								textview3.setText("Cannot reach tips service");
								e.printStackTrace();
							}

							@Override
							public void onResponse(Call call, Response response) throws IOException {
								if (response.isSuccessful()) {
									final String responseBody = response.body().string();
									requireActivity().runOnUiThread(new Runnable() {
										@Override
										public void run() {
											textview3.setText(responseBody);
										}
									});
								}
							}
						});
					}
					new FetchCommitMessageTask().execute();
				}
			});
		} catch (Exception e) {
			textview3.setText("TipsLoader at SettingsDialogFragmentActivity has failed. Check logs.");
		}
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) return;

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(
					View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED),
					View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
			);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	// GPT generated, I can't be botheres with jsonobjects sorry not sorry
	private class FetchCommitMessageTask extends AsyncTask<Void, Void, String> {
		boolean tipsFailed;
		@Override
		protected String doInBackground(Void... voids) {
			try {
				URL url = new URL("https://api.github.com/repos/ios7jbpro/wallify/commits/main");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestProperty("User-Agent", "Wallify-App"); // GitHub requires this
				connection.connect();

				InputStream inputStream = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				StringBuilder jsonBuilder = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					jsonBuilder.append(line);
				}

				// Convert JSON string to JSONObject
				JSONObject json = new JSONObject(jsonBuilder.toString());
				JSONObject commit = json.getJSONObject("commit");
				String message = commit.getString("message");

				tipsFailed = false;

				return message;

			} catch (Exception e) {
				e.printStackTrace();
				return "Failed to fetch commit message";
			}
		}
		@Override
		protected void onPostExecute(String message) {
			// My own code to connect to TipsLoader, and add the commit message.
			if (tipsFailed == false) {
				textview3.setText(textview3.getText() + "\n\nLast commit message:" + message + "\n\nClick me to see the wallpapers repository");
				textviewtipsloading.setVisibility(View.GONE);
				textview3.setVisibility(View.VISIBLE);
			} else {
				textviewtipsloading.setText("TipsLoader service failed, check logs");
			}
		}
	}


}
