package com.cxmax.popup.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/2/27.
 */

public class GeneratorTool {
    public GeneratorTool() {

    }

    public Generator generate(@NonNull Context context, @NonNull View rootView, @NonNull PopupOptions options) {
        return options.getStyle() != 0
                ? new DialogGenerator(context, rootView, options)
                : new PopupGenerator(context, rootView, options);
    }

}


