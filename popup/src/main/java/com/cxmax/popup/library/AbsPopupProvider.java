package com.cxmax.popup.library;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * describe : a base popup initialized class,
 * usage : it's provide abstract method to init popup,fill data in popup,init popup-interaction-event callback
 * the child class need override those abstract method
 * Created by cxmax on 2017/2/25.
 */

public abstract class AbsPopupProvider<T> {

    private static final int DEFAULT_WIDTH = 312;
    private static final int DEFAULT_HEIGHT = 390;
    private static final int DEFAULT_STYLE = android.R.style.Theme_DeviceDefault_Light_Dialog_Alert;

    protected View rootView;
    @NonNull protected Activity activity;
    protected PopupOptions popupOptions;
    protected PopupOptions defaultPopupOptions;
    protected PopupOperation popupOperation;
    protected Generator generator;

    protected void createView() {
        if (rootView == null) {
            rootView = onCreateView();
            initDefaultOption();
            initOperation();
            if (popupOptions == null) {
                popupOptions = defaultPopupOptions.clone();
            }
            generator = new GeneratorTool().generate(activity , rootView , popupOptions);
        }
        generator.create();
        initView(rootView);
    }

    public void showPopupView(@NonNull View parent, int gravity, int x, int y) {
        if (Preconditions.assertNotNull(generator)) {
            generator.show(parent, gravity, x, y);
        }
    }

    public void showPopupView() {
        showPopupView(rootView, Gravity.CENTER, 0, 0);
    }

    public void hidePopupView() {
        if (Preconditions.assertNotNull(generator)) {
            generator.hide();
        }
    }

    public boolean isShowing() {
        return Preconditions.assertNotNull(generator) && generator.isShowing();
    }

    public View getRootView() {
        return generator.getRootView();
    }

    public abstract View onCreateView();

    public abstract void initView(@NonNull View rootView);

    public abstract void updateView(@NonNull T data);

    public abstract void initOperation();

    private void initDefaultOption() {
        defaultPopupOptions = new PopupOptions(activity);
        defaultPopupOptions.windowWidth((int) (DEFAULT_WIDTH * activity.getResources().getDisplayMetrics().density));
        defaultPopupOptions.windowHeight((int) (DEFAULT_HEIGHT * activity.getResources().getDisplayMetrics().density));
        defaultPopupOptions.dialogStyle(DEFAULT_STYLE);
    }

    protected View inflate(@NonNull Context context, int layout) {
        rootView = LayoutInflater.from(context).inflate(layout, null);
        return rootView;
    }
}
