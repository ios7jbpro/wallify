package com.ios7.wallify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ios7.wallify.MyClasses.EzTimer;

public class AppRestarter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_restarter);
        EzTimer.runWithDelay(750, () -> {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            Log.d("MANDEBUG", "Restarting MainActivity");
            startActivity(intent);
            Log.d("MANDEBUG", "Exiting manualdebug enabler");
            finish();
        });
    }
}