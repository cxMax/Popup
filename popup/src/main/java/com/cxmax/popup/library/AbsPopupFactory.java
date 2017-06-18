package com.cxmax.popup.library;


import android.support.annotation.NonNull;

/**
 * describe : an abstract parent factory-pattern class to generate different {@link }
 * usage : the child class needs override the following abstract method
 * Created by cxmax on 2017/2/25.
 */

public abstract class AbsPopupFactory {

    abstract <T extends AbsPopupProvider> T createPopup(@NonNull Class<T> clz);

}

