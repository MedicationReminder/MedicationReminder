package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.NatDrugstoreAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.response.NationalDrugstoreResponseBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_national_drugstore_web)
public class NationalDrugstoreWebActivity extends BaseActivity {
    @ViewInject(R.id.nationalDrugstoreListView)
    private ListView nationalDrugstoreListView;
    private NatDrugstoreAdapter natDrugstoreAdapter;
    private NationalDrugstoreResponseBean nationalDrugstoreResponseBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.nationalDrugstore);
        RequestParams requestParams=new RequestParams(AppConstants.BASE_ACTION+AppConstants.HOSPTIALS);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                nationalDrugstoreResponseBean=new Gson().fromJson(result,NationalDrugstoreResponseBean.class);
                natDrugstoreAdapter=new NatDrugstoreAdapter(NationalDrugstoreWebActivity.this,nationalDrugstoreResponseBean);
                nationalDrugstoreListView.setAdapter(natDrugstoreAdapter);
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
    }
}