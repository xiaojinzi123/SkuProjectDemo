package com.xiaojinzii.skuproject.view;

import android.support.annotation.NonNull;

import com.xiaojinzii.skuproject.bean.skuInterface.SkuProperty;

/**
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public interface SkuView {

    /**
     * this method will be called once,and the length of parameter 'skuProperties' is equal to the langth of parameter of
     *
     * @param skuProperties       you can show the all properties
     * @param skuPropertityValues you can show all propertyValues
     */
    void initShow(@NonNull SkuProperty[] skuProperties, @NonNull SkuPropertityValueImplForView[][] skuPropertityValues);

    void showSkuCount(int length);

    void freshSku(SkuPropertityValueImplForView[][] skuPropertityValues);

}
