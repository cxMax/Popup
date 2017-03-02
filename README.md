## Popup

### 介绍
popup 用来解决弹窗(popupwindow/dialog)初始化以及交互事件耦合的lib , 交互事件处理严格遵守MVP的设计思想.
旨在简化用户在构建弹窗,处理具体的弹窗业务需求的代码成本.

### 优点
1. 使用这个lib来构建窗体，可以节省代码成本。（参考使用步骤）
2. 在配合rxjava ， 可以完美避免BadTokenException。 ps:相信异步请求回调触发弹窗的需求，很多同学都遇到过这个bug吧
3. todo 增加动画接口， 使弹出效果更加酷炫。

### 使用步骤
>（可以参考项目sample里面的代码）

用户在处理与弹窗交互相关的业务需求的时, 只需要关心建立以下三个类即可.  
1. JavaBean类, 初始化与弹窗业务相关的实体类, 类似CouponItem/GiftItem  
2. 继承AbsPopupProvider类,类似CouponProvider.
    Override以下方法:
```
    onCreateView() 弹窗的布局layout  
    initView() 初始化弹窗相关的控件  
    updateView() 数据的绑定  
    initOperation() 初始化处理具体弹窗(内部控件点击)业务逻辑类  
    showPopupView() 显示的位置,默认在屏幕中央   
```
3. 继承AbsPopupOperation类, 类似CouponOperation.  
    具体的控件点击交互事件可以在PopupOperation接口中定义或者拓展,PopupOperation.PopupView作为view交互的回调事件定义  
4. 具体代码调用.  
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
1. rakeet/MultiType：
https://github.com/drakeet/MultiType
2. bumptech/glide
https://github.com/bumptech/glide

###最后
1. 因为jcenter()老是上传失败，现目前还不能通过gradle直接引用，所以还请大家暂时pull一下源码，源码都在library的module下面
2. 如果对于library代码封装有更好的建议，建议直接提issue，我不会断更新代码库，并且增加其他在实际项目中，所封装的一些代码。

