package com.cxmax.popup.library;

/**
 * @describe : a window-interaction-event interface
 * @usage:
 * u can expand this interface by actual business requirements.
 * close() && handle() just are simple window-interaction events ,
 * <p>
 * <p>
 * Created by cxmax on 2017/2/25.
 */

public interface PopupOperation<T> {
    void closeTask();  //handle window-close-event
    void handleTask(T t); //handle button-click-event

    public interface PopupView<T>{
        void showCloseTaskUi();
        void showHandleTaskUi(T t);
    }
}
