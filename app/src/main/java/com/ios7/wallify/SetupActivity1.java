package com.ios7.wallify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class SetupActivity1 extends AppCompatActivity {
    private TextView textView9;
    private Button button;
    private int funny = 0;
    private int easteregg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setup1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               easteregg = 1;
            }
            }, 2000);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (easteregg == 1) {
                    if (funny == 1) {
                        finish();
                    } else {
                        textView9 = findViewById(R.id.textView9);
                        button.setVisibility(View.GONE);
                        textView9.setText("OOPS I THINK I ACCIDENTALLY ATE YOUR BUTTON\nGIVE ME A MINUTE LET ME FIND IT");
                        // Start a timer for 5 seconds
                        new android.os.Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                button.setVisibility(View.VISIBLE);
                                textView9.setText("\n\n\n\n\nHERE IS YOUR BUTTON MY BAD MAN");
                                funny = 1;
                            }
                        }, 5000); // 5000 milliseconds = 5 seconds
                    }
                } else {
                    finish();
                }
            }
        });

    }
}