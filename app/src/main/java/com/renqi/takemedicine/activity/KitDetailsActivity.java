package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.KitDetialsBean;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_kit_details)
public class KitDetailsActivity extends BaseActivity {

    @ViewInject(R.id.EarlyMiddleLate)
    private TextView EarlyMiddleLate;

    @ViewInject(R.id.time)
    private TextView time;

    @ViewInject(R.id.iv_time)
    private ImageView ivTime;
    @ViewInject(R.id.lv_kit_detials)
    private ImageView lvKitDetials;
    private List<KitDetialsBean> kitDetialsBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.kitDetials);
    }
}
