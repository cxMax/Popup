package com.cxmax.popup.library;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/2/27.
 */

public class DialogGenerator extends Generator{

    private Dialog dialog;


    public DialogGenerator(Context context, View rootView, PopupOptions popupOptions) {
        super(context, rootView, popupOptions);
    }

    @Override
    public void create() {
        if (dialog == null) {
            dialog = new Dialog(context, 0 /*R.style.Theme_Flyme_AppCompat_Light_Dialog_Alert*/);
        }
        dialog.setContentView(rootView);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                final WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                if (lp.alpha != 1.0f) {
                    setBackgroundAlpha(1.0f);
                }
            }
        });
    }

    @Override
    void show(View parent, int gravity, int x, int y) {
        if (Preconditions.assertNotNull(dialog)) {
            setBackgroundAlpha(0.4f);
            dialog.show();
        }
    }

    @Override
    public void hide() {
        if (Preconditions.assertNotNull(dialog) && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    boolean isShowing() {
        return Preconditions.assertNotNull(dialog) && dialog.isShowing();
    }

    public Dialog getDialog(){
        return dialog;
    }
}
