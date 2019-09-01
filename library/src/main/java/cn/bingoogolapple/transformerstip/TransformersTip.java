package cn.bingoogolapple.transformerstip;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.LayoutRes;
import androidx.core.widget.PopupWindowCompat;
import cn.bingoogolapple.transformerstip.gravity.ArrowGravity;
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
    private boolean mBackgroundDimEnabled = false; // 是否允许浮窗的背景变暗
    private ArrowDrawable mArrowDrawable;

    /**
     * 锚点控件
     *
     * @param anchorView  锚点控件
     * @param contentView 浮窗内容视图
     */
    public TransformersTip(View anchorView, View contentView) {
        super(anchorView.getContext());
        mAnchorView = anchorView;

        Drawable backgroundDrawable = contentView.getBackground();
        if (backgroundDrawable instanceof ArrowDrawable) {
            mArrowDrawable = (ArrowDrawable) backgroundDrawable;
        } else {
            mArrowDrawable = new ArrowDrawable(contentView);
        }
        contentView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        setContentView(contentView);
        initView(contentView);
        initDefaultAttributes();
        customAttributes();
    }

    /**
     * 锚点控件
     *
     * @param anchorView  锚点控件
     * @param layoutResId 浮窗布局资源 id
     */
    public TransformersTip(View anchorView, @LayoutRes int layoutResId) {
        this(anchorView, LayoutInflater.from(anchorView.getContext()).inflate(layoutResId, null));
    }

    // ================ 复写 START ================

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
     * 设置背景色
     */
    public TransformersTip setBgColor(int value) {
        mArrowDrawable.setBgColor(value);
        return this;
    }

    /**
     * 设置背景色
     */
    public TransformersTip setBgColorRes(@ColorRes int resId) {
        mArrowDrawable.setBgColor(getContentView().getResources().getColor(resId));
        return this;
    }

    /**
     * 设置阴影色
     */
    public TransformersTip setShadowColor(int value) {
        mArrowDrawable.setShadowColor(value);
        return this;
    }

    /**
     * 设置阴影色
     */
    public TransformersTip setShadowColorRes(@ColorRes int resId) {
        mArrowDrawable.setShadowColor(getContentView().getResources().getColor(resId));
        return this;
    }

    /**
     * 设置箭头高度
     */
    public TransformersTip setArrowHeightDp(int value) {
        mArrowDrawable.setArrowHeightPx(ArrowDrawable.dp2px(getContentView().getContext(), value));
        return this;
    }

    /**
     * 设置箭头高度
     */
    public TransformersTip setArrowHeightRes(@DimenRes int resId) {
        mArrowDrawable.setArrowHeightPx(getContentView().getResources().getDimensionPixelOffset(resId));
        return this;
    }

    /**
     * 设置浮窗圆角半径
     */
    public TransformersTip setRadiusDp(int value) {
        mArrowDrawable.setRadiusPx(ArrowDrawable.dp2px(getContentView().getContext(), value));
        return this;
    }

    /**
     * 设置浮窗圆角半径
     */
    public TransformersTip setRadiusRes(@DimenRes int resId) {
        mArrowDrawable.setRadiusPx(getContentView().getResources().getDimensionPixelOffset(resId));
        return this;
    }

    /**
     * 设置箭头相对于浮窗的位置
     */
    public TransformersTip setArrowGravity(@ArrowGravity int arrowGravity) {
        mArrowDrawable.setArrowGravity(arrowGravity);
        return this;
    }

    /**
     * 设置箭头在 x 轴的偏移量
     */
    public TransformersTip setArrowOffsetXDp(int value) {
        mArrowDrawable.setArrowOffsetXPx(ArrowDrawable.dp2px(getContentView().getContext(), value));
        return this;
    }

    /**
     * 设置箭头在 x 轴的偏移量
     */
    public TransformersTip setArrowOffsetXRes(@DimenRes int resId) {
        mArrowDrawable.setArrowOffsetXPx(getContentView().getResources().getDimensionPixelOffset(resId));
        return this;
    }

    /**
     * 设置箭头在 y 轴的偏移量
     */
    public TransformersTip setArrowOffsetYDp(int value) {
        mArrowDrawable.setArrowOffsetYPx(ArrowDrawable.dp2px(getContentView().getContext(), value));
        return this;
    }

    /**
     * 设置箭头在 y 轴的偏移量
     */
    public TransformersTip setArrowOffsetYRes(@DimenRes int resId) {
        mArrowDrawable.setArrowOffsetYPx(getContentView().getResources().getDimensionPixelOffset(resId));
        return this;
    }

    /**
     * 设置阴影宽度
     */
    public TransformersTip setShadowSizeDp(int value) {
        mArrowDrawable.setShadowSizePx(ArrowDrawable.dp2px(getContentView().getContext(), value));
        return this;
    }

    /**
     * 设置阴影宽度
     */
    public TransformersTip setShadowSizeRes(@DimenRes int resId) {
        mArrowDrawable.setShadowSizePx(getContentView().getResources().getDimensionPixelOffset(resId));
        return this;
    }

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
    public TransformersTip setTipOffsetXDp(int value) {
        mTipOffsetX = ArrowDrawable.dp2px(getContentView().getContext(), value);
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
    public TransformersTip setTipOffsetYDp(int value) {
        mTipOffsetY = ArrowDrawable.dp2px(getContentView().getContext(), value);
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
     * 设置是否允许浮窗的背景变暗
     */
    public TransformersTip setBackgroundDimEnabled(boolean backgroundDimEnabled) {
        mBackgroundDimEnabled = backgroundDimEnabled;
        return this;
    }

    /**
     * 显示浮窗
     */
    public TransformersTip show() {
        expandShadowAndArrowPadding();
        getContentView().measure(makeDropDownMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT), makeDropDownMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT));

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

    private void expandShadowAndArrowPadding() {
        View contentView = getContentView();
        Drawable background = contentView.getBackground();
        if (!(background instanceof ArrowDrawable)) {
            return;
        }

        ArrowDrawable arrowDrawable = (ArrowDrawable) background;
        arrowDrawable.expandShadowAndArrowPadding(contentView);
    }

    /**
     * 初始化默认属性
     */
    private void initDefaultAttributes() {
        mTipGravity = TipGravity.TO_TOP_CENTER;
        mTipOffsetX = 0;
        mTipOffsetY = 0;

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
        int[] anchorXy = new int[2];
        mAnchorView.getLocationInWindow(anchorXy);

        // x 轴方向起始点
        int startX = anchorXy[0];
        if (gravity == Gravity.END) {
            startX = anchorXy[0] + mAnchorView.getWidth();
        }
        // x 轴坐标
        int xoff = offsetX + mTipOffsetX;
        if (startX + xoff + getContentView().getMeasuredWidth() > point.x) {
            // x 轴方向展示到屏幕外了，修正到屏幕内
            xoff = point.x - getContentView().getMeasuredWidth() - startX;
        } else if (startX + xoff < 0) {
            xoff = 0;
        }

        // y 轴方向起始点
        int startY = anchorXy[1] + mAnchorView.getHeight();
        // y 轴坐标
        int yoff = offsetY + mTipOffsetY;
        if (startY + yoff + getContentView().getMeasuredHeight() > point.y) {
            // y 轴方向展示到屏幕外了，修正到屏幕内
            yoff = point.y - getContentView().getMeasuredHeight() - startY;
        } else if (startY + yoff < 0) {
            yoff = -startY;
        }

        PopupWindowCompat.showAsDropDown(this, mAnchorView, xoff, yoff, gravity);

        if (mBackgroundDimEnabled) {
            mAnchorView.post(new Runnable() {
                @Override
                public void run() {
                    dimBehind();
                }
            });
        }
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

    private void dimBehind() {
        View container;
        if (getBackground() == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View) getContentView().getParent();
            } else {
                container = getContentView();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View) getContentView().getParent().getParent();
            } else {
                container = (View) getContentView().getParent();
            }
        }
        WindowManager windowManager = (WindowManager) getContentView().getContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) container.getLayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.3f;
        if (windowManager != null) {
            windowManager.updateViewLayout(container, layoutParams);
        }
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
