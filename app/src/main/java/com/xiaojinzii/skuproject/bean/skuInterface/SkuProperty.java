package com.xiaojinzii.skuproject.bean.skuInterface;

/**
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public interface SkuProperty {

    /**
     * return a unique id of SkuPropertity
     *
     * @return return a unique id of SkuPropertity
     */
    String getPropertyId();

    /**
     * return a name of SkuPropertity,it's not unique
     *
     * @return return name id of SkuPropertity
     */
    String getPropertyName();

}
