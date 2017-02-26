package com.cxmax.popup.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * @describe : a base popup initialized class,
 * @usage : it's provide abstract method to init popup,fill data in popup,init popup-interaction-event callback
 * the child class need override those abstract method
 * <p>
 * <p>
 * Created by cxmax on 2017/2/25.
 */

public abstract class AbsPopupProvider<T> {

    private static final int DEFAULT_WITDH = 312;
    private static final int DEFAULT_HEIGHT = 390;

    protected PopupWindow popupWindow;
    protected View rootView;
    protected Context context;
    protected PopupOptions popupOptions;
    protected PopupOptions defaultPopupOptions;
    protected PopupOperation popupOperation;

    protected void createView() {
        if (rootView == null) {
            rootView = onCreateView();
            initDefaultOption();
            if (popupOptions == null) {
                popupOptions = defaultPopupOptions.clone();
            }
        }
        popupWindow = new PopupWindow(rootView,
                popupOptions.getWindowWith(),
                popupOptions.getWindowHeight(), false);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                final WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                if (lp.alpha != 1.0f) {
                    lp.alpha = 1.0f;
                    ((Activity) context).getWindow().setAttributes(lp);
                }
            }
        });
        initView(rootView);
    }

    private void initDefaultOption() {
        defaultPopupOptions = new PopupOptions(context);
        defaultPopupOptions.windowWidth((int) (DEFAULT_WITDH * context.getResources().getDisplayMetrics().density));
        defaultPopupOptions.windowHeight((int) (DEFAULT_HEIGHT * context.getResources().getDisplayMetrics().density));
//        defaultPopupOptions.background(R.drawable.game_gift_popupwindow_use_background);
    }

    protected View inflate(Context context, int layout) {
        rootView = LayoutInflater.from(context).inflate(layout, null);
        return rootView;
    }

    public void showPopupView(View parent, int gravity, int x, int y) {
        if (Preconditions.assertNotNull(popupWindow)) {
            popupWindow.showAtLocation(parent, gravity, x, y);
        }
    }

    public void showPopupView() {
        if (Preconditions.assertNotNull(popupWindow)) {
            popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
        }
    }

    public void hidePopupView() {
        if (Preconditions.assertNotNull(popupWindow) && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public boolean isShowing() {
        return Preconditions.assertNotNull(popupWindow) && popupWindow.isShowing();
    }

    public abstract View onCreateView();

    public abstract void initView(View rootView);

    public abstract void updateView(T data);

    public abstract void initOperation();
}
