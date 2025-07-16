package com.ios7.wallify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ios7.wallify.MyClasses.EzTimer;

public class ManualDebugEnabler extends AppCompatActivity {

    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manual_debug_enabler);
        config = this.getSharedPreferences("config", Activity.MODE_PRIVATE);
        if (config.getString("forcedDebug", "").equals("1")) {
            Log.d("MANDEBUG", "Turning off forcedDebug");
            config.edit().putString("forcedDebug", "0").commit();
            config.edit().putString("debugMode", "0").commit();
        } else {
            Log.d("MANDEBUG", "Turning on forcedDebug");
            config.edit().putString("forcedDebug", "1").commit();
            config.edit().putString("debugMode", "1").commit();
        }
        Log.d("MANDEBUG", "Delaying for 3 seconds");
        EzTimer.runWithDelay(3000, () -> {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            Log.d("MANDEBUG", "Restarting MainActivity");
            startActivity(intent);
            Log.d("MANDEBUG", "Exiting manualdebug enabler");
            finish();
        });
    }
}