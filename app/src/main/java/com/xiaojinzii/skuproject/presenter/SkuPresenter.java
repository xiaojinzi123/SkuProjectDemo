package com.xiaojinzii.skuproject.presenter;

import com.xiaojinzii.skuproject.bean.skuInterface.SkuPropertityValue;
import com.xiaojinzii.skuproject.bean.skuInterface.SkuProperty;
import com.xiaojinzii.skuproject.source.SkuSource;
import com.xiaojinzii.skuproject.view.SkuPropertityValueImplForView;
import com.xiaojinzii.skuproject.view.SkuView;

/**
 * @author : xiaojinzi 30212
 * @desc :
 * @time : 2017/12/02
 * @blog : http://blog.csdn.net/u011692041
 */
public class SkuPresenter {

    private SkuView view;

    private SkuSource source = new SkuSource();

    private SkuPropertityValueImplForView[][] skuPropertityValues;

    public SkuPresenter(SkuView view) {
        this.view = view;
    }

    public void onPrepare() {

        // get all SkuPropertityValue,and do change SkuPropertityValue to SkuPropertityValueImplForView
        SkuPropertityValue[][] values = source.getAllSkuPropertyValuesBySku();
        skuPropertityValues = new SkuPropertityValueImplForView[values.length][];

        for (int i = 0; i < values.length; i++) {
            SkuPropertityValue[] value1 = values[i];
            skuPropertityValues[i] = new SkuPropertityValueImplForView[value1.length];
            SkuPropertityValueImplForView[] value2 = skuPropertityValues[i];
            for (int j = 0; j < value1.length; j++) {
                value2[j] = new SkuPropertityValueImplForView(value1[j]);
            }
        }

        // 构建界面所需数据
        view.initShow(source.getSkuProperties(), skuPropertityValues);

        view.showSkuCount(source.getSkus().length);

    }

    public void onPropertyClick(int rowIndex, int columIndex) {
        // 如果不能选中就取消
        if (!skuPropertityValues[rowIndex][columIndex].isEble()) {
            return;
        }
        // 点击了一个实现选中,如果是已经选中的实现取消选中,如果是选中一个然后选中了同一个属性的其他的,那么先取消原来的
        if (skuPropertityValues[rowIndex][columIndex].isSelect()) {
            skuPropertityValues[rowIndex][columIndex].setSelect(false);
        } else {
            for (int i = 0; i < skuPropertityValues[rowIndex].length; i++) {
                skuPropertityValues[rowIndex][i].setSelect(false);
            }
            skuPropertityValues[rowIndex][columIndex].setSelect(true);
        }

        // 找出每一种属性被选中的属性,null表示没有
        String propertyIds[] = new String[skuPropertityValues.length];
        for (int i = 0; i < skuPropertityValues.length; i++) {
            SkuPropertityValueImplForView[] skuPropertityValues = this.skuPropertityValues[i];
            for (int j = 0; j < skuPropertityValues.length; j++) {
                if (skuPropertityValues[j].isSelect()) {
                    propertyIds[i] = skuPropertityValues[j].getPropertyValueId();
                }
            }
        }

        SkuProperty[] skuProperties = source.getSkuProperties();
        StringBuffer sb = new StringBuffer();

        // 需要组建的属性数组
        String queryStrs[] = new String[skuPropertityValues.length];

        for (int i = 0; i < skuPropertityValues.length; i++) {
            // 拿到一个属性的所有属性值
            SkuPropertityValueImplForView[] skuPropertityValues = this.skuPropertityValues[i];
            for (int j = 0; j < skuPropertityValues.length; j++) {
                sb.delete(0, sb.length());
                // 清空
                for (int k = 0; k < queryStrs.length; k++) {
                    queryStrs[k] = null;
                }
                // 已经选中的属性先占位
                for (int k = 0; k < propertyIds.length; k++) {
                    queryStrs[k] = propertyIds[k];
                }
                // 然后让当前循环到的属性占位,会覆盖刚刚的已经选中的属性
                queryStrs[i] = skuPropertityValues[j].getPropertyValueId();
                // 拼接出查询的字符串
                for (int k = 0; k < queryStrs.length; k++) {
                    if (queryStrs[k] == null) continue;
                    if (sb.length() > 0) sb.append("&");
                    sb.append(skuProperties[k].getPropertyId());
                    sb.append(":");
                    sb.append(queryStrs[k]);
                }

                // 根据查询的结果是否禁用SkuPropertyValue
                skuPropertityValues[j].setEble(source.isExist(sb.toString()));

            }
        }

        view.freshSku(skuPropertityValues);

    }

}
