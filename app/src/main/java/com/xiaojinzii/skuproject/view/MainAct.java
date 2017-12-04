package com.xiaojinzii.skuproject.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaojinzii.skuproject.R;
import com.xiaojinzii.skuproject.presenter.SkuPresenter;
import com.xiaojinzii.skuproject.bean.skuInterface.SkuProperty;

import java.util.ArrayList;
import java.util.List;

public class MainAct extends AppCompatActivity implements SkuView {

    private SkuPresenter presenter = new SkuPresenter(this);

    LinearLayout ll_content;

    TextView tv_sku_count;

    private List<SkuFlowView> flowViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        ll_content = findViewById(R.id.ll_content);
        tv_sku_count = findViewById(R.id.tv_sku_count);


    }

    public void clickView(View view) {
        presenter.onPrepare();
    }

    @Override
    public void initShow(@NonNull SkuProperty[] skuProperties, @NonNull SkuPropertityValueImplForView[][] skuPropertityValues) {
        ll_content.removeAllViews();
        flowViewList.clear();
        for (int i = 0; i < skuProperties.length; i++) {
            // add a property will add a array of propertyValues
            SkuProperty skuProperty = skuProperties[i];
            TextView tv_property = new TextView(this);
            tv_property.setText(skuProperty.getPropertyName());

            tv_property.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            tv_property.setPadding(0, 20, 0, 20);
            ll_content.addView(tv_property);

            SkuFlowView skuFlowView = new SkuFlowView(this);
            skuFlowView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            skuFlowView.show(skuPropertityValues[i]);
            ll_content.addView(skuFlowView);
            flowViewList.add(skuFlowView);

            skuFlowView.setOnItemClickListener(new SkuFlowView.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int index, int rowIndex) {
                    presenter.onPropertyClick(rowIndex, index);
                }
            }, i);

        }
    }

    @Override
    public void showSkuCount(int length) {
        tv_sku_count.setText("一共有" + length + "个SKU");
    }

    @Override
    public void freshSku(SkuPropertityValueImplForView[][] skuPropertityValues) {
        for (int i = 0; i < flowViewList.size(); i++) {
            flowViewList.get(i).show(skuPropertityValues[i]);
            flowViewList.get(i).setOnItemClickListener(new SkuFlowView.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int index, int rowIndex) {
                    presenter.onPropertyClick(rowIndex, index);
                }
            }, i);
        }
    }


}
