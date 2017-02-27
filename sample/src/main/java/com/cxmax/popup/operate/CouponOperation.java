package com.cxmax.popup.operate;

import com.cxmax.popup.bean.CouponItem;
import com.cxmax.popup.library.AbsPopupOperation;

/**
 * @describe : handle detailed window-interaction-event
 * @usage : you need extends {@link AbsPopupOperation}
 * <p>
 * <p>
 * Created by cxmax on 2017/2/27.
 */

public class CouponOperation extends AbsPopupOperation<CouponItem> {

    public CouponOperation() {

    }

    @Override
    public void closeTask() {
        popupView.showCloseTaskUi();
    }

    @Override
    public synchronized void handleTask(CouponItem couponItem) {
        //mock process
        popupView.showHandleTaskUi(couponItem);
    }

}
