package com.ios7.wallify.MyClasses;

import android.os.Handler;
import android.os.Looper;

public class EzTimerLooped {

    private static final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable action;
    private long intervalMillis;
    private boolean isRunning = false;

    // Starts the loop with the provided interval and action
    public void start(long intervalMillis, Runnable action) {
        if (isRunning) {
            return; // Prevent multiple timers from running simultaneously
        }

        this.intervalMillis = intervalMillis;
        this.action = action;
        isRunning = true;

        // Start the loop
        runLoop();
    }

    // Method to run the looped task
    private void runLoop() {
        if (!isRunning) return;

        action.run();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runLoop(); // Keep calling the loop recursively
            }
        }, intervalMillis);
    }

    // Stops the looped timer
    public void stop() {
        isRunning = false;
    }

    // Check if the loop is running
    public boolean isRunning() {
        return isRunning;
    }
}
