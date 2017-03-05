## Popup

### 介绍
popup 用来解决弹窗(popupwindow/dialog)初始化以及交互事件耦合的lib , 交互事件处理严格遵守MVP的设计思想.
旨在简化用户在构建弹窗,处理具体的弹窗业务需求的代码成本.

### 优点
1. <b>使用这个lib来构建窗体，可以节省代码成本。（参考使用步骤）</b>  
2. <b>解决BadTokenException</b>。 ps:相信异步请求回调触发弹窗的需求，很多同学都遇到过这个bug吧  
    解决方案:  
    1.配合rxlivecycle的调用 , 在当前生命周期结束的时候,会cancle掉此次请求  
 2.Popup.with(this),的实现参考了glide源代码, 这次传递activity 或者 fragment 作为context,会在弹窗调用show()方法的时候,判断当前activity/fragment的生命周期情况  
3. <b>增加动画接口，和弹窗样式接口.</b>相关属性可以直接在style中配置调用,是不是很方便~

### 待优化or缺点 
1. 在AbsPopupProvider的createView() ; PopupGenerator的create(); DialogGenerator的create(); 代码还可以更优雅的处理.
2. 关于整个popup的链式调用设计,我觉得可能存在一点缺陷,以下方法必须调用.但方法调用顺序可随意更改.  
```
Popup.with(this)
     .data(data)
     .clz(CouponProvider.class)
     .apply()
     .showPopupView();
```
  <b>todo</b>  
  作为优化点, 我在想, 链式调用设计可不可划分为两部分,即非必须参数,和必须参数.直白一点就是,以下调用的必要参数,作为(Object... params)只要调用一次就可以了.类似于glide的transform()的调用
```
Glide.with(context)
     .transform(new XXTransformation() 
     , new XXTransformation() 
     , new CenterCrop(context));
```

### 使用步骤
>（可以参考项目sample里面的代码）

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

### 相关类说明
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

###感谢
1. drakeet/MultiType：
https://github.com/drakeet/MultiType
2. bumptech/glide
https://github.com/bumptech/glide

###最后
1. 因为jcenter()老是上传失败，现目前还不能通过gradle直接引用，所以还请大家暂时pull一下源码，源码都在library的module下面
2. 如果对于library代码封装有更好的建议，建议直接提issue，我不会断更新代码库，并且增加其他在实际项目中，所封装的一些代码。

# License
 Copyright (C) 2016 Amit Shekhar
   Copyright (C) 2011 Android Open Source Project

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

