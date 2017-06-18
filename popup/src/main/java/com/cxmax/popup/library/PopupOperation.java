package com.cxmax.popup.library;

import android.support.annotation.NonNull;

/**
 * describe : a window-interaction-event interface
 * usage:
 * u can expand this interface by actual business requirements.
 * close() ,  handle() just are simple window-interaction events ,
 * Created by cxmax on 2017/2/25.
 */

public interface PopupOperation<T> {
    void closeTask();  //handle window-close-event
    void handleTask(@NonNull T t); //handle button-click-event

    public interface PopupView<T>{
        void showCloseTaskUi();
        void showHandleTaskUi(@NonNull T t);
    }
}

