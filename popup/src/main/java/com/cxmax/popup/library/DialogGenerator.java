package com.cxmax.popup.library;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * describe :generate dialog
 * usage :
 * Created by cxmax on 2017/2/27.
 */

public class DialogGenerator extends Generator {

    private Dialog dialog;


    public DialogGenerator(@NonNull Context context, @NonNull View rootView, @NonNull PopupOptions popupOptions) {
        super(context, rootView, popupOptions);
    }

    @Override
    public void create() {
        if (dialog == null) {
            dialog = new Dialog(context, popupOptions.getDialogStyle());
            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
            dialog.getWindow().setLayout(popupOptions.getWindowWith() , popupOptions.getWindowHeight());
        }
        dialog.setContentView(rootView);
        if (Preconditions.assertNotNull(popupOptions.getAnimStyle())
                && Preconditions.assertNotNull(dialog.getWindow())) {
            dialog.getWindow().setWindowAnimations(popupOptions.getAnimStyle());
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ((Activity) context).getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.white)));
                setBackgroundAlpha(1.0f);
            }
        });
    }

    @Override
    void show(View parent, int gravity, int x, int y) {
        if (Preconditions.assertNotNull(dialog)) {
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

    public Dialog getDialog() {
        return dialog;
    }
}
