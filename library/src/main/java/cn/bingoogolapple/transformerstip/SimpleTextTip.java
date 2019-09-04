package cn.bingoogolapple.transformerstip;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.StringRes;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:19/9/1 13:29
 * 描述:适用于只有文字的浮窗，不写布局文件，在 Java 代码中设置文本内容属性
 */
public class SimpleTextTip extends TransformersTip {
    private TextView mContentTv;

    /**
     * @param anchorView 锚点控件
     */
    public SimpleTextTip(View anchorView) {
        super(anchorView, new TextView(anchorView.getContext()));
        mContentTv = (TextView) getContentView();
        setTextColor(Color.WHITE);
        setTextSizeSp(14);
        setTextPaddingDp(12);
    }

    /**
     * 设置文字内容
     */
    public SimpleTextTip setTextContent(CharSequence text) {
        mContentTv.setText(text);
        return this;
    }

    /**
     * 设置文字内容
     */
    public SimpleTextTip setTextContentRes(@StringRes int resId) {
        mContentTv.setText(resId);
        return this;
    }

    /**
     * 设置文字颜色
     */
    public SimpleTextTip setTextColor(@ColorInt int color) {
        mContentTv.setTextColor(color);
        return this;
    }

    /**
     * 设置文字颜色
     */
    public SimpleTextTip setTextColorRes(@ColorRes int resId) {
        mContentTv.setTextColor(mContentTv.getResources().getColor(resId));
        return this;
    }

    /**
     * 设置文字大小
     */
    public SimpleTextTip setTextSizeSp(int textSize) {
        mContentTv.setTextSize(textSize);
        return this;
    }

    /**
     * 设置文字大小
     */
    public SimpleTextTip setTextSizeRes(@DimenRes int resId) {
        mContentTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContentView().getResources().getDimensionPixelOffset(resId));
        return this;
    }

    /**
     * 设置文字对其方式
     *
     * @see android.view.Gravity
     */
    public SimpleTextTip setTextGravity(int gravity) {
        mContentTv.setGravity(gravity);
        return this;
    }

    /**
     * 设置文字行间距的倍数
     */
    public SimpleTextTip setLineSpacingMultiplier(float mult) {
        mContentTv.setLineSpacing(mContentTv.getLineSpacingExtra(), mult);
        return this;
    }

    /**
     * 设置文字行间距数值
     */
    public SimpleTextTip setLineSpacingExtraDp(int lineSpacingExtr) {
        mContentTv.setLineSpacing(ArrowDrawable.dp2px(getContentView().getContext(), lineSpacingExtr), mContentTv.getLineSpacingMultiplier());
        return this;
    }

    /**
     * 设置文字行间距数值
     */
    public SimpleTextTip setLineSpacingExtraRes(@DimenRes int resId) {
        mContentTv.setLineSpacing(getContentView().getResources().getDimensionPixelOffset(resId), mContentTv.getLineSpacingMultiplier());
        return this;
    }

    /**
     * 设置文字与浮窗边框的间距
     */
    public SimpleTextTip setTextPaddingDp(int padding) {
        return setTextPaddingPx(ArrowDrawable.dp2px(getContentView().getContext(), padding));
    }

    /**
     * 设置文字与浮窗边框的间距
     */
    public SimpleTextTip setTextPaddingRes(@DimenRes int resId) {
        return setTextPaddingPx(getContentView().getResources().getDimensionPixelOffset(resId));
    }

    private SimpleTextTip setTextPaddingPx(int padding) {
        mContentTv.setPaddingRelative(padding, padding, padding, padding);
        return this;
    }
}
