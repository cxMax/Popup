package com.cxmax.popup.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * @describe : generate popupwindow
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/2/27.
 */

public class PopupGenerator extends Generator {

    private PopupWindow popupWindow;

    public PopupGenerator(@NonNull Context context, @NonNull View rootView, @NonNull PopupOptions popupOptions) {
        super(context, rootView, popupOptions);
    }

    @Override
    public void create() {
        popupWindow = new PopupWindow(rootView,
                popupOptions.getWindowWith(),
                popupOptions.getWindowHeight(), false);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        if (Preconditions.assertNotNull(popupOptions.getBackgroundDrawable())) {
            popupWindow.setBackgroundDrawable(popupOptions.getBackgroundDrawable());
        }
        if (Preconditions.assertNotNull(popupOptions.getAnimStyle())) {
            popupWindow.setAnimationStyle(popupOptions.getAnimStyle());
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                final WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                if (lp.alpha != 1.0f) {
                    setBackgroundAlpha(1.0f);
                }
            }
        });
    }

    @Override
    public void show(View parent, int gravity, int x, int y) {
        if (Preconditions.assertNotNull(popupWindow)) {
            setBackgroundAlpha(0.4f);
            popupWindow.showAtLocation(parent, gravity, x, y);
        }
    }

    @Override
    public void hide() {
        if (Preconditions.assertNotNull(popupWindow) && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    @Override
    boolean isShowing() {
        return Preconditions.assertNotNull(popupWindow) && popupWindow.isShowing();
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }
}
