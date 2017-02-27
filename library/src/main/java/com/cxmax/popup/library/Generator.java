package com.cxmax.popup.library;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/2/27.
 */

public abstract class Generator {
    protected Context context;
    protected View rootView;
    protected PopupOptions popupOptions;

    public Generator(Context context , View rootView , PopupOptions popupOptions) {
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

    abstract void show(View parent, int gravity, int x, int y);

    abstract void hide();

    abstract boolean isShowing();
}
