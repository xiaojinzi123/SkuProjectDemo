package com.xiaojinzii.skuproject.bean.skuInterface;

/**
 * this object means a property,For example,
 * it's name is yellow,it also has a unique id
 *
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public interface SkuPropertityValue {

    /**
     * return a unique id of SkuPropertityValue
     *
     * @return return a unique id of SkuPropertityValue
     */
    String getPropertyValueId();

    /**
     * return a name of SkuPropertityValue,it's not unique
     *
     * @return return name id of SkuPropertityValue
     */
    String getPropertyValueName();

}
