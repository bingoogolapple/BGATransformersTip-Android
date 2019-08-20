package cn.bingoogolapple.transformerstip;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.DimenRes;
import androidx.annotation.LayoutRes;
import androidx.core.widget.PopupWindowCompat;
import cn.bingoogolapple.transformerstip.gravity.HorizontalGravity;
import cn.bingoogolapple.transformerstip.gravity.TipGravity;
import cn.bingoogolapple.transformerstip.gravity.VerticalGravity;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:19/8/19 21:23
 * 描述:
 */
public abstract class TransformersTip extends PopupWindow implements PopupWindow.OnDismissListener {
    private View mAnchorView; // 锚点控件
    private int mTipGravity; // 浮窗相对于锚点控件展示的位置
    private int mTipOffsetX; // 浮窗在 x 轴的偏移量
    private int mTipOffsetY; // 浮窗在 y 轴的偏移量

    /**
     * 锚点控件
     *
     * @param anchorView  锚点控件
     * @param layoutResId 浮窗布局资源 id
     */
    public TransformersTip(View anchorView, @LayoutRes int layoutResId) {
        super(anchorView.getContext());
        mAnchorView = anchorView;
        mTipGravity = TipGravity.TO_TOP_CENTER;
        mTipOffsetX = 0;
        mTipOffsetY = 0;

        View contentView = LayoutInflater.from(anchorView.getContext()).inflate(layoutResId, null);
        setContentView(contentView);
        initBackground(contentView);
        initView(contentView);
        contentView.measure(makeDropDownMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT), makeDropDownMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT));
        initDefaultAttributes();
        customAttributes();
    }

    // ================ 复写 START ================

    /**
     * 初始化浮窗背景
     */
    protected void initBackground(View contentView) {
    }

    /**
     * 初始化控件
     */
    protected void initView(View contentView) {
    }

    /**
     * 自定义属性
     */
    protected void customAttributes() {
    }

    /**
     * 初始 PopupWindow 消失事件
     */
    protected void handleOnDismiss() {
    }
    // ================ 复写 END ================

    // ================ 对外暴露 START ================

    /**
     * 设置浮窗相对于锚点控件展示的位置
     */
    public TransformersTip setTipGravity(@TipGravity int tipGravity) {
        mTipGravity = tipGravity;
        return this;
    }

    /**
     * 设置浮窗在 x 轴的偏移量
     */
    public TransformersTip setTipOffsetXDp(int tipOffsetX) {
        mTipOffsetX = tipOffsetX;
        return this;
    }

    /**
     * 设置浮窗在 x 轴的偏移量
     */
    public TransformersTip setTipOffsetXRes(@DimenRes int resId) {
        mTipOffsetX = mAnchorView.getResources().getDimensionPixelOffset(resId);
        return this;
    }

    /**
     * 设置浮窗在 y 轴的偏移量
     */
    public TransformersTip setTipOffsetYDp(int tipOffsetY) {
        mTipOffsetY = tipOffsetY;
        return this;
    }

    /**
     * 设置浮窗在 y 轴的偏移量
     */
    public TransformersTip setTipOffsetYRes(@DimenRes int resId) {
        mTipOffsetY = mAnchorView.getResources().getDimensionPixelOffset(resId);
        return this;
    }

    /**
     * 显示浮窗
     */
    public TransformersTip show() {
        if ((mTipGravity & VerticalGravity.CENTER) == VerticalGravity.CENTER ||
                (mTipGravity & VerticalGravity.ALIGN_TOP) == VerticalGravity.ALIGN_TOP ||
                (mTipGravity & VerticalGravity.TO_TOP) == VerticalGravity.TO_TOP ||
                (mTipGravity & HorizontalGravity.CENTER) == HorizontalGravity.CENTER ||
                (mTipGravity & HorizontalGravity.ALIGN_END) == HorizontalGravity.ALIGN_END) {
            mAnchorView.post(new Runnable() {
                @Override
                public void run() {
                    showAsDropDownInternal();
                }
            });
        } else {
            showAsDropDownInternal();
        }
        return this;
    }
    // ================ 对外暴露 END ================

    @Override
    public void onDismiss() {
        handleOnDismiss();
    }

    /**
     * 初始化默认属性
     */
    private void initDefaultAttributes() {
        setOnDismissListener(this);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setClippingEnabled(false);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private boolean isExist(@TipGravity int tipGravity, int directionGravity) {
        return (tipGravity & directionGravity) == directionGravity;
    }

    private void showAsDropDownInternal() {
        // 获取 offsetY
        int offsetY = getToBottomOffsetY();
        if (isExist(mTipGravity, VerticalGravity.TO_TOP)) {
            offsetY = getToTopOffsetY(mAnchorView);
        } else if (isExist(mTipGravity, VerticalGravity.ALIGN_TOP)) {
            offsetY = getAlignTopOffsetY(mAnchorView);
        } else if (isExist(mTipGravity, VerticalGravity.CENTER)) {
            offsetY = getCenterOffsetY(mAnchorView);
        } else if (isExist(mTipGravity, VerticalGravity.ALIGN_BOTTOM)) {
            offsetY = getAlignBottomOffsetY();
        } else if (isExist(mTipGravity, VerticalGravity.TO_BOTTOM)) {
            offsetY = getToBottomOffsetY();
        }

        // 获取 offsetX
        int gravity = Gravity.START;
        int offsetX = getCenterOffsetX(mAnchorView);
        if (isExist(mTipGravity, HorizontalGravity.TO_START)) {
            offsetX = getToStartOffsetX();
        } else if (isExist(mTipGravity, HorizontalGravity.ALIGN_START)) {
            offsetX = getAlignStartOffsetX();
        } else if (isExist(mTipGravity, HorizontalGravity.CENTER)) {
            offsetX = getCenterOffsetX(mAnchorView);
        } else if (isExist(mTipGravity, HorizontalGravity.ALIGN_END)) {
            offsetX = getAlignEndOffsetX(mAnchorView);
        } else if (isExist(mTipGravity, HorizontalGravity.TO_END)) {
            offsetX = getToEndOffsetX();
            gravity = Gravity.END;
        }

        // 获取屏幕宽高
        Point point = new Point();
        mAnchorView.getDisplay().getSize(point);
        // 获取锚点控件在屏幕上的位置
        int[] xy = new int[2];
        mAnchorView.getLocationInWindow(xy);

        // x 轴方向起始点
        int startX = xy[0];
        if (gravity == Gravity.END) {
            startX = xy[0] + mAnchorView.getWidth();
        }
        // x 轴坐标
        int xoff = offsetX + mTipOffsetX;
        if (startX + xoff + getContentView().getMeasuredWidth() > point.x) {
            // x 轴方向展示到屏幕外了，修正到屏幕内
            xoff = point.x - getContentView().getMeasuredWidth() - startX;
        }

        // y 轴方向起始点
        int startY = xy[1] + mAnchorView.getHeight();
        // y 轴坐标
        int yoff = offsetY + mTipOffsetY;
        if (startY + yoff + getContentView().getMeasuredHeight() > point.y) {
            // y 轴方向展示到屏幕外了，修正到屏幕内
            yoff = point.y - getContentView().getMeasuredHeight() - startY;
        }

        PopupWindowCompat.showAsDropDown(this, mAnchorView, xoff, yoff, gravity);
    }

    private static int makeDropDownMeasureSpec(int measureSpec) {
        int mode;
        if (measureSpec == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mode = View.MeasureSpec.AT_MOST;
        } else {
            mode = View.MeasureSpec.EXACTLY;
        }
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec), mode);
    }

    private int getToBottomOffsetY() {
        return 0;
    }

    private int getAlignBottomOffsetY() {
        return -getContentView().getMeasuredHeight();
    }

    private int getCenterOffsetY(View anchor) {
        return -(getContentView().getMeasuredHeight() + anchor.getHeight()) / 2;
    }

    private int getAlignTopOffsetY(View anchor) {
        return -anchor.getHeight();
    }

    private int getToTopOffsetY(View anchor) {
        return -(getContentView().getMeasuredHeight() + anchor.getHeight());
    }

    private int getToStartOffsetX() {
        return -getContentView().getMeasuredWidth();
    }

    private int getAlignStartOffsetX() {
        return 0;
    }

    private int getCenterOffsetX(View anchor) {
        return (anchor.getWidth() - getContentView().getMeasuredWidth()) / 2;
    }

    private int getAlignEndOffsetX(View anchor) {
        return anchor.getWidth() - getContentView().getMeasuredWidth();
    }

    private int getToEndOffsetX() {
        return 0;
    }
}
