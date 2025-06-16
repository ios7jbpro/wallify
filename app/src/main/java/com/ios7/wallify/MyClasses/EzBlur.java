package com.ios7.wallify.MyClasses;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.view.View;

public final class EzBlur {

    private EzBlur() {
        // Prevent instantiation
    }

    /**
     * Apply blur effect to a view if supported (API 31+).
     *
     * @param view   The View to blur
     * @param radius The blur radius in pixels (typically 10-25)
     */
    public static void setBlur(View view, float radius) {
        if (view == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) { // API 31+
            RenderEffect blurEffect = RenderEffect.createBlurEffect(radius, radius, Shader.TileMode.CLAMP);
            view.setRenderEffect(blurEffect);
        } else {
            // No-op on older devices
        }
    }

    /**
     * Remove any blur effect applied to the view.
     *
     * @param view The View to clear blur from
     */
    public static void clearBlur(View view) {
        if (view == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            view.setRenderEffect(null);
        }
    }
}
