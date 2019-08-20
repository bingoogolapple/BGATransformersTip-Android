package cn.bingoogolapple.transformerstip.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import cn.bingoogolapple.transformerstip.ArrowDrawable;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:19/8/19 21:34
 * 描述:继承自 LinearLayout 来设置 ArrowDrawable 作为背景，便于在布局文件中预览
 */
public class TransformersTipLinearLayout extends LinearLayout {

    public TransformersTipLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransformersTipLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackground(new ArrowDrawable(context, attrs));
    }
}
