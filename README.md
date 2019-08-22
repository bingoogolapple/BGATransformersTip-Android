:running:BGATransformersTip-Android:running:
============

Android 通用 PopupWindow，支持从锚点控件的各个位置弹出浮窗，可以配置箭头指示器展示到浮窗边缘的任意位置

## 效果图

![image](https://user-images.githubusercontent.com/8949716/63393442-b56d1080-c3ed-11e9-86bc-7ee4df4d2756.png)
![效果图](https://user-images.githubusercontent.com/8949716/63364704-b4fe5680-c3a8-11e9-9185-872fc1671430.png)

## 使用说明

### 添加 Gradle 依赖
[![Download](https://api.bintray.com/packages/bingoogolapple/maven/bga-transformerstip/images/download.svg)](https://bintray.com/bingoogolapple/maven/bga-transformerstip/_latestVersion) bga-transformerstip 后面的「latestVersion」指的是左边这个 Download 徽章后面的「数字」，请自行替换。

```groovy
dependencies {
    implementation 'cn.bingoogolapple:bga-transformerstip:latestVersion@aar'
}
```

### 方式一：在 Java 代码中设置浮窗位置、浮窗背景、箭头位置
* 添加浮窗布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:paddingTop="10dp"
    android:paddingBottom="16dp"
    android:paddingStart="10dp"
    android:paddingEnd="10dp"
    android:background="@color/colorPrimaryDarkTrans"
    android:gravity="center_vertical"
    android:orientation="horizontal">

    <TextView
        android:id="@+id/tv_tip_content"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="这里 paddingBottom 比其他 padding 多了 6pd 是为了留空间给三角箭头"
        android:textColor="@android:color/white" />

    <TextView
        android:id="@+id/tv_tip_close"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_gravity="top"
        android:padding="4dp"
        android:text="X"
        android:textColor="@android:color/white" />
</LinearLayout>
```
* 在 Java 代码中设置浮窗位置、浮窗背景、箭头位置
```Java
new TransformersTip(anchorView, R.layout.layout_demo1_tip) {
    @Override
    protected void initBackground(View contentView) {
        // 在 Java 代码中设置浮窗背景以及箭头位置
        new ArrowDrawable(contentView)
                .setArrowGravity(ArrowGravity.TO_BOTTOM_CENTER) // 设置箭头相对于浮窗的位置
                .setBgColorRes(R.color.colorPrimaryDarkTrans) // 设置背景色
                .setArrowHeightDp(6) // 设置箭头高度
                .setRadiusDp(4) // 设置浮窗圆角半径
                .setArrowOffsetXDp(0) // 设置箭头在 x 轴的偏移量
                .setArrowOffsetYDp(0); // 设置箭头在 y 轴的偏移量
    }

    @Override
    protected void initView(View contentView) {
        // 点击浮窗中自定按钮关闭浮窗
        contentView.findViewById(R.id.tv_tip_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}.setTipGravity(TipGravity.TO_TOP_CENTER) // 设置浮窗相对于锚点控件展示的位置
        .setTipOffsetXDp(0) // 设置浮窗在 x 轴的偏移量
        .setTipOffsetYDp(0) // 设置浮窗在 y 轴的偏移量
        .setBackgroundDimEnabled(false) // 设置是否允许浮窗的背景变暗
        .show(); // 显示浮窗
```

### 方式二：在 Java 代码中设置浮窗位置，在布局文件中设置浮窗背景、箭头位置
* 添加浮窗布局文件，在布局文件中设置浮窗背景、箭头位置。优点是可以提前预览效果

```xml
<?xml version="1.0" encoding="utf-8"?>
<cn.bingoogolapple.transformerstip.view.TransformersTipLinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:paddingTop="16dp"
    android:paddingBottom="10dp"
    android:paddingStart="10dp"
    android:paddingEnd="10dp"
    android:gravity="center_vertical"
    android:orientation="horizontal"
    app:ad_arrowExtraOffsetX="0dp"
    app:ad_arrowExtraOffsetY="0dp"
    app:ad_arrowGravity="to_top_center"
    app:ad_arrowHeight="6dp"
    app:ad_background="@color/colorPrimaryDarkTrans"
    app:ad_radius="4dp">

    <TextView
        android:id="@+id/tv_tip_content"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="这里 paddingTop 比其他 padding 多了 6pd 是为了留空间给三角箭头"
        android:textColor="@android:color/white" />

    <TextView
        android:id="@+id/tv_tip_close"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_gravity="top"
        android:padding="4dp"
        android:text="X"
        android:textColor="@android:color/white" />
</cn.bingoogolapple.transformerstip.view.TransformersTipLinearLayout>
```
* 在 Java 代码中设置浮窗位置
```Java
new TransformersTip(anchorView, R.layout.layout_demo2_tip) {
    @Override
    protected void initView(View contentView) {
        // 点击浮窗中自定按钮关闭浮窗
        contentView.findViewById(R.id.tv_tip_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}.setTipGravity(TipGravity.TO_BOTTOM_CENTER) // 设置浮窗相对于锚点控件展示的位置
        .setTipOffsetXDp(0) // 设置浮窗在 x 轴的偏移量
        .setTipOffsetYDp(0) // 设置浮窗在 y 轴的偏移量
        .setBackgroundDimEnabled(false) // 设置是否允许浮窗的背景变暗
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
    <!-- 背景 -->
    <attr format="reference|color" name="ad_background" />
    <!-- 箭头高度 -->
    <attr format="dimension" name="ad_arrowHeight" />
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
