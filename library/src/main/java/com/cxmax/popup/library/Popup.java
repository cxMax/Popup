package com.cxmax.popup.library;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @describe : a builder-pattern to design a popup with different params(you can use custom-made layout and custom window params )
 * @usage steps:
 * 1. set up pojo to bind data
 * 2. extends {@link AbsPopupProvider} and override onCreateView(),initView(),updateView(),initOperation() ; showPopupView() is not necessary
 * 3. in an activity or a fragment init PopupManager instance and init popup-related params
 * 4. the popup-interaction-event can be define in {@link PopupOperation} and expand
 * 5. actually the {@link AbsPopupProvider} hold the {@link PopupOperation}
 *    and handle the operate callback to change view ,
 *    you need complete the whole logic in those class
 *    (View logic in {@link AbsPopupProvider} ,
 *    operate logic int {@link PopupOperation})
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

        public PopupManager windowWidth(int windowWith) {
            initOptions();
            options.windowWidth(windowWith);
            return this;
        }

        public PopupManager windowHeight(int windowHeight) {
            initOptions();
            options.windowHeight(windowHeight);
            return this;
        }

        public PopupManager background(Drawable backgroundDrawable) {
            initOptions();
            options.background(backgroundDrawable);
            return this;
        }

        public PopupManager background(int backgroundId) {
            initOptions();
            options.background(backgroundId);
            return this;
        }

        public PopupManager dialogStyle(int dialogStyle){
            initOptions();
            options.dialogStyle(dialogStyle);
            return this;
        }

        public PopupManager animStyle(int animStyle){
            initOptions();
            options.animStyle(animStyle);
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

        public PopupManager asDialog(){
            initOptions();
            options.style(PopupOptions.STYLE_DIALOG);
            return this;
        }

        public PopupManager apply() {
            assertNotNull();
            if (absPopupProvider == null){
                absPopupProvider = new PopupFactory().createPopup(clazz);
                absPopupProvider.context = context;
                absPopupProvider.popupOptions = options;
                absPopupProvider.createView();
                absPopupProvider.updateView(data);
            }
            return this;
        }

        @Override
        public void showPopupView() {
            showPopupView(null,0,0,0);

        }

        public void showPopupView(View parent, int gravity, int x, int y) {
            if (!assertHasDestroyed(context)
                    && Preconditions.assertNotNull(absPopupProvider)
                    && !isShowing()){
                if (parent == null){
                    absPopupProvider.showPopupView();
                    return;
                }
                absPopupProvider.showPopupView(parent , gravity , x , y);
            }
        }

        @Deprecated
        @Override
        public void hidePopupView() {
            if (assertIsAdded() && Preconditions.assertNotNull(absPopupProvider)) {
                absPopupProvider.hidePopupView();
            }
        }

        @Deprecated
        @Override
        public void updatePopupView(Object data) {
            if (!assertHasDestroyed(context) && Preconditions.assertNotNull(data) && Preconditions.assertNotNull(absPopupProvider)){
                absPopupProvider.updateView(data);
            }
        }

        private void initOptions(){
            if (!Preconditions.assertNotNull(options)) {
                options = new PopupOptions(context);
            }
        }

        private void assertNotNull(){
            if (context == null) {
                throw new NullPointerException("You cannot apply() on a null Context");
            }else if (data == null) {
                throw new NullPointerException("there is no data found, you should invoke data() to bind dada before showPopupView()");
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

        @Deprecated
        void hidePopupView();

        @Deprecated
        void updatePopupView(T data);
    }
}
