package com.cxmax.popup.provider;

import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button open , close;

    @Override
    public View onCreateView() {
        return inflate(activity, R.layout.sample_popup_layout);
    }

    @Override
    public void initView(View rootView) {
        layout = (ConstraintLayout) rootView.findViewById(R.id.popup_layout);
        title = (TextView) rootView.findViewById(R.id.popup_title);
        content = (TextView) rootView.findViewById(R.id.popup_content);
        open = (Button) rootView.findViewById(R.id.popup_open_btn);
        close = (Button) rootView.findViewById(R.id.popup_close_btn);
    }

    @Override
    public void updateView(final CouponItem data) {
        //mock data
        if (Preconditions.assertNotNull(data)){
            if (popupOptions.getBackgroundDrawable() != null) {
                layout.setBackground(popupOptions.getBackgroundDrawable());
            }
            title.setText(data.title);
            content.setText(data.content);
            open.setText(activity.getString(R.string.btn_open));
            open.setOnClickListener(new View.OnClickListener() {
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
        Toast.makeText(activity , "点击了关闭按钮"  , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHandleTaskUi(CouponItem couponItem) {
        Toast.makeText(activity , "点击了打开按钮"  , Toast.LENGTH_SHORT).show();
//        if (Preconditions.assertNotNull(couponItem)){
//            //mock data
//            title.setText(couponItem.title);
//        }
    }
}
