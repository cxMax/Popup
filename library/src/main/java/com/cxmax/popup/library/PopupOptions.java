package com.cxmax.popup.library;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @describe : a popup-window params
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/2/25.
 */

public class PopupOptions implements Cloneable {

    private Drawable backgroundDrawable;
    private int backgroundId;
    private int windowWith;
    private int windowHeight;
    private Context context;

    public PopupOptions(Context context) {
        this.context = context;
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

    public void background(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
    }

    public void background(int backgroundId) {
        this.backgroundId = backgroundId;
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
