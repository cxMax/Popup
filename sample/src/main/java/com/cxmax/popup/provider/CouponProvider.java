package com.cxmax.popup.provider;

import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxmax.popup.R;
import com.cxmax.popup.bean.CouponItem;
import com.cxmax.popup.library.AbsPopupProvider;
import com.cxmax.popup.library.PopupOperation;
import com.cxmax.popup.library.Preconditions;
import com.cxmax.popup.operate.CouponOperation;

/**
 * @describe : init popup-related-view params and fill data with related views
 * @usage :  you need extends {@link AbsPopupProvider}
 * <p>
 * <p>
 * Created by cxmax on 2017/2/27.
 */

public class CouponProvider extends AbsPopupProvider<CouponItem> implements PopupOperation.PopupView<CouponItem>{

    private ConstraintLayout layout;
    private TextView title;
    private TextView content;
    private Button button;
    private ImageView close;

    @Override
    public View onCreateView() {
        return inflate(context, R.layout.game_coupon_popup);
    }

    @Override
    public void initView(View rootView) {
        layout = (ConstraintLayout) rootView.findViewById(R.id.coupon_popup_layout);
        title = (TextView) rootView.findViewById(R.id.coupon_popup_title);
        content = (TextView) rootView.findViewById(R.id.coupon_popup_content);
        button = (Button) rootView.findViewById(R.id.coupon_popup_btn);
        close = (ImageView) rootView.findViewById(R.id.coupon_popup_img_close);
        button.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
    }

    @Override
    public void updateView(final CouponItem data) {
        //mock data
        if (Preconditions.assertNotNull(data)){
            layout.setBackground(context.getDrawable(R.drawable.game_gift_popupwindow_grab_background));
            title.setText(data.title);
            content.setText(data.content);
            button.setVisibility(View.VISIBLE);
            button.setText(context.getString(R.string.gift_copy_and_in));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupOperation.handleTask(data);
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupOperation.closeTask();
                }
            });
        }
    }

    @Override
    public void initOperation() {
        popupOperation = new CouponOperation();
        ((CouponOperation)popupOperation).setPopupView(this);
    }

    /**
     * you can make a custom-made popup showAtLocation() or super.method
     * @param parent
     * @param gravity
     * @param x
     * @param y
     */
    @Override
    public void showPopupView(View parent, int gravity, int x, int y) {
        super.showPopupView(parent, gravity, x, y);
    }

    @Override
    public void showCloseTaskUi() {

    }

    @Override
    public void showHandleTaskUi() {

    }

    @Override
    public void showHandleTaskUi(CouponItem couponItem) {
        if (Preconditions.assertNotNull(couponItem)){
            //mock data
            button.setText(couponItem.title);
        }
    }
}
