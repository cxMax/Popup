package com.cxmax.popup.library;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * @describe : a popup-window params
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/2/25.
 */

public class PopupOptions implements Cloneable {
    public static final int STYLE_POPUP = 0;
    public static final int STYLE_DIALOG = 1;

    private Drawable backgroundDrawable;
    private int backgroundId;
    private int windowWith;
    private int windowHeight;
    private Context context;
    private int style; //0-popupwindow 1-dialog

    public PopupOptions(Context context) {
        this.context = context;
        style = STYLE_POPUP;
    }

    public void backgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }

    public void windowWidth(int windowWith) {
        this.windowWith = windowWith;
    }

    public void windowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public void style(int style) {
        this.style = style;
    }

    public void background(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
    }

    public void background(int backgroundId) {
        this.backgroundId = backgroundId;
        this.backgroundDrawable = new ColorDrawable(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    public Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public int getWindowWith() {
        return windowWith;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getStyle(){
        return style;
    }

    @Override
    protected PopupOptions clone() {
        try {
            PopupOptions result = (PopupOptions) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
