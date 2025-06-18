package com.ios7.wallify.MyClasses;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class EzFade {

    private static final long DEFAULT_DURATION = 300; // ms

    public static void fadeIn(final View view) {
        fadeIn(view, DEFAULT_DURATION);
    }

    public static void fadeOut(final View view) {
        fadeOut(view, DEFAULT_DURATION);
    }

    public static void fadeIn(final View view, long duration) {
        if (view == null) return;

        view.setVisibility(View.VISIBLE);
        AlphaAnimation anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        view.setAlpha(1);
        view.startAnimation(anim);
        view.bringToFront();
    }

    public static void fadeOut(final View view, long duration) {
        if (view == null) return;

        AlphaAnimation anim = new AlphaAnimation(1f, 0f);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE); // optionally View.INVISIBLE
            }
        });
        view.setAlpha(1);
        view.startAnimation(anim);
        view.bringToFront();
    }

    public static void crossfade(final View fromView, final View toView) {
        crossfade(fromView, toView, DEFAULT_DURATION);
    }

    public static void crossfade(final View fromView, final View toView, final long duration) {
        if (fromView == null || toView == null) return;

        // Fade out "fromView"
        AlphaAnimation fadeOut = new AlphaAnimation(1f, 0f);
        fadeOut.setDuration(duration);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                fromView.setVisibility(View.GONE);
            }
        });

        // Fade in "toView"
        toView.setVisibility(View.VISIBLE);
        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(duration);
        fadeIn.setFillAfter(true);

        // Start both animations
        fromView.startAnimation(fadeOut);
        toView.setAlpha(1);
        toView.startAnimation(fadeIn);
        toView.bringToFront();
    }
}
