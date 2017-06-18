package com.cxmax.popup.library;

/**
 * describe : init detailed {@link AbsPopupProvider} like {@link }
 * usage :
 * Created by cxmax on 2017/2/25.
 */

public class PopupFactory extends AbsPopupFactory {

    @Override
    public <T extends AbsPopupProvider> T createPopup(Class<T> clz) {
        AbsPopupProvider provider = null;
        try {
            provider = (AbsPopupProvider) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) provider;
    }
}
