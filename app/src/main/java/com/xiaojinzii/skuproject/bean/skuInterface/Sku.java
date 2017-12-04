package com.xiaojinzii.skuproject.bean.skuInterface;

/**
 * this object descript a specific good,such as:
 * color: yellow
 * size: XXXL
 * style: for man
 *
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public interface Sku {

    /**
     * return a array of SkuPropertities
     *
     * @return return a array of SkuPropertities
     */
    SkuPropertityValue[] getSkuPropertities();

}
