package cn.bingoogolapple.transformerstip.demo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.transformerstip.ArrowDrawable;
import cn.bingoogolapple.transformerstip.TransformersTip;
import cn.bingoogolapple.transformerstip.gravity.ArrowGravity;
import cn.bingoogolapple.transformerstip.gravity.TipGravity;

public class TestActivity extends AppCompatActivity {
    private List<View> mAnchorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initAnchorList();
        initGravityDemo();
    }

    private void initAnchorList() {
        mAnchorList = new ArrayList<>();
        mAnchorList.add(findViewById(R.id.tv_anchor_one));
        mAnchorList.add(findViewById(R.id.tv_anchor_two));
        mAnchorList.add(findViewById(R.id.tv_anchor_three));
        mAnchorList.add(findViewById(R.id.tv_anchor_four));
        mAnchorList.add(findViewById(R.id.tv_anchor_five));
        mAnchorList.add(findViewById(R.id.tv_anchor_six));
        mAnchorList.add(findViewById(R.id.tv_anchor_seven));
        mAnchorList.add(findViewById(R.id.tv_anchor_eight));
        mAnchorList.add(findViewById(R.id.tv_anchor_nine));
    }

    private void initGravityDemo() {
        ChipGroup chipGroup = findViewById(R.id.cg_gravity);
        for (Field field : TipGravity.class.getDeclaredFields()) {
            Chip chip = new Chip(this);
            chip.setText(field.getName());
            try {
                chip.setTag(field.getInt(field.getType()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTip((Integer) v.getTag());
                }
            });
            chipGroup.addView(chip);
        }
    }

    private void showTip(@TipGravity int tipGravity) {
        for (View anchorView : mAnchorList) {
            new TransformersTip(anchorView, R.layout.layout_test_tip) {
                private TextView mTipContentTv;
                private RecyclerView mTipContentRv;

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
                    mTipContentTv = contentView.findViewById(R.id.tv_tip_content);
                    mTipContentTv.setText("修改后的标题");

                    mTipContentRv = contentView.findViewById(R.id.rv_tip_content);
                    mTipContentRv.setAdapter(new RecyclerView.Adapter() {
                        @NonNull
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            return new RecyclerView.ViewHolder(new TextView(parent.getContext())) {
                            };
                        }

                        @Override
                        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                            ((TextView) holder.itemView).setText("第 " + position + " 项");
                        }

                        @Override
                        public int getItemCount() {
                            return 15;
                        }
                    });
                }
            }.setTipGravity(tipGravity) // 设置浮窗相对于锚点控件展示的位置
                    .setTipOffsetXDp(0) // 设置浮窗在 x 轴的偏移量
                    .setTipOffsetYDp(0) // 设置浮窗在 y 轴的偏移量
                    .setBackgroundDimEnabled(false) // 设置是否允许浮窗的背景变暗
                    .show(); // 显示浮窗
        }
    }
}
