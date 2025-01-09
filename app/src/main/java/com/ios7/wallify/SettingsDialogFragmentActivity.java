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
import de.hdodenhof.circleimageview.*;
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
	
	private SharedPreferences config;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.settings_dialog_fragment, _container, false);
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
		circleimageview1 = _view.findViewById(R.id.circleimageview1);
		textview4 = _view.findViewById(R.id.textview4);
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
		// Remove the timeout option since it's unnecessary now as we are using Glide onResourceReady instead
		linear4.setVisibility(View.GONE);
		textview5.setVisibility(View.GONE);
	}
}
