package com.xiaojinzii.skuproject.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.move.widget.XFlowLayout;
import com.xiaojinzii.skuproject.R;

/**
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public class SkuFlowView extends XFlowLayout {

    public SkuFlowView(Context context) {
        this(context, null);
    }

    public SkuFlowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkuFlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void show(@NonNull SkuPropertityValueImplForView[] skuPropertityValues) {
        removeAllViews();

        for (int i = 0; i < skuPropertityValues.length; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(skuPropertityValues[i].getPropertyValueName());
            tv.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            if (skuPropertityValues[i].isEble()) {
                tv.setTextColor(Color.BLACK);
                if (skuPropertityValues[i].isSelect()) {
                    tv.setBackgroundResource(R.drawable.sku_property_value_item_selected);
                } else {
                    tv.setBackgroundResource(R.drawable.sku_property_value_item_normal);
                }
            }else {
                tv.setTextColor(Color.GRAY);
                tv.setBackgroundResource(R.drawable.sku_property_value_item_disable);
            }

            addView(tv);
        }

    }

    public void setOnItemClickListener(final OnItemClickListener l, final int rowIndex) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    l.onItemClick(view, indexOfChild(view), rowIndex);
                }
            });
        }
    }

    public void refresh() {

    }

    public interface OnItemClickListener {

        void onItemClick(View v, int index, int rowIndex);

    }


}
