package com.demospotsoon.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;

public class CompatibilityUtil {

    public static ColorStateList getTextViewColorStateList(Context context, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColorStateList(colorId, context.getTheme());
        } else {
            return context.getResources().getColorStateList(colorId);
        }
    }
}
