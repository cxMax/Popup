package com.cxmax.popup.library;

/**
 * @describe : base popup-operation class, aim at setting PopupView change callback
 * @usage : you can invoke this callback class in the {@link AbsPopupProvider} , and realize the detailed view-change event
 * <p>
 * </p>
 * Created by caixi on 17-2-24.
 */

public abstract class AbsPopupOperation<T> implements PopupOperation<T>{

    public AbsPopupOperation(){

    }

    public PopupOperation.PopupView popupView;

    public void setPopupView(PopupOperation.PopupView popupView) {
        this.popupView = popupView;
    }

}
