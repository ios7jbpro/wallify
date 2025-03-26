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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.*;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class SettingsDialogFragmentActivity extends DialogFragment {
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview5;
	private LinearLayout linear4;
	private TextView textview2;
	private LinearLayout linear3;
	private TextView textview3;
	private TextView textview6;
	private LinearLayout linear5;
	private EditText edittext1;
	private TextView textview7;
	private CircleImageView circleimageview1;
	private TextView textview4;
	private Switch switchColorPreviews;
	private ListView listView;
	private LinearLayout linear30;
	
	private SharedPreferences config;
	
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
		linear4 = _view.findViewById(R.id.linear4);
		textview2 = _view.findViewById(R.id.textview2);
		linear3 = _view.findViewById(R.id.linear3);
		textview3 = _view.findViewById(R.id.textview3);
		textview6 = _view.findViewById(R.id.textview6);
		linear5 = _view.findViewById(R.id.linear5);
		edittext1 = _view.findViewById(R.id.edittext1);
		textview7 = _view.findViewById(R.id.textview7);
		switchColorPreviews = _view.findViewById(R.id.switchColorPreviews);
		circleimageview1 = _view.findViewById(R.id.circleimageview1);
		textview4 = _view.findViewById(R.id.textview4);
		linear30 = _view.findViewById(R.id.linear30);
		config = getContext().getSharedPreferences("config", Activity.MODE_PRIVATE);
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				config.edit().putString("timeout", _charSeq).commit();
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});

	}

	private void initializeLogic() {
		edittext1.setText(config.getString("timeout", ""));
		if (config.getString("colorextraction", "").equals("1")) {
			switchColorPreviews.setChecked(true);
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

		// Remove the timeout option since it's unnecessary now as we are using Glide onResourceReady instead
		linear4.setVisibility(View.GONE);
		linear3.setVisibility(View.GONE);

		// Create a network request to the json we have in Github with all the methods
		// The URL
		String url = "https://raw.githubusercontent.com/ios7jbpro/wallify-walls/refs/heads/main/devs.json";
		// Create a new request to the URL
		Request request = new Request.Builder().url(url).build();
		// Create a new client
		OkHttpClient client = new OkHttpClient();
		// Do the call
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

				// Use a JSON parsing library (like Gson or JSONObject)
				try {
					JSONArray jsonArray = new JSONArray(json);
					// Initialize the list before adding items
					List<Developer> developerList = new ArrayList<>();

					// Iterate over the JSON array and create Developer objects
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String name = jsonObject.getString("name");
						String imageUrl = jsonObject.getString("pfp");
						Developer developer = new Developer(name, imageUrl);
						// Add the developer to the list
						developerList.add(developer);
					}

					// Now, update the UI with the list of developers
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// Create and set the custom adapter
							DeveloperAdapter adapter = new DeveloperAdapter(getContext(), developerList);
							listView.setAdapter(adapter);
						}
					});

				} catch (JSONException e) {
					Log.e("FETCH_ERROR", "JSON parsing error", e);
				}
			}

		});

		// Listen for linear30 clicks
		linear30.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// Inflate the custom layout
				LayoutInflater inflater = LayoutInflater.from(requireContext());
				View customView = inflater.inflate(R.layout.abandon_dialog, null);

				// Find views inside the custom layout
				TextView closebtn = customView.findViewById(R.id.closebtn);

				// Create a dialog and set the custom view
				AlertDialog.Builder _builder = new AlertDialog.Builder(requireContext());
				_builder.setView(customView);

				// Show the dialog
				AlertDialog dialog = _builder.create();
				dialog.show();

				// Handle button click to dismiss dialog
				closebtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			}
		});

	}
}
