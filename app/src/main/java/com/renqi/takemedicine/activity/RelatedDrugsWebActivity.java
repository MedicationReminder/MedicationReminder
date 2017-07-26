package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.RelDrugsAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.response.RelatedDrugsWebResponseBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_related_drugs_web)
public class RelatedDrugsWebActivity extends BaseActivity {
    @ViewInject(R.id.relatedDrugsListView)
    private ListView relatedDrugsListView;
private RelDrugsAdapter relDrugsAdapter;
    private RelatedDrugsWebResponseBean relatedDrugsWebResponseBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.relatedDrugs);
        RequestParams requestParams=new RequestParams(AppConstants.BASE_ACTION+AppConstants.SEARCHDRUGSNAME);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                relatedDrugsWebResponseBean=new Gson().fromJson(result,RelatedDrugsWebResponseBean.class);
                relDrugsAdapter=new RelDrugsAdapter(RelatedDrugsWebActivity.this,relatedDrugsWebResponseBean);
                relatedDrugsListView.setAdapter(relDrugsAdapter);
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