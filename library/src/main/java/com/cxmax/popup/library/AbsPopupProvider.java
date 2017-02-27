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

    private static final int DEFAULT_WIDTH = 312;
    private static final int DEFAULT_HEIGHT = 390;

    protected View rootView;
    protected Context context;
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
            generator = new GeneratorTool().generate(context , rootView , popupOptions);
        }
        generator.create();
        initView(rootView);
    }

    public void showPopupView(View parent, int gravity, int x, int y) {
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

    public abstract View onCreateView();

    public abstract void initView(View rootView);

    public abstract void updateView(T data);

    public abstract void initOperation();

    private void initDefaultOption() {
        defaultPopupOptions = new PopupOptions(context);
        defaultPopupOptions.windowWidth((int) (DEFAULT_WIDTH * context.getResources().getDisplayMetrics().density));
        defaultPopupOptions.windowHeight((int) (DEFAULT_HEIGHT * context.getResources().getDisplayMetrics().density));
    }

    protected View inflate(Context context, int layout) {
        rootView = LayoutInflater.from(context).inflate(layout, null);
        return rootView;
    }
}
