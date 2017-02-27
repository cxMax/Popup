package com.cxmax.popup.library;

import android.content.Context;
import android.view.View;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/2/27.
 */

public class GeneratorTool {
    public GeneratorTool(){

    }

    public Generator generate(Context context, View rootView, PopupOptions options){
        assertNotNull(context,rootView,options);
        return options != null && options.getStyle() != 0 ? new DialogGenerator(context,rootView,options) : new PopupGenerator(context,rootView,options);
    }

    private void assertNotNull(Context context , View rootView , PopupOptions options){
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }else if (rootView == null) {
            throw new NullPointerException("rootView cannot be null");
        }else if (options == null) {
            throw new NullPointerException("options cannot be null");
        }
    }
}

