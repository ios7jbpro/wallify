package com.ios7.wallify;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.ios7.wallify.MyClasses.EzTimer;
import com.ios7.wallify.MyClasses.EzTimerLooped;


public class SetupActivity1 extends AppCompatActivity {
    private TextView button;
    private TextView nextbutton1;
    private TextView nextbutton2;
    private TextView nextbutton3;
    private LinearLayout backgroundlayout;
    private LinearLayout background_topleft;
    private LinearLayout background_topright;
    private LinearLayout background_bottomleft;
    private LinearLayout background_bottomright;
    private FrameLayout mainframe;
    private LinearLayout SetupProgress1;
    private LinearLayout SetupProgress2;
    private LinearLayout SetupProgress3;
    private int funny = 0;
    private int easteregg = 0;
    private int canStop = 0;
    private SharedPreferences config;
    private Switch switchColorPreviews;
    private Switch switchDisableAnims;

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
        nextbutton1 = findViewById(R.id.nextbutton1);
        nextbutton2 = findViewById(R.id.nextbutton2);
        nextbutton3 = findViewById(R.id.nextbutton3);
        button = findViewById(R.id.skipbutton);
        mainframe = findViewById(R.id.mainframe);
        SetupProgress1 = findViewById(R.id.SetupProgress1);
        SetupProgress2 = findViewById(R.id.SetupProgress2);
        SetupProgress3 = findViewById(R.id.SetupProgress3);
        switchColorPreviews = findViewById(R.id.switchColorPreviews);
        switchDisableAnims = findViewById(R.id.switchDisableAnims);
        backgroundlayout.setClipToOutline(true);
        background_topleft.setClipToOutline(true);
        background_topright.setClipToOutline(true);
        background_bottomleft.setClipToOutline(true);
        background_bottomright.setClipToOutline(true);
        config = getSharedPreferences("config", MODE_PRIVATE);
        SetupProgress2.setVisibility(View.GONE);
        SetupProgress2.setAlpha(0);
        SetupProgress3.setVisibility(View.GONE);
        SetupProgress3.setAlpha(0);
        mainframe.setAlpha(0);
        backgroundlayout.setAlpha(0);
        background_topleft.setAlpha(0);
        background_topright.setAlpha(0);
        background_bottomleft.setAlpha(0);
        background_bottomright.setAlpha(0);
        button.setAlpha(0);
        nextbutton1.setAlpha(0);
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

        EzTimer.runWithDelay(4300, () -> {
            EzTimerLooped loopedTimer8 = new EzTimerLooped();
            loopedTimer8.start(1, () -> {
                if (Math.abs(button.getAlpha() - 1) < 0.01f) {
                    loopedTimer8.stop();
                } else {
                    button.setAlpha(button.getAlpha() + 0.05f);
                }
            });
        });

        EzTimer.runWithDelay(4500, () -> {
            EzTimerLooped loopedTimer8 = new EzTimerLooped();
            loopedTimer8.start(1, () -> {
                if (Math.abs(nextbutton1.getAlpha() - 1) < 0.01f) {
                    loopedTimer8.stop();
                } else {
                    nextbutton1.setAlpha(nextbutton1.getAlpha() + 0.05f);
                }
            });
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    config.edit().putString("setupcomplete", "1").commit();
                    finish();
            }
        });

        nextbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EzTimerLooped loopedTimer9 = new EzTimerLooped();
                loopedTimer9.start(1, () -> {
                    if (Math.abs(SetupProgress1.getAlpha() - 0) < 0.1f) {
                        loopedTimer9.stop();
                    } else {
                        SetupProgress1.setAlpha(SetupProgress1.getAlpha() - 0.08f);
                        Log.d("DEBUG", "Prog1lpha: " + SetupProgress1.getAlpha());
                    }
                });
                EzTimer.runWithDelay(200, () -> {
                    SetupProgress1.setVisibility(View.GONE);
                    SetupProgress2.setVisibility(View.VISIBLE);
                EzTimerLooped loopedTimer10 = new EzTimerLooped();
                loopedTimer10.start(1, () -> {
                    if (Math.abs(SetupProgress2.getAlpha() - 1) < 0.1f) {
                        loopedTimer10.stop();
                    } else {
                        SetupProgress2.setAlpha(SetupProgress2.getAlpha() + 0.08f);
                        Log.d("DEBUG", "Prog2lpha: " + SetupProgress2.getAlpha());
                    }
                });
                });
            }
        });

        nextbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EzTimerLooped loopedTimer15 = new EzTimerLooped();
                loopedTimer15.start(1, () -> {
                    if (Math.abs(SetupProgress2.getAlpha() - 0) < 0.1f) {
                        loopedTimer15.stop();
                    } else {
                        SetupProgress2.setAlpha(SetupProgress2.getAlpha() - 0.08f);
                        Log.d("DEBUG", "Prog2lpha: " + SetupProgress2.getAlpha());
                    }
                });
                EzTimer.runWithDelay(200, () -> {
                    SetupProgress2.setVisibility(View.GONE);
                    SetupProgress3.setVisibility(View.VISIBLE);
                    EzTimerLooped loopedTimer16 = new EzTimerLooped();
                    loopedTimer16.start(1, () -> {
                        if (Math.abs(SetupProgress3.getAlpha() - 1) < 0.1f) {
                            loopedTimer16.stop();
                        } else {
                            SetupProgress3.setAlpha(SetupProgress3.getAlpha() + 0.08f);
                            Log.d("DEBUG", "Prog3lpha: " + SetupProgress3.getAlpha());
                        }
                    });
                });
            }
        });

        nextbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EzTimerLooped loopedTimer18 = new EzTimerLooped();
                loopedTimer18.start(1, () -> {
                    if (Math.abs(SetupProgress3.getAlpha() - 0) < 0.1f) {
                        config.edit().putString("setupcomplete", "1").commit();
                        finish();
                        loopedTimer18.stop();
                    } else {
                        SetupProgress3.setAlpha(SetupProgress3.getAlpha() - 0.08f);
                    }
                });
            }
        });

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

    }
}