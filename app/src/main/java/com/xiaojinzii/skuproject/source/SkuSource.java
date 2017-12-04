package com.xiaojinzii.skuproject.source;

import com.xiaojinzii.skuproject.bean.skuInterface.Sku;
import com.xiaojinzii.skuproject.bean.skuInterface.SkuPropertityValue;
import com.xiaojinzii.skuproject.bean.skuInterface.SkuProperty;
import com.xiaojinzii.skuproject.webData.WebDataGenerater;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public class SkuSource {

    private WebDataGenerater generater = new WebDataGenerater();

    private SkuProperty[] mSkuProperties;

    private Sku[] skus;

    // 供给查询
    private Set<String> queryDb = new HashSet<>();

    public SkuSource() {

        mSkuProperties = generater.getSkuProperties();
        skus = generater.getSkus();

        createQueryDb();

    }

    public Sku[] getSkus() {
        return generater.getSkus();
    }

    public SkuProperty[] getSkuProperties() {
        return generater.getSkuProperties();
    }

    public SkuPropertityValue[][] getAllSkuPropertyValuesBySku() {


        SkuPropertityValue[][] skuPropertityValues = new SkuPropertityValue[mSkuProperties.length][];
        Set[] tmpSets = new HashSet[mSkuProperties.length];

        for (int i = 0; i < skus.length; i++) {
            Sku skus = this.skus[i];
            SkuPropertityValue[] skuPropertities = skus.getSkuPropertities();
            for (int j = 0; j < skuPropertities.length; j++) {
                SkuPropertityValue skuPropertityValue = skuPropertities[j];
                Set set = tmpSets[j];
                if (set == null) {
                    set = new HashSet();
                    tmpSets[j] = set;
                }
                set.add(skuPropertityValue);
            }
        }

        for (int i = 0; i < mSkuProperties.length; i++) {
            SkuPropertityValue[] objects = (SkuPropertityValue[]) tmpSets[i].toArray(new SkuPropertityValue[tmpSets[i].size()]);
            skuPropertityValues[i] = objects;
        }

        return skuPropertityValues;

    }

    private void createQueryDb() {

        int maybeCount = (int) Math.pow(2, mSkuProperties.length) - 1;

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < skus.length; i++) {
            // get a Sku
            Sku sku = this.skus[i];
            // get the SkuPropertityValues which length is equal property's length
            SkuPropertityValue[] skuPropertities = sku.getSkuPropertities();

            for (int j = 1; j <= maybeCount; j++) {
                // 1,10,11,100,101,110,111
                String binaryString = Integer.toBinaryString(j);
                // maybe 3 - 1 = 2
                int offset = mSkuProperties.length - binaryString.length();
                sb.delete(0, sb.length());
                for (int k = 0 + offset; k < mSkuProperties.length; k++) {
                    if (binaryString.charAt(k - offset) == '1') {
                        if (sb.length() > 0) sb.append("&");
                        sb.append(mSkuProperties[k].getPropertyId());
                        sb.append(":");
                        sb.append(skuPropertities[k].getPropertyValueId());
                    }
                }
                if (sb.length() > 0) {
                    queryDb.add(sb.toString());
                }
            }

            // abc
            // 100 -> a...
            // 010 -> b...
            // 001 -> c...
            // 110 -> ab...
            // 101 -> ac...
            // 011 -> bc...
            // 111 -> abc

        }

    }

    public boolean isExist(String string) {
        return queryDb.contains(string);
    }

}
