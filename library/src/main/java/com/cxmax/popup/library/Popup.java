package com.cxmax.popup.library;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;

/**
 * @describe : a builder-pattern to design a popup with different params(you can use custom-made layout and custom window params )
 * @usage steps:
 * 1. set up pojo to bind data like {@link com.meizu.cloud.coupon.item.CouponItem}
 * 2. extends {@link AbsPopupProvider} and override onCreateView(),initView(),updateView(),initOperation() ; showPopupView() is not necessary
 * 3. in an activity or a fragment init PopupManager instance and init popup-related params
 * 4. the popup-interaction-event can be define in {@link PopupOperation} and expand like {@link com.meizu.cloud.coupon.operate.CouponOperation}
 * 5. actually the {@link com.meizu.cloud.coupon.provider.CouponProvider} hold the {@link com.meizu.cloud.coupon.operate.CouponOperation}
 *    and handle the operate callback to change view ,
 *    you need complete the whole logic in those class
 *    (View logic in {@link com.meizu.cloud.coupon.provider.CouponProvider} ,
 *    operate logic int {@link com.meizu.cloud.coupon.operate.CouponOperation})
 * btw: to generate a whole popup view-to-logic interaction , use this lib you only use those steps
 * <p>
 *     manager = Popup.with(this)
 *              .windowHeight(390)
 *              .windowWidth(300)
 *              .data(data)
 *              .background(R.drawable.game_gift_popupwindow_use_background)
 *              .clz(CouponProvider.class)
 *              .apply();
 *     manager.updatePopupView(data);
 *     manager.showPopupView();
 * </p>
 * Created by cxmax on 2017/2/25.
 */

public class Popup {

    public static PopupManager with(Context context) {
        return new PopupManager(context);
    }

    public static class PopupManager<T> implements Action<T> {

        private Context context;
        private Fragment fragment;
        private PopupOptions options;
        private T data;
        private Class<? extends AbsPopupProvider> clazz;
        private AbsPopupProvider absPopupProvider;

        public PopupManager(Context context) {
            this.context = context;
            options = new PopupOptions(context);
        }

        public PopupManager(AppCompatActivity appCompatActivity) {
            this.context = appCompatActivity;
        }

        public PopupManager(Activity activity) {
            this.context = activity;
        }

        public PopupManager(Fragment fragment) {
            this.fragment = fragment;
            this.context = fragment.getActivity();
        }

        public PopupManager backgroundId(int backgroundId) {
            options.backgroundId(backgroundId);
            return this;
        }

        public PopupManager windowWidth(int windowWith) {
            options.windowWidth(windowWith);
            return this;
        }

        public PopupManager windowHeight(int windowHeight) {
            options.windowHeight(windowHeight);
            return this;
        }

        public PopupManager background(Drawable backgroundDrawable) {
            options.background(backgroundDrawable);
            return this;
        }

        public PopupManager background(int backgroundId) {
            options.background(backgroundId);
            return this;
        }

        public PopupManager data(T data) {
            this.data = data;
            return this;
        }

        public PopupManager clz(Class<? extends AbsPopupProvider> clazz) {
            this.clazz = clazz;
            return this;
        }

        public PopupManager apply() {
            if (context == null) {
                throw new NullPointerException("You cannot apply() on a null Context");
            }
            if (absPopupProvider == null){
                options = new PopupOptions(context);
                absPopupProvider = new PopupFactory().createPopup(clazz);
                absPopupProvider.context = context;
                absPopupProvider.popupOptions = options;
                absPopupProvider.createView();
                if (data != null) {
                    absPopupProvider.updateView(data);
                }
            }
            return this;
        }

        @Override
        public void showPopupView() {
            if (absPopupProvider == null) {
                throw new NullPointerException("you should invoke apply() method at last");
            }
            if (data == null) {
                throw new NullPointerException("there is no data found, you should invoke data() to bind dada before showPopupView()");
            }
            if (!assertHasDestroyed(context) && !isShowing()){
                absPopupProvider.showPopupView();
            }
        }

        @Override
        public void hidePopupView() {
            if (assertIsAdded() && Preconditions.assertNotNull(absPopupProvider)) {
                absPopupProvider.hidePopupView();
            }
        }

        @Override
        public void updatePopupView(Object data) {
            if (!assertHasDestroyed(context) && Preconditions.assertNotNull(data) && Preconditions.assertNotNull(absPopupProvider)){
                absPopupProvider.updateView(data);
            }
        }

        private boolean isShowing(){
            return Preconditions.assertNotNull(absPopupProvider) && absPopupProvider.isShowing();
        }

        private boolean assertHasDestroyed(Context context) {
            if (context == null) {
                throw new NullPointerException("You cannot apply() on a null Context");
            } else {
                if (context instanceof Application) {
                    throw new IllegalArgumentException("you cannot bind context with application context");
                }else if (context instanceof AppCompatActivity || context instanceof Activity) {
                    return Preconditions.assertHasDestroyed((Activity) context);
                }
            }
            return true;
        }

        private boolean assertIsAdded(){
            return fragment == null || Preconditions.assertIsAdded(fragment);
        }
    }


    public interface Action<T> {
        void showPopupView();

        void hidePopupView();

        void updatePopupView(T data);
    }
}
