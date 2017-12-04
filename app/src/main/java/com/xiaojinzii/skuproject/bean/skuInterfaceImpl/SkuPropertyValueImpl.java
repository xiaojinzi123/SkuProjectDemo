package com.xiaojinzii.skuproject.bean.skuInterfaceImpl;

import com.xiaojinzii.skuproject.bean.skuInterface.SkuPropertityValue;

/**
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public class SkuPropertyValueImpl implements SkuPropertityValue {

    protected String propertyValueId;
    protected String propertyValueName;

    public SkuPropertyValueImpl() {
    }

    public SkuPropertyValueImpl(String propertyValueId, String propertyValueName) {
        this.propertyValueId = propertyValueId;
        this.propertyValueName = propertyValueName;
    }

    @Override
    public String getPropertyValueId() {
        return propertyValueId;
    }

    @Override
    public String getPropertyValueName() {
        return propertyValueName;
    }

    public void setPropertyValueId(String propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public void setPropertyValueName(String propertyValueName) {
        this.propertyValueName = propertyValueName;
    }

    @Override
    public int hashCode() {
        return propertyValueId.hashCode();
    }

    @Override
    public String toString() {
        return "SkuPropertyValueImpl{" +
                "propertyValueId='" + propertyValueId + '\'' +
                ", propertyValueName='" + propertyValueName + '\'' +
                '}';
    }
}
