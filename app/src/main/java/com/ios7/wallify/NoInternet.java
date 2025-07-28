package com.ios7.wallify;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ios7.wallify.MyClasses.EzTimer;

public class NoInternet extends AppCompatActivity {

    private TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_no_internet);
        tx = findViewById(R.id.textView19);
        EzTimer.runWithDelay(1000, () -> {
            tx.setText("No internet connection detected.\nTerminating the app in 5 seconds.");
            EzTimer.runWithDelay(1000, () -> {
                tx.setText("No internet connection detected.\nTerminating the app in 4 seconds.");
                EzTimer.runWithDelay(1000, () -> {
                    tx.setText("No internet connection detected.\nTerminating the app in 3 seconds.");
                    EzTimer.runWithDelay(1000, () -> {
                        tx.setText("No internet connection detected.\nTerminating the app in 2 seconds.");
                        EzTimer.runWithDelay(1000, () -> {
                            tx.setText("No internet connection detected.\nTerminating the app in 1 second.");
                            EzTimer.runWithDelay(1000, () -> {
                                tx.setText("Exiting the app...");
                                EzTimer.runWithDelay(1000, () -> {
                                    finish();
                                });
                            });
                        });
                    });
                });
            });
        });
        }
}