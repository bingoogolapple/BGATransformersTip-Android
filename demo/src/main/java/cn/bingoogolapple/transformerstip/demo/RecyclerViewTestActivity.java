package cn.bingoogolapple.transformerstip.demo;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.transformerstip.SimpleTextTip;
import cn.bingoogolapple.transformerstip.gravity.ArrowGravity;
import cn.bingoogolapple.transformerstip.gravity.TipGravity;

public class RecyclerViewTestActivity extends AppCompatActivity {
    private RecyclerView mContentRv;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_test);
        mContentRv = findViewById(R.id.rv_content);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new RecyclerView.ViewHolder(new TextView(parent.getContext())) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
                final TextView textView = ((TextView) holder.itemView);
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
                textView.setPaddingRelative(10, 10, 10, 10);
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundResource(R.color.colorPrimaryDark);
                textView.setText("第 " + position + " 项" + (position % 2 == 0 ? "->点击浮层后 notify 当前条目" : "点击浮层后向上滚动 8 个条目"));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTip(textView, position);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return 500;
            }
        };
        mContentRv.setAdapter(mAdapter);
        mContentRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = 10;
                outRect.top = 10;
                outRect.right = 10;
                outRect.bottom = 10;
            }
        });
    }


    private void showTip(TextView anchorView, final int position) {
        new SimpleTextTip(anchorView) {
            @Override
            protected void initView(View contentView) {
                contentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position % 2 == 0) {
                            mAdapter.notifyItemChanged(position);
                        } else {
                            // 调大这个数字测试
                            int scrollCount = 8;
                            mContentRv.smoothScrollToPosition(position + scrollCount);
//                            mAdapter.notifyItemRangeChanged(position, 8);
                        }
                    }
                });
            }
        }
                .setTextContent("第 " + position + " 项的浮层\n" + (position % 2 == 0 ? "点击浮层后 notify 当前条目" : "点击浮层后向上滚动 8 个条目"))
                .setTextColor(Color.WHITE)
                .setArrowGravity(ArrowGravity.TO_TOP_CENTER) // 设置箭头相对于浮窗的位置
                .setBgColor(Color.BLACK) // 设置背景色
                .setArrowHeightDp(6) // 设置箭头高度
                .setRadiusDp(4) // 设置浮窗圆角半径
                .setArrowOffsetXDp(0) // 设置箭头在 x 轴的偏移量
                .setArrowOffsetYDp(0) // 设置箭头在 y 轴的偏移量

                .setTipGravity(TipGravity.TO_BOTTOM_CENTER) // 设置浮窗相对于锚点控件展示的位置
                .setTipOffsetXDp(0) // 设置浮窗在 x 轴的偏移量
                .setTipOffsetYDp(0) // 设置浮窗在 y 轴的偏移量
                .setBackgroundDimEnabled(false) // 设置是否允许浮窗的背景变暗
                .show(); // 显示浮窗
    }
}
