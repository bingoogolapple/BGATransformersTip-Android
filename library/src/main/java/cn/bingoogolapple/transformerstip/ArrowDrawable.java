package cn.bingoogolapple.transformerstip;

import android.content.Context;
import android.content.res.TypedArray;
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
        mPaint.setColor(Color.parseColor("#99000000"));

        mArrowHeight = dp2px(context, 6);
        mRadius = dp2px(context, 4);
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
        if (attr == R.styleable.ArrowDrawable_ad_background) {
            mPaint.setColor(typedArray.getColor(attr, Color.BLACK));
        } else if (attr == R.styleable.ArrowDrawable_ad_arrowHeight) {
            mArrowHeight = typedArray.getDimensionPixelSize(attr, mArrowHeight);
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
        mPaint.setColor(bgColor);
        return this;
    }

    /**
     * 设置背景色
     */
    public ArrowDrawable setBgColorRes(@ColorRes int bgColorRes) {
        mPaint.setColor(mContext.getResources().getColor(bgColorRes));
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

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mPath != null) {
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
    protected void onBoundsChange(Rect bounds) {
        if (mPath == null) {
            mPath = new Path();
        } else {
            mPath.reset();
        }

        RectF bgRectF = new RectF(bounds);

        PointF centerPointF = new PointF();
        if (isExist(mArrowGravity, VerticalGravity.TO_TOP)) {
            bgRectF.top += mArrowHeight;
            centerPointF.y = bgRectF.top;
        } else if (isExist(mArrowGravity, VerticalGravity.ALIGN_TOP)) {
            centerPointF.y = bgRectF.top + mArrowHeight;
        } else if (isExist(mArrowGravity, VerticalGravity.CENTER)) {
            centerPointF.y = bgRectF.height() / 2;
        } else if (isExist(mArrowGravity, VerticalGravity.ALIGN_BOTTOM)) {
            centerPointF.y = bgRectF.bottom - mArrowHeight;
        } else if (isExist(mArrowGravity, VerticalGravity.TO_BOTTOM)) {
            bgRectF.bottom -= mArrowHeight;
            centerPointF.y = bgRectF.bottom;
        }

        if (isExist(mArrowGravity, HorizontalGravity.TO_START)) {
            bgRectF.left += mArrowHeight;
            centerPointF.x = bgRectF.left;
        } else if (isExist(mArrowGravity, HorizontalGravity.ALIGN_START)) {
            centerPointF.x = bgRectF.left + mArrowHeight;
        } else if (isExist(mArrowGravity, HorizontalGravity.CENTER)) {
            centerPointF.x = bgRectF.width() / 2;
        } else if (isExist(mArrowGravity, HorizontalGravity.ALIGN_END)) {
            centerPointF.x = bgRectF.right - mArrowHeight;
        } else if (isExist(mArrowGravity, HorizontalGravity.TO_END)) {
            bgRectF.right -= mArrowHeight;
            centerPointF.x = bgRectF.right;
        }

        if (centerPointF.y + mArrowOffsetY + mArrowHeight > bounds.bottom) {
            centerPointF.y = bounds.bottom - mArrowHeight;
        } else if (centerPointF.y + mArrowOffsetY - mArrowHeight < bounds.top) {
            centerPointF.y = bounds.top + mArrowHeight;
        } else {
            centerPointF.y += mArrowOffsetY;
        }

        if (centerPointF.x + mArrowOffsetX + mArrowHeight > bounds.right) {
            centerPointF.x = bounds.right - mArrowHeight;
        } else if (centerPointF.x + mArrowOffsetX - mArrowHeight < bounds.left) {
            centerPointF.x = bounds.left + mArrowHeight;
        } else {
            centerPointF.x += mArrowOffsetX;
        }

        if (mRadius > 0) {
            if (isExist(mArrowGravity, HorizontalGravity.ALIGN_START)) {
                centerPointF.x = Math.max(centerPointF.x, mRadius + mArrowHeight);
            }
            if (isExist(mArrowGravity, HorizontalGravity.ALIGN_END)) {
                centerPointF.x = Math.min(centerPointF.x, bounds.right - mRadius - mArrowHeight);
            }

            if (isExist(mArrowGravity, VerticalGravity.ALIGN_TOP)) {
                centerPointF.y = Math.max(centerPointF.y, mRadius + mArrowHeight);
            }
            if (isExist(mArrowGravity, VerticalGravity.ALIGN_BOTTOM)) {
                centerPointF.y = Math.min(centerPointF.y, bounds.bottom - mRadius - mArrowHeight);
            }
        }

        Path arrowPath = new Path();
        arrowPath.moveTo(centerPointF.x - mArrowHeight, centerPointF.y);
        arrowPath.lineTo(centerPointF.x, centerPointF.y - mArrowHeight);
        arrowPath.lineTo(centerPointF.x + mArrowHeight, centerPointF.y);
        arrowPath.lineTo(centerPointF.x, centerPointF.y + mArrowHeight);
        arrowPath.close();

        mPath.addRoundRect(bgRectF, mRadius, mRadius, Path.Direction.CW);
        mPath.addPath(arrowPath);

        invalidateSelf();
    }

    private boolean isExist(@ArrowGravity int arrowGravity, int directionGravity) {
        return (arrowGravity & directionGravity) == directionGravity;
    }

    private static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }
}
