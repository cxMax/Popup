# Popup
> current version : 1.0  
> English introduce plz click here [Readme_english.md]() 

## 介绍
Popup 旨在解决自定义复杂布局的弹窗,弹窗内部view层跟model层解偶,分离.  

## Popup 2.0 
Popup 2.0 会优化构建Dialog/PopupWindow的代码,Popup 1.0 的构建部分看上去有点冗余且绕.

## 使用步骤
>（可以参考项目sample里面的代码）
```
compile 'com.cxmax.popup:popup:1.0'
```
用户在处理与弹窗交互相关的业务需求的时, 只需要关心建立以下三个类即可.  
1. <b>JavaBean类</b>, 初始化与弹窗业务相关的实体类, 类似CouponItem/GiftItem  
2. <b>继承AbsPopupOperation类</b>, 类似CouponOperation.  
    具体的控件点击交互事件可以在PopupOperation接口中定义或者拓展,PopupOperation.PopupView作为view交互的回调事件定义   
 并且AbsPopupProvider会持有改operation,作为view交互事件回调改变弹窗的ui视图  
3. <b>继承AbsPopupProvider类</b>,类似CouponProvider.
    Override以下方法:
```
    onCreateView() 弹窗的布局layout  
    initView() 初始化弹窗相关的控件  
    updateView() 数据的绑定  
    initOperation() 初始化处理具体弹窗(内部控件点击)业务逻辑类  
    showPopupView() 显示的位置,默认在屏幕中央   
```
>具体代码调用. 类似 activity, fragment.  
``` 
        Popup.with(this)
                .windowHeight(390)
                .windowWidth(300)
                .data(data)
                .background(R.drawable.game_gift_popupwindow_use_background)
                .clz(CouponProvider.class)
//                .asDialog()
                .apply()
                .showPopupView();
```

## 相关类说明
1. PopupOptions 弹窗相关的属性
2. GeneratorTool , Generator , DialogGenerator , PopupGenerator 具体dialog and popupwindow初始化的代码.
   以下抽象方法在AbsPopupProvider中调用
   
```
    abstract void create();

    abstract void show(View parent, int gravity, int x, int y);

    abstract void hide();

    abstract boolean isShowing();
```

3. AbsPopupProvider, 弹窗view的init和change,都在这个类
4. AbsPopupOperation , 弹窗view的交互逻辑
5. Popup , 对外提供的一个链式调用 , 调用的参数包含弹窗的一些属性

## 感谢
1. drakeet/MultiType：
https://github.com/drakeet/MultiType
2. bumptech/glide
https://github.com/bumptech/glide

## License
   Copyright (C) 2017 cxMax  
   Copyright (C) 2017 Popup

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

