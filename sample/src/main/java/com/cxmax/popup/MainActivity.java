package com.cxmax.popup;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cxmax.popup.bean.CouponItem;
import com.cxmax.popup.library.Popup;
import com.cxmax.popup.provider.CouponProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.popup_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Popup.with(MainActivity.this)
                                .windowHeight(390)
                                .windowWidth(300)
                                .data(new CouponItem("我是新标题" , " 我是新内容"))
                                .background(new ColorDrawable(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)))
                                .clz(CouponProvider.class)
                                .asDialog()
                                .apply()
                                .showPopupView();
                    }
                });
    }

}
