package com.ios7.wallify.MyClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class EzIntent {

    private Intent intent;

    public EzIntent(Context context, Class<?> cls) {
        intent = new Intent(context, cls);
    }

    public EzIntent setAction(String action) {
        intent.setAction(action);
        return this;
    }

    public EzIntent setFlags(int flags) {
        intent.setFlags(flags);
        return this;
    }

    public EzIntent addFlag(int flag) {
        intent.addFlags(flag);
        return this;
    }

    public EzIntent putExtra(String name, boolean value) {
        intent.putExtra(name, value);
        return this;
    }

    public EzIntent putExtra(String name, byte value) {
        intent.putExtra(name, value);
        return this;
    }

    public EzIntent putExtra(String name, short value) {
        intent.putExtra(name, value);
        return this;
    }

    public EzIntent putExtra(String name, int value) {
        intent.putExtra(name, value);
        return this;
    }

    public EzIntent putExtra(String name, long value) {
        intent.putExtra(name, value);
        return this;
    }

    public EzIntent putExtra(String name, float value) {
        intent.putExtra(name, value);
        return this;
    }

    public EzIntent putExtra(String name, double value) {
        intent.putExtra(name, value);
        return this;
    }

    public EzIntent putExtra(String name, String value) {
        intent.putExtra(name, value);
        return this;
    }

    public EzIntent putExtras(Bundle extras) {
        intent.putExtras(extras);
        return this;
    }

    public void startActivity(Context context) {
        context.startActivity(intent);
    }

    public void startActivityForResult(Context context, int requestCode) {
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        } else {
            throw new IllegalArgumentException("Context must be an instance of Activity.");
        }
    }

    public Intent getIntent() {
        return intent;
    }
}
