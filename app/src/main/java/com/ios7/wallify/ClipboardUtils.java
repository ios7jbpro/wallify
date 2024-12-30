package com.ios7.wallify;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

public class ClipboardUtils {
    public static void copyTextToClipboard(Context context, String text) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", text);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
    }
}