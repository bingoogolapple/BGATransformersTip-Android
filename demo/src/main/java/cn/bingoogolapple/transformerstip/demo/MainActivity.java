package cn.bingoogolapple.transformerstip.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import cn.bingoogolapple.transformerstip.ArrowDrawable;
import cn.bingoogolapple.transformerstip.TransformersTip;
import cn.bingoogolapple.transformerstip.gravity.ArrowGravity;
import cn.bingoogolapple.transformerstip.gravity.TipGravity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.mb_anchor_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demo1();
            }
        });
        findViewById(R.id.mb_anchor_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demo2();
            }
        });

        findViewById(R.id.mb_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
    }

    // 在 Java 代码中设置浮窗位置、浮窗背景、箭头位置
    private void demo1() {
        View anchorView = findViewById(R.id.mb_anchor_one);
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
                .show(); // 显示浮窗
    }

    // 方式二：在 Java 代码中设置浮窗位置，在布局文件中设置浮窗背景、箭头位置
    private void demo2() {
        View anchorView = findViewById(R.id.mb_anchor_two);
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
                .show(); // 显示浮窗
    }
}
