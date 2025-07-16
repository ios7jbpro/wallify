package com.ios7.wallify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ios7.wallify.MyClasses.EzTimerLooped;

public class ShareImageLoader extends AppCompatActivity {

    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(false);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_share_image_loader);
        config = getSharedPreferences("config", Activity.MODE_PRIVATE);
        EzTimerLooped loopedTimer = new EzTimerLooped();
        loopedTimer.start(50, () -> {
            if (config.getString("isImageReady", "") == "1") {
                config.edit().putString("isImageReady", "0");
                finish();
                loopedTimer.stop();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do nothing to block back button
        super.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Block touches outside the dialog
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Ignore touches outside
            return true;
        }
        return super.onTouchEvent(event);
    }

}