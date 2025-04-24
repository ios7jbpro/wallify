package com.ios7.wallify;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.ios7.wallify.MyClasses.EzTimer;
import com.ios7.wallify.MyClasses.EzTimerLooped;


public class SetupActivity1 extends AppCompatActivity {
    private TextView textView9;
    private TextView button;
    private LinearLayout backgroundlayout;
    private LinearLayout background_topleft;
    private LinearLayout background_topright;
    private LinearLayout background_bottomleft;
    private LinearLayout background_bottomright;
    private FrameLayout mainframe;
    private int funny = 0;
    private int easteregg = 0;
    private int canStop = 0;
    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setup1);
        backgroundlayout = findViewById(R.id.backgroundlayout);
        background_topleft = findViewById(R.id.background_topleft);
        background_topright = findViewById(R.id.background_topright);
        background_bottomleft = findViewById(R.id.background_bottomleft);
        background_bottomright = findViewById(R.id.background_bottomright);
        mainframe = findViewById(R.id.mainframe);
        backgroundlayout.setClipToOutline(true);
        background_topleft.setClipToOutline(true);
        background_topright.setClipToOutline(true);
        background_bottomleft.setClipToOutline(true);
        background_bottomright.setClipToOutline(true);
        config = getSharedPreferences("config", MODE_PRIVATE);
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //     Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //    return insets;
        //});

        // Disable the easter egg, commenting this out entirely as well. We are building animations now instead.
        //EzTimer.delay(2000, () -> {
        //        easteregg = 1;
        //});
        textView9 = findViewById(R.id.textView9);
        textView9.setVisibility(View.GONE);

        // We will do a fade effect using timers and alpha here.
        mainframe.setAlpha(0);
        backgroundlayout.setAlpha(0);
        background_topleft.setAlpha(0);
        background_topright.setAlpha(0);
        background_bottomleft.setAlpha(0);
        background_bottomright.setAlpha(0);
        EzTimer.runWithDelay(1500, () -> {
                    EzTimerLooped loopedTimer = new EzTimerLooped();
                    loopedTimer.start(25, () -> {
                        if (Math.abs(mainframe.getAlpha() - 1) < 0.01f) {
                            loopedTimer.stop();
                            Log.d("DEBUG", "Timer1 stopped");
                        } else {
                            mainframe.setAlpha(mainframe.getAlpha() + 0.05f);
                            Log.d("APROG", "Mainframe alpha still progressing:");
                            Log.d("DEBUG", "Alpha: " + mainframe.getAlpha());
                        }
            });
        });

        EzTimer.runWithDelay(1700, () -> {
            EzTimerLooped loopedTimer2 = new EzTimerLooped();
            loopedTimer2.start(25, () -> {
                if (Math.abs(backgroundlayout.getAlpha() - 0.2f) < 0.01f) {
                    loopedTimer2.stop();
                    Log.d("DEBUG", "Timer2 stopped");
                } else {
                    backgroundlayout.setAlpha(backgroundlayout.getAlpha() + 0.01f);
                    Log.d("APROG", "Backgroundlayout alpha still progressing:");
                    Log.d("DEBUG", "Alpha: " + backgroundlayout.getAlpha());
                }
            });
        });

        EzTimer.runWithDelay(1900, () -> {
                    EzTimerLooped loopedTimer3 = new EzTimerLooped();
                    loopedTimer3.start(1, () -> {
                        if (Math.abs(background_topleft.getAlpha() - 1) < 0.01f) {
                            loopedTimer3.stop();
                            Log.d("DEBUG", "Timer3 stopped");
                        } else {
                            background_topleft.setAlpha(background_topleft.getAlpha() + 0.01f);
                            Log.d("APROG", "Background_topleft alpha still progressing:");
                            Log.d("DEBUG", "Alpha: " + background_topleft.getAlpha());
                        }
                    });
        });

        EzTimer.runWithDelay(2300, () -> {
            EzTimerLooped loopedTimer4 = new EzTimerLooped();
            loopedTimer4.start(1, () -> {
                if (Math.abs(background_topright.getAlpha() - 1) < 0.01f) {
                    loopedTimer4.stop();
                    Log.d("DEBUG", "Timer4 stopped");
                } else {
                    background_topright.setAlpha(background_topright.getAlpha() + 0.01f);
                    Log.d("APROG", "Background_topright alpha still progressing:");
                    Log.d("DEBUG", "Alpha: " + background_topright.getAlpha());
                }
            });
        });

        EzTimer.runWithDelay(2700, () -> {
            EzTimerLooped loopedTimer5 = new EzTimerLooped();
            loopedTimer5.start(1, () -> {
                if (Math.abs(background_bottomleft.getAlpha() - 1) < 0.01f) {
                    loopedTimer5.stop();
                    Log.d("DEBUG", "Timer5 stopped");
                } else {
                    background_bottomleft.setAlpha(background_bottomleft.getAlpha() + 0.01f);
                    Log.d("APROG", "Background_bottomleft alpha still progressing:");
                    Log.d("DEBUG", "Alpha: " + background_bottomleft.getAlpha());
                }
            });
        });


        EzTimer.runWithDelay(2700, () -> {
            EzTimerLooped loopedTimer6 = new EzTimerLooped();
            loopedTimer6.start(1, () -> {
                if (Math.abs(background_bottomright.getAlpha() - 1) < 0.01f) {
                    loopedTimer6.stop();
                    Log.d("DEBUG", "Timer6 stopped");
                } else {
                    background_bottomright.setAlpha(background_bottomright.getAlpha() + 0.01f);
                    Log.d("APROG", "Background_bottomright alpha still progressing:");
                    Log.d("DEBUG", "Alpha: " + background_bottomright.getAlpha());
                }
            });
        });



        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (easteregg == 1) {
                    if (funny == 1) {
                        config.edit().putString("setupcomplete", "1").commit();
                        finish();
                    } else {
                        button.setVisibility(View.GONE);
                        textView9.setText("OOPS I THINK I ACCIDENTALLY ATE YOUR BUTTON\nGIVE ME A MINUTE LET ME FIND IT");
                        EzTimer.runWithDelay(2000, () -> {
                                button.setVisibility(View.VISIBLE);
                                textView9.setText("\n\n\n\n\nHERE IS YOUR BUTTON MY BAD MAN");
                                funny = 1;
                        });
                    }
                } else {
                    // I AM ACTUALLY REALLY STUPID don't let me be a dev for any corpo
                    config.edit().putString("setupcomplete", "1").commit();
                    finish();
                }
            }
        });

    }
}