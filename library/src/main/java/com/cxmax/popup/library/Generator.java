package com.cxmax.popup.library;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;

/**
 * @describe : base class to generate a real dialog or popupwindow, and alse provide children abstract method to override
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/2/27.
 */

public abstract class Generator {
    protected Context context;
    protected View rootView;
    protected PopupOptions popupOptions;

    public Generator(@NonNull Context context, @NonNull View rootView, @NonNull PopupOptions popupOptions) {
        this.context = context;
        this.rootView = rootView;
        this.popupOptions = popupOptions;
    }

    protected void setBackgroundAlpha(float f) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = f;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    abstract void create();

    abstract void show(@NonNull View parent, int gravity, int x, int y);

    abstract void hide();

    abstract boolean isShowing();
}
