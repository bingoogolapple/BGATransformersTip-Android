:running:BGATransformersTip-Android:running:
============

Android 通用 PopupWindow，再也不用找 UI 小姐姐切 .9 图片了，大致能为你节省 30 分钟的开发时间

## 功能介绍

- [x] 支持配置浮窗展示在锚点控件的任意位置
- [x] 支持配置指示箭头（是否展示、展示在浮窗的任意位置、高度、圆角、颜色）
- [x] 支持配置浮窗背景色
- [x] 支持配置浮窗边框阴影（是否展示、宽度、颜色）
- [x] 支持配置浮窗以外的其他区域是否变暗
- [x] 浮窗超出屏幕区域后会自动移动浮窗到屏幕区域内
- [x] 对于只有文字的浮窗，直接使用 SimpleTextTip，不用写布局文件

## 效果图

| ![1](https://user-images.githubusercontent.com/8949716/64065983-38406780-cc47-11e9-8971-5fa730206fe5.png) | ![2](https://user-images.githubusercontent.com/8949716/64066122-cc5efe80-cc48-11e9-9eba-479df9c78962.png) |
| ------------ | ------------- |
| ![3](https://user-images.githubusercontent.com/8949716/64065997-645be880-cc47-11e9-8b4a-6f5d179ec6be.png) | ![4](https://user-images.githubusercontent.com/8949716/64066135-e7317300-cc48-11e9-8e8b-38458d4ed3ba.png) |
 
![效果图](https://user-images.githubusercontent.com/8949716/63364704-b4fe5680-c3a8-11e9-9185-872fc1671430.png)

## 使用说明

### 添加 Gradle 依赖

* 把 `maven { url 'https://jitpack.io' }` 加入到 repositories 中
* 添加如下依赖，末尾的「latestVersion」指的是徽章 [![Download](https://jitpack.io/v/bingoogolapple/BGATransformersTip-Android.svg)](https://jitpack.io/#bingoogolapple/BGATransformersTip-Android) 里的版本名称，请自行替换。

```groovy
dependencies {
    // 使用 AndroidX 时
    implementation 'com.github.bingoogolapple.BGATransformersTip-Android:library:latestVersion@aar'
    
    // 没有使用 AndroidX 时
    // implementation 'com.github.bingoogolapple.BGATransformersTip-Android:library-noandroidx:latestVersion@aar'
}
```

### 方式一：在 Java 代码中设置浮窗位置，在布局文件中设置浮窗背景、箭头位置
* 这种方式的优点是可以提前查看预览效果，提升开发效率

![preview](https://user-images.githubusercontent.com/8949716/64067626-5b2a4600-cc5e-11e9-9ffe-6e0891a9f72d.png)

* 添加浮窗布局文件，在布局文件中设置浮窗背景、箭头位置

```xml
<?xml version="1.0" encoding="utf-8"?>
<cn.bingoogolapple.transformerstip.view.TransformersTipLinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:gravity="center_vertical"
    android:orientation="horizontal"
    app:ad_arrowExtraOffsetX="0dp"
    app:ad_arrowExtraOffsetY="0dp"
    app:ad_arrowGravity="to_top_center"
    app:ad_arrowHeight="6dp"
    app:ad_bgColor="@android:color/black"
    app:ad_radius="4dp"
    app:ad_shadowColor="#33000000"
    app:ad_shadowSize="6dp">

    <TextView
        android:id="@+id/tv_tip_content"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:text="B -> bingo\nG googol\nA -> apple\nBGA -> bingoogolapple"
        android:textColor="@android:color/white" />

    <TextView
        android:id="@+id/tv_tip_close"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="6dp"
        android:layout_marginEnd="6dp"
        android:layout_gravity="top"
        android:padding="4dp"
        android:text="x"
        android:textColor="@android:color/white" />
</cn.bingoogolapple.transformerstip.view.TransformersTipLinearLayout>
```
* 在 Java 代码中设置浮窗位置
```Java
new TransformersTip(anchorView, R.layout.layout_demo1_tip) {
    @Override
    protected void initView(View contentView) {
        // 点击浮窗中自定按钮关闭浮窗
        contentView.findViewById(R.id.tv_tip_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissTip();
            }
        });
    }
}
        .setTipGravity(TipGravity.TO_BOTTOM_CENTER) // 设置浮窗相对于锚点控件展示的位置
        .setTipOffsetXDp(0) // 设置浮窗在 x 轴的偏移量
        .setTipOffsetYDp(-6) // 设置浮窗在 y 轴的偏移量

        .setBackgroundDimEnabled(true) // 设置是否允许浮窗的背景变暗
        .setDismissOnTouchOutside(false) // 设置点击浮窗外部时是否自动关闭浮窗

        .show(); // 显示浮窗
```

### 方式二：在 Java 代码中设置浮窗位置、浮窗背景、箭头位置

* 添加浮窗布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <TextView
        android:id="@+id/tv_tip_content"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:text="B -> bingo\nG googol\nA -> apple\nBGA -> bingoogolapple"
        android:textColor="@android:color/black" />

    <TextView
        android:id="@+id/tv_tip_close"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="6dp"
        android:layout_marginEnd="6dp"
        android:layout_gravity="top"
        android:padding="4dp"
        android:text="x"
        android:textColor="@android:color/black" />
</LinearLayout>
```

* 在 Java 代码中设置浮窗位置、浮窗背景、箭头位置

```Java
new TransformersTip(anchorView, R.layout.layout_demo2_tip) {
    @Override
    protected void initView(View contentView) {
        // 点击浮窗中自定按钮关闭浮窗
        contentView.findViewById(R.id.tv_tip_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissTip();
            }
        });
    }
}
        .setArrowGravity(ArrowGravity.TO_BOTTOM_CENTER) // 设置箭头相对于浮窗的位置
        .setBgColor(Color.WHITE) // 设置背景色
        .setShadowColor(Color.parseColor("#33000000")) // 设置阴影色
        .setArrowHeightDp(6) // 设置箭头高度
        .setRadiusDp(4) // 设置浮窗圆角半径
        .setArrowOffsetXDp(0) // 设置箭头在 x 轴的偏移量
        .setArrowOffsetYDp(0) // 设置箭头在 y 轴的偏移量
        .setShadowSizeDp(6) // 设置阴影宽度

        .setTipGravity(TipGravity.TO_TOP_CENTER) // 设置浮窗相对于锚点控件展示的位置
        .setTipOffsetXDp(0) // 设置浮窗在 x 轴的偏移量
        .setTipOffsetYDp(6) // 设置浮窗在 y 轴的偏移量

        .setBackgroundDimEnabled(false) // 设置是否允许浮窗的背景变暗
        .setDismissOnTouchOutside(false) // 设置点击浮窗外部时是否自动关闭浮窗

        .show(); // 显示浮窗
```

### 方式三：对于仅有文字的浮窗，可以直接使用 SimpleTextTip，不用再写布局文件了

```java
new SimpleTextTip(anchorView)
        .setTextContent("适用于只有文字的浮窗\n不写布局文件\n在 Java 代码中设置文本内容属性") // 设置浮窗文本内容
        .setTextPaddingDp(12) // 设置文字与浮窗边框的间距
        .setTextColor(Color.BLACK) // 设置文字颜色
        .setTextSizeSp(14) // 设置文字大小
        .setTextGravity(Gravity.CENTER) // 设置文字对其方式
        .setLineSpacingExtraDp(4) // 设置文字行间距

        .setArrowGravity(ArrowGravity.TO_BOTTOM_ALIGN_START) // 设置箭头相对于浮窗的位置
        .setBgColor(Color.WHITE) // 设置背景色
        .setShadowColor(Color.parseColor("#33000000")) // 设置阴影色
        .setArrowHeightDp(6) // 设置箭头高度
        .setRadiusDp(4) // 设置浮窗圆角半径
        .setArrowOffsetXDp(0) // 设置箭头在 x 轴的偏移量
        .setArrowOffsetYDp(0) // 设置箭头在 y 轴的偏移量
        .setShadowSizeDp(6) // 设置阴影宽度

        .setTipGravity(TipGravity.TO_TOP_ALIGN_START) // 设置浮窗相对于锚点控件展示的位置
        .setTipOffsetXDp(0) // 设置浮窗在 x 轴的偏移量
        .setTipOffsetYDp(6) // 设置浮窗在 y 轴的偏移量

        .setBackgroundDimEnabled(false) // 设置是否允许浮窗的背景变暗
        .setDismissOnTouchOutside(true) // 设置点击浮窗外部时是否自动关闭浮窗

        .show(); // 显示浮窗
```

### TipGravity 说明

* 通过 TipGravity.xxxx 来设置浮窗相对于锚点控件展示的位置
![TipGravity说明](https://user-images.githubusercontent.com/8949716/63364371-04905280-c3a8-11e9-97f5-83191e19463b.png)

### ArrowGravity 说明

* 通过 ArrowGravity.xxxx 来设置箭头相对于浮窗的位置

![ArrowGravity说明](https://user-images.githubusercontent.com/8949716/63364360-fe9a7180-c3a7-11e9-8686-bfe9ff86b3a4.png)

## TransformersTipLinearLayout 和 TransformersTipRelativeLayout 自定义属性说明
```xml
<declare-styleable name="ArrowDrawable">
    <!-- 背景色 -->
    <attr format="reference|color" name="ad_bgColor" />
    <!-- 阴影色 -->
    <attr format="reference|color" name="ad_shadowColor" />
    <!-- 箭头高度 -->
    <attr format="dimension" name="ad_arrowHeight" />
    <!-- 阴影宽度 -->
    <attr format="dimension" name="ad_shadowSize" />
    <!-- 浮窗圆角半径 -->
    <attr format="dimension" name="ad_radius" />
    <!-- 箭头在 x 轴的偏移量 -->
    <attr format="dimension" name="ad_arrowExtraOffsetX" />
    <!-- 箭头在 y 轴的偏移量 -->
    <attr format="dimension" name="ad_arrowExtraOffsetY" />
    <!-- 箭头相对于浮窗的位置 -->
    <attr name="ad_arrowGravity">
        <flag name="to_top_align_start" value="65" />
        <flag name="to_top_center" value="129" />
        <flag name="to_top_align_end" value="257" />

        <flag name="align_top_to_start" value="34" />
        <flag name="align_top_to_end" value="514" />

        <flag name="center_to_start" value="36" />
        <flag name="center_to_end" value="516" />

        <flag name="align_bottom_to_start" value="40" />
        <flag name="align_bottom_to_end" value="520" />

        <flag name="to_bottom_align_start" value="80" />
        <flag name="to_bottom_center" value="144" />
        <flag name="to_bottom_align_end" value="272" />
    </attr>
</declare-styleable>
```

## 代码是最好的老师，更多详细用法请查看 [demo](https://github.com/bingoogolapple/BGATransformersTip-Android/tree/master/demo):feet:

## 关于我

| 个人主页 | 邮箱 | BGA系列开源库QQ群
| ------------- | ------------ | ------------ |
| <a  href="http://www.bingoogolapple.cn" target="_blank">bingoogolapple.cn</a>  | <a href="mailto:bingoogolapple@gmail.com" target="_blank">bingoogolapple@gmail.com</a> | ![BGA_CODE_CLUB](http://bgashare.bingoogolapple.cn/BGA_CODE_CLUB.png?imageView2/2/w/200) |

## 打赏支持

如果您觉得 BGA 系列开源库帮你节省了大量的开发时间，请扫描下方的二维码随意打赏，要是能打赏个 10.24 :monkey_face:就太:thumbsup:了。您的支持将鼓励我继续创作:octocat:

如果您目前正打算购买通往墙外的梯子，可以使用我的邀请码「YFQ9Q3B」购买 [Lantern](https://github.com/getlantern/forum)，双方都赠送三个月的专业版使用时间:beers:

<p align="center">
  <img src="http://bgashare.bingoogolapple.cn/bga_pay.png?imageView2/2/w/450" width="450">
</p>

## License

    Copyright 2019 bingoogolapple

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
