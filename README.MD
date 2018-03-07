为学习Android Support Design而写的Demo

# NavigationView

## 效果图

![](http://7sbojw.com1.z0.glb.clouddn.com//asd/NavigationView.png?imageView/2/w/500)

## 注意点

### 状态栏透明
就一个步骤，新建一个AppTheme.NoActionBarTransparent的style应用到Activity上就可以，不需要其他的。
```xml
    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>

    <style name="AppTheme.NoActionBarTransparent" parent="AppTheme.NoActionBar">
        <item name="android:statusBarColor">@android:color/transparent</item>
    </style>
```
亲测把蓝色调成和play商店一样的颜色，效果和play商店一毛一样。

注意，这里的transparent是全透，至于`<item name="android:windowTranslucentStatus">true</item>`这个是半透明，使用这个状态栏看起来会暗一点。

**有个坑**，就是状态栏的下端有时候会看到一个内阴影，找了一下发现原来是内容布局的根节点设置了`android:fitsSystemWindows="true"`，这个不需要哈，删了之后就显示成平面的了。下面是对比图：

![](http://7sbojw.com1.z0.glb.clouddn.com//asd/status_diff.png)

# CollapsingToolbarLayout

## 效果图

这其实是个对比图

![](http://7sbojw.com1.z0.glb.clouddn.com/ADW_collapsingToolbarLayout.gif)

## 注意点

CollapsingToolbarLayout有一个属性可以设置在折叠状态时状态栏的颜色：
`app:statusBarScrim="?attr/colorPrimary"`一般会写成透明或者半透明
但是我发现，在写了这一行代码后没有生效，即颜色还是默认的颜色（colorPrimary）。
后调查发现：

不管是
1. 在布局文件里设置状态栏颜色
   ```xml
    <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/toolbar_layout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fitsSystemWindows="true"
            app:contentScrim="?attr/colorPrimary"
            app:statusBarScrim="@android:color/holo_purple"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">
   ```
2. 还是在java代码里设置状态栏颜色
   ```java
       collapsingToolbarLayout.setStatusBarScrimColor(Color.GREEN);
   ```

都需要先对状态栏的透明度设置一下，上面的代码才会生效，为啥会这样？暂时没研究。
设置状态栏透明度的三种方法：
1. 在style.xml中增加下面的代码把状态栏设置成全透明
   ```xml
        <item name="android:statusBarColor">@android:color/transparent</item>
   ```

2. 在style.xml中增加下面的代码把状态栏设置成半透明
   ```xml
       <item name="android:windowTranslucentStatus">true</item>
   ```

3. 在java代码中onCreate()方法里把状态栏设置成半透明
   ```java
       getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
   ```

如果你已经在**style**里面把状态栏设置成了透明，那么CollapsingToolbarLayout也想要透明效果的话就无需设置了。

设置半透明和透明的效果图就是上面的图（两边都是紫色）。

PS：不要吐槽审美，为了凸显效果才这样的。

# TextInputLayout和Snackbar
## 效果图

![](http://7sbojw.com1.z0.glb.clouddn.com//asd/inputlayout&snkb.gif)

## 注意点
想要Snackbar可以滑动消除，需要把它放在CoordinatorLayout里面
# RecyclerView
## 效果图

![](http://7sbojw.com1.z0.glb.clouddn.com/recyvlerview.gif)

这个没啥说的
# AnimatedVectorDrawable
## 效果图

![](http://7sbojw.com1.z0.glb.clouddn.com//asd/animatedVectorDrawable.gif)

矢量图动画，另外还有tween animation等没加进来。
# TabLayout
## 效果图

![](http://7sbojw.com1.z0.glb.clouddn.com//asd/tablayout.gif)

和palette一起应用的

