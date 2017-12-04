package com.xiaojinzii.skuproject.bean.skuInterfaceImpl;

import com.xiaojinzii.skuproject.bean.skuInterface.SkuProperty;

/**
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public class SkuPropertyImpl implements SkuProperty {

    private String propertyId;
    private String propertyName;

    public SkuPropertyImpl() {
    }

    public SkuPropertyImpl(String propertyId, String propertyName) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
    }

    @Override
    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
