package cn.bingoogolapple.transformerstip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.bingoogolapple.transformerstip.gravity.ArrowGravity;
import cn.bingoogolapple.transformerstip.gravity.HorizontalGravity;
import cn.bingoogolapple.transformerstip.gravity.VerticalGravity;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:19/8/19 21:34
 * 描述:
 */
public class ArrowDrawable extends Drawable {
    private Context mContext;
    private Path mPath;
    private Paint mPaint;

    private int mArrowHeight;
    private int mRadius;
    private int mArrowGravity;
    private int mArrowOffsetX;
    private int mArrowOffsetY;
    private int mShadowSize;
    private int mBgColor;
    private int mShadowColor;
    private boolean mAlreadyExpandShadowAndArrowPadding;

    /**
     * 在 Java 代码中设置浮窗背景
     *
     * @param anchor 锚点控件
     */
    public ArrowDrawable(View anchor) {
        initDefaultAttrs(anchor.getContext());
        anchor.setBackground(this);
    }

    /**
     * 自定义控件构造方法设置为背景
     */
    public ArrowDrawable(Context context, AttributeSet attrs) {
        initDefaultAttrs(context);
        initCustomAttrs(context, attrs);
    }

    private void initDefaultAttrs(Context context) {
        mContext = context;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);

        mBgColor = Color.BLACK;
        mShadowColor = Color.parseColor("#33000000");
        mArrowHeight = dp2px(context, 6);
        mRadius = dp2px(context, 4);
        mShadowSize = 0;
        mArrowOffsetX = 0;
        mArrowOffsetY = 0;
        mArrowGravity = ArrowGravity.TO_BOTTOM_CENTER;
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArrowDrawable);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    private void initCustomAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.ArrowDrawable_ad_bgColor) {
            mBgColor = typedArray.getColor(attr, mBgColor);
        } else if (attr == R.styleable.ArrowDrawable_ad_shadowColor) {
            mShadowColor = typedArray.getColor(attr, mShadowColor);
        } else if (attr == R.styleable.ArrowDrawable_ad_arrowHeight) {
            mArrowHeight = typedArray.getDimensionPixelSize(attr, mArrowHeight);
        } else if (attr == R.styleable.ArrowDrawable_ad_shadowSize) {
            mShadowSize = typedArray.getDimensionPixelSize(attr, mShadowSize);
        } else if (attr == R.styleable.ArrowDrawable_ad_radius) {
            mRadius = typedArray.getDimensionPixelSize(attr, mRadius);
        } else if (attr == R.styleable.ArrowDrawable_ad_arrowExtraOffsetX) {
            mArrowOffsetX = typedArray.getDimensionPixelSize(attr, mArrowOffsetX);
        } else if (attr == R.styleable.ArrowDrawable_ad_arrowExtraOffsetY) {
            mArrowOffsetY = typedArray.getDimensionPixelSize(attr, mArrowOffsetY);
        } else if (attr == R.styleable.ArrowDrawable_ad_arrowGravity) {
            mArrowGravity = typedArray.getInt(attr, mArrowGravity);
        }
    }

    /**
     * 设置背景色
     */
    public ArrowDrawable setBgColor(int bgColor) {
        mBgColor = bgColor;
        return this;
    }

    /**
     * 设置背景色
     */
    public ArrowDrawable setBgColorRes(@ColorRes int bgColorRes) {
        mBgColor = mContext.getResources().getColor(bgColorRes);
        return this;
    }

    /**
     * 设置阴影色
     */
    public ArrowDrawable setShadowColor(int shadowColor) {
        mShadowColor = shadowColor;
        return this;
    }

    /**
     * 设置阴影色
     */
    public ArrowDrawable setShadowColorRes(@ColorRes int shadowColorRes) {
        mShadowColor = mContext.getResources().getColor(shadowColorRes);
        return this;
    }

    /**
     * 设置箭头高度
     */
    public ArrowDrawable setArrowHeightDp(int arrowHeight) {
        mArrowHeight = dp2px(mContext, arrowHeight);
        return this;
    }

    /**
     * 设置箭头高度
     */
    public ArrowDrawable setArrowHeightRes(@DimenRes int resId) {
        mArrowHeight = mContext.getResources().getDimensionPixelOffset(resId);
        return this;
    }

    /**
     * 设置浮窗圆角半径
     */
    public ArrowDrawable setRadiusDp(int radius) {
        mRadius = dp2px(mContext, radius);
        return this;
    }

    /**
     * 设置浮窗圆角半径
     */
    public ArrowDrawable setRadiusRes(@DimenRes int resId) {
        mRadius = mContext.getResources().getDimensionPixelOffset(resId);
        return this;
    }

    /**
     * 设置箭头相对于浮窗的位置
     */
    public ArrowDrawable setArrowGravity(@ArrowGravity int arrowGravity) {
        mArrowGravity = arrowGravity;
        return this;
    }

    /**
     * 设置箭头在 x 轴的偏移量
     */
    public ArrowDrawable setArrowOffsetXDp(int arrowOffsetX) {
        mArrowOffsetX = dp2px(mContext, arrowOffsetX);
        return this;
    }

    /**
     * 设置箭头在 x 轴的偏移量
     */
    public ArrowDrawable setArrowOffsetXRes(@DimenRes int resId) {
        mArrowOffsetX = mContext.getResources().getDimensionPixelOffset(resId);
        return this;
    }

    /**
     * 设置箭头在 y 轴的偏移量
     */
    public ArrowDrawable setArrowOffsetYDp(int arrowOffsetX) {
        mArrowOffsetY = dp2px(mContext, arrowOffsetX);
        return this;
    }

    /**
     * 设置箭头在 y 轴的偏移量
     */
    public ArrowDrawable setArrowOffsetYRes(@DimenRes int resId) {
        mArrowOffsetY = mContext.getResources().getDimensionPixelOffset(resId);
        return this;
    }

    /**
     * 设置阴影宽度
     */
    public ArrowDrawable setShadowSizeDp(int shadowSize) {
        mShadowSize = shadowSize;
        return this;
    }

    /**
     * 设置阴影宽度
     */
    public ArrowDrawable setShadowSizeRes(@DimenRes int resId) {
        mShadowSize = mContext.getResources().getDimensionPixelOffset(resId);
        return this;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mPath != null) {
            if (mShadowSize > 0) {
                mPaint.setMaskFilter(new BlurMaskFilter(mShadowSize, BlurMaskFilter.Blur.OUTER));
                mPaint.setColor(mShadowColor);
                canvas.drawPath(mPath, mPaint);
            }
            mPaint.setMaskFilter(null);
            mPaint.setColor(mBgColor);
            canvas.drawPath(mPath, mPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    protected void onBoundsChange(Rect viewRect) {
        if (mPath == null) {
            mPath = new Path();
        } else {
            mPath.reset();
        }

        RectF excludeShadowRectF = new RectF(viewRect);
        excludeShadowRectF.inset(mShadowSize, mShadowSize);

        PointF centerPointF = new PointF();
        // 箭头（旋转了 90 度后的正方形区域）中心 x 坐标
        if (isExist(HorizontalGravity.TO_START)) {
            excludeShadowRectF.left += mArrowHeight;
            centerPointF.x = excludeShadowRectF.left;
        } else if (isExist(HorizontalGravity.ALIGN_START)) {
            centerPointF.x = excludeShadowRectF.left + mArrowHeight;
        } else if (isExist(HorizontalGravity.CENTER)) {
            centerPointF.x = excludeShadowRectF.width() / 2;
        } else if (isExist(HorizontalGravity.ALIGN_END)) {
            centerPointF.x = excludeShadowRectF.right - mArrowHeight;
        } else if (isExist(HorizontalGravity.TO_END)) {
            excludeShadowRectF.right -= mArrowHeight;
            centerPointF.x = excludeShadowRectF.right;
        }

        // 箭头（旋转了 90 度后的正方形区域）中心 y 坐标
        if (isExist(VerticalGravity.TO_TOP)) {
            excludeShadowRectF.top += mArrowHeight;
            centerPointF.y = excludeShadowRectF.top;
        } else if (isExist(VerticalGravity.ALIGN_TOP)) {
            centerPointF.y = excludeShadowRectF.top + mArrowHeight;
        } else if (isExist(VerticalGravity.CENTER)) {
            centerPointF.y = excludeShadowRectF.height() / 2;
        } else if (isExist(VerticalGravity.ALIGN_BOTTOM)) {
            centerPointF.y = excludeShadowRectF.bottom - mArrowHeight;
        } else if (isExist(VerticalGravity.TO_BOTTOM)) {
            excludeShadowRectF.bottom -= mArrowHeight;
            centerPointF.y = excludeShadowRectF.bottom;
        }

        // 更新箭头偏移量
        centerPointF.x += mArrowOffsetX;
        centerPointF.y += mArrowOffsetY;

        // 限制箭头（旋转了 90 度后的正方形区域）中心 x 坐标范围
        if (isExist(HorizontalGravity.ALIGN_START) || isExist(HorizontalGravity.CENTER) || isExist(HorizontalGravity.ALIGN_END)) {
            centerPointF.x = Math.max(centerPointF.x, excludeShadowRectF.left + mRadius + mArrowHeight);
            centerPointF.x = Math.min(centerPointF.x, excludeShadowRectF.right - mRadius - mArrowHeight);
        }
        if (isExist(HorizontalGravity.TO_START) || isExist(HorizontalGravity.TO_END)) {
            centerPointF.x = Math.max(centerPointF.x, excludeShadowRectF.left);
            centerPointF.x = Math.min(centerPointF.x, excludeShadowRectF.right);
        }
        // 限制箭头（旋转了 90 度后的正方形区域）中心 y 坐标范围
        if (isExist(VerticalGravity.ALIGN_TOP) || isExist(VerticalGravity.CENTER) || isExist(VerticalGravity.ALIGN_BOTTOM)) {
            centerPointF.y = Math.max(centerPointF.y, excludeShadowRectF.top + mRadius + mArrowHeight);
            centerPointF.y = Math.min(centerPointF.y, excludeShadowRectF.bottom - mRadius - mArrowHeight);
        }
        if (isExist(VerticalGravity.TO_TOP) || isExist(VerticalGravity.TO_BOTTOM)) {
            centerPointF.y = Math.max(centerPointF.y, excludeShadowRectF.top);
            centerPointF.y = Math.min(centerPointF.y, excludeShadowRectF.bottom);
        }

        // 箭头区域（其实是旋转了 90 度后的正方形区域）
        Path arrowPath = new Path();
        arrowPath.moveTo(centerPointF.x - mArrowHeight, centerPointF.y);
        arrowPath.lineTo(centerPointF.x, centerPointF.y - mArrowHeight);
        arrowPath.lineTo(centerPointF.x + mArrowHeight, centerPointF.y);
        arrowPath.lineTo(centerPointF.x, centerPointF.y + mArrowHeight);
        arrowPath.close();

        mPath.addRoundRect(excludeShadowRectF, mRadius, mRadius, Path.Direction.CW);
        mPath.addPath(arrowPath);

        invalidateSelf();
    }

    public void expandShadowAndArrowPadding(View contentView) {
        if (mAlreadyExpandShadowAndArrowPadding) {
            // 使用 TransformersTipLinearLayout 或 TransformersTipRelativeLayout 优化预览效果时已经调用过了
            return;
        }
        mAlreadyExpandShadowAndArrowPadding = true;

        Rect paddingRect = new Rect();
        paddingRect.left = contentView.getPaddingStart() + mShadowSize;
        paddingRect.top = contentView.getPaddingTop() + mShadowSize;
        paddingRect.right = contentView.getPaddingEnd() + mShadowSize;
        paddingRect.bottom = contentView.getPaddingBottom() + mShadowSize;

        if (isExist(HorizontalGravity.TO_START)) {
            paddingRect.left += mArrowHeight;
        } else if (isExist(VerticalGravity.TO_TOP)) {
            paddingRect.top += mArrowHeight;
        } else if (isExist(HorizontalGravity.TO_END)) {
            paddingRect.right += mArrowHeight;
        } else if (isExist(VerticalGravity.TO_BOTTOM)) {
            paddingRect.bottom += mArrowHeight;
        }
        contentView.setPaddingRelative(paddingRect.left, paddingRect.top, paddingRect.right, paddingRect.bottom);
    }

    private boolean isExist(int directionGravity) {
        return (mArrowGravity & directionGravity) == directionGravity;
    }

    private static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }
}
