package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.KitDetialsAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.response.KitDetialsResponseBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

@ContentView(R.layout.activity_kit_details)
public class KitDetailsActivity extends BaseActivity {

    @ViewInject(R.id.EarlyMiddleLate)
    private TextView EarlyMiddleLate;

    @ViewInject(R.id.time)
    private TextView time;

    @ViewInject(R.id.iv_time)
    private ImageView ivTime;


    @ViewInject(R.id.lv_kit_detials)
    private ListView lvKitDetials;
    private KitDetialsResponseBean kitDetialsResponseBean;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.kitDetials);
        setIption(AppConstants.ToolBarTitle.kitDetialsRemind);

        RequestParams params = new RequestParams(AppConstants.BASE_ACTION + AppConstants.app_drugreminds);
        params.addBodyParameter("token", "426426426");
        Log.e("params",params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result",result);
                kitDetialsResponseBean = new Gson().fromJson(result, KitDetialsResponseBean.class);
                KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, flag);
                lvKitDetials.setAdapter(kitDetialsAdapter);
                if (kitDetialsResponseBean.getApp_drugreminds().size() == 0) {
                    ToastUtils.showLongToast("今日暂无用药提醒！");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        SimpleDateFormat sm = new SimpleDateFormat("HH:mm");
        time.setText(sm.format(new Date()));
        int hour = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));

        if (6 <= hour && hour <= 9) {
            EarlyMiddleLate.setText("早上");
            ivTime.setImageResource(R.mipmap.time_icon_morning);
        } else if (9 < hour && hour <= 16) {
            EarlyMiddleLate.setText("中午");
            ivTime.setImageResource(R.mipmap.time_icon_noon);
        } else if (16 < hour && hour <= 20) {
            EarlyMiddleLate.setText("傍晚");
            ivTime.setImageResource(R.mipmap.time_icon_evening);
        } else {
            EarlyMiddleLate.setText("夜晚");
            ivTime.setImageResource(R.mipmap.time_icon_night);
        }

    }


    @Event(R.id.iption)
    private void iption(View view) {
        if (flag) {
            flag = false;
            setToolBarTitle(AppConstants.ToolBarTitle.kitDetials2);
            KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, flag);
            lvKitDetials.setAdapter(kitDetialsAdapter);
            if (kitDetialsResponseBean.getApp_register_reminds().size() == 0) {
                ToastUtils.showLongToast("今日暂无挂号提醒！");

            }
        } else {
            flag = true;
            setToolBarTitle(AppConstants.ToolBarTitle.kitDetials);
            KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, flag);
            lvKitDetials.setAdapter(kitDetialsAdapter);
            if (kitDetialsResponseBean.getApp_drugreminds().size() == 0) {
                ToastUtils.showLongToast("今日暂无用药提醒！");
            }
        }

    }
}
