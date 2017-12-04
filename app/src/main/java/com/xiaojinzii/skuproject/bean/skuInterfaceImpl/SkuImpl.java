package com.xiaojinzii.skuproject.bean.skuInterfaceImpl;

import com.xiaojinzii.skuproject.bean.skuInterface.Sku;
import com.xiaojinzii.skuproject.bean.skuInterface.SkuPropertityValue;

import java.util.Arrays;

/**
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public class SkuImpl implements Sku {

    private SkuPropertityValue[] skuPropertities;

    public SkuImpl() {
    }

    public SkuImpl(SkuPropertityValue[] skuPropertities) {
        this.skuPropertities = skuPropertities;
    }

    @Override
    public SkuPropertityValue[] getSkuPropertities() {
        return skuPropertities;
    }

    public void setSkuPropertities(SkuPropertityValue[] skuPropertities) {
        this.skuPropertities = skuPropertities;
    }

    @Override
    public String toString() {
        return "SkuImpl{" +
                "SkuPropertities=" + Arrays.toString(skuPropertities) +
                '}';
    }

    /**
     * 实现比较两个SKU是否是一样的,比较的是内部各个属性
     * 1.属性的长度
     * 2.属性的所有id是否都相同
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SkuImpl == false) {
            return false;
        }
        SkuImpl target = (SkuImpl) obj;
        if (skuPropertities.length != target.skuPropertities.length)
            return false;

        for (int i = 0; i < skuPropertities.length; i++) {
            if (!skuPropertities[i].getPropertyValueId().equals(target.skuPropertities[i].getPropertyValueId())) {
                return false;
            }
        }

        return true;

    }
}
