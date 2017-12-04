package com.xiaojinzii.skuproject.view;

import android.support.annotation.NonNull;

import com.xiaojinzii.skuproject.bean.skuInterface.SkuPropertityValue;
import com.xiaojinzii.skuproject.bean.skuInterfaceImpl.SkuPropertyValueImpl;

/**
 * this object means a property,For example,
 * it's name is yellow,it also has a unique id
 *
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public class SkuPropertityValueImplForView extends SkuPropertyValueImpl {

    private boolean isSelect;

    // default is true
    private boolean isEble = true;

    public SkuPropertityValueImplForView(@NonNull SkuPropertityValue skuPropertyValue) {
        this.propertyValueId = skuPropertyValue.getPropertyValueId();
        this.propertyValueName = skuPropertyValue.getPropertyValueName();
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isEble() {
        return isEble;
    }

    public void setEble(boolean eble) {
        isEble = eble;
    }

}
