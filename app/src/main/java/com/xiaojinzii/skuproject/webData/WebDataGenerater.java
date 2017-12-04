package com.xiaojinzii.skuproject.webData;

import com.xiaojinzii.skuproject.bean.skuInterface.Sku;
import com.xiaojinzii.skuproject.bean.skuInterfaceImpl.SkuImpl;
import com.xiaojinzii.skuproject.bean.skuInterface.SkuPropertityValue;
import com.xiaojinzii.skuproject.bean.skuInterface.SkuProperty;
import com.xiaojinzii.skuproject.bean.skuInterfaceImpl.SkuPropertyImpl;
import com.xiaojinzii.skuproject.bean.skuInterfaceImpl.SkuPropertyValueImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/04
 * @blog : http://blog.csdn.net/u011692041
 */
public class WebDataGenerater {

    // 生成的所有Sku
    private Sku[] skus = null;

    // 所有的属性
    private SkuProperty mSkuProperties[] = null;

    public WebDataGenerater() {

        createSkus();

    }

    private void createSkus() {

        String allPropertyNames[] = {
                "服装",
                "款式",
                "类型",
                "颜色",
                "尺寸",
                "重量",
                "脸型",
                "发票",
        };

        // 每次使用了之后都要自增++
        int uniqueId = 1;
        // 表示有几种属性
        int rows = 6;
        rows = Math.min(allPropertyNames.length, rows);
        // 表示每种属性最多有几个,比如颜色这个属性可以有三个
        int maxColumn = 8;
        // 表示每种属性最少有几个,比如颜色这个属性可以有三个
        int minColumn = 1;
        // 控制sku生成的个数,下面随机生成个数
        int skuCount = 0;

        Random r = new Random();

        List<Integer> tmpList = new ArrayList<>();
        for (int i = 0; i < allPropertyNames.length; i++) {
            tmpList.add(i);
        }
        // 随机创建出几个属性
        mSkuProperties = new SkuProperty[rows];
        for (int i = 0; i < mSkuProperties.length; i++) {
            int index = r.nextInt(tmpList.size());
            mSkuProperties[i] = new SkuPropertyImpl(
                    String.valueOf(uniqueId),
                    allPropertyNames[tmpList.get(index)]
            );
            tmpList.remove(index);
            uniqueId++;
        }

        // 构建好了每一种属性的所有属性值了

        SkuPropertityValue[][] skuPropertityValuesArr = new SkuPropertityValue[rows][];

        for (int i = 0; i < skuPropertityValuesArr.length; i++) {
            // 随机生成某一种属性的几个值
            int count = r.nextInt(maxColumn - minColumn + 1) + minColumn;
            skuPropertityValuesArr[i] = new SkuPropertityValue[count];
            String propertyName = mSkuProperties[i].getPropertyName();
            for (int j = 0; j < count; j++) {
                skuPropertityValuesArr[i][j] = new SkuPropertyValueImpl(
                        String.valueOf(uniqueId), propertyName + (j + 1)
                );
                uniqueId++;
            }
        }

        // 最多的种Sku的可能为各个属性的个数相乘
        int allCount = 1;
        for (int i = 0; i < skuPropertityValuesArr.length; i++) {
            allCount *= skuPropertityValuesArr[i].length;
        }
        skuCount = 1 + r.nextInt(allCount);
        //skuCount = 6;
        if (skuCount > allCount) {
            skuCount = allCount;
        }

        // 测试打印输出
        for (int i = 0; i < skuPropertityValuesArr.length; i++) {
            for (int j = 0; j < skuPropertityValuesArr[i].length; j++) {
                System.out.print(skuPropertityValuesArr[i][j].getPropertyValueName() + "\t\t");
            }
            System.out.println();
        }


        List<Sku> skuList = new ArrayList<>();

        while (skuList.size() < skuCount) {
            // 生成每一个Sku的属性值,也就是从每一种属性中抽取一个
            SkuPropertityValue skuPropertityValues[] = new SkuPropertityValue[rows];
            for (int j = 0; j < skuPropertityValues.length; j++) {
                // 随机从一个属性中抽取一个
                int index = r.nextInt(skuPropertityValuesArr[j].length);
                skuPropertityValues[j] = skuPropertityValuesArr[j][index];
            }
            Sku sku = new SkuImpl(skuPropertityValues);
            if (!skuList.contains(sku)) {
                skuList.add(sku);
            }
        }

        // 打印测试
        System.out.println("---------------------------------------------");

        for (int i = 0; i < skuList.size(); i++) {
            System.out.println();
            Sku sku = skuList.get(i);
            SkuPropertityValue[] skuPropertityValues = sku.getSkuPropertities();
            for (int j = 0; j < skuPropertityValues.length; j++) {
                SkuPropertityValue skuPropertity = skuPropertityValues[j];
                System.out.print(skuPropertity.getPropertyValueName() + "\t\t");
            }
        }

        System.out.println();
        System.out.println("---------------------------------------------");

        skus = skuList.toArray(new Sku[skuList.size()]);

    }

    public Sku[] getSkus() {
        return skus;
    }

    public SkuProperty[] getSkuProperties() {
        return mSkuProperties;
    }

}
