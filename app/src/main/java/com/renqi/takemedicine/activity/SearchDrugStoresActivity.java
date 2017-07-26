package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.SearchDrugStoresAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.response.SearchDrugStoresResponseBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_search_drug_stores)
public class SearchDrugStoresActivity extends BaseActivity {
    @ViewInject(R.id.nationalDrugstoreListView)
    private ListView nationalDrugstoreListView;
private SearchDrugStoresResponseBean searchDrugStoresResponseBean;
    private SearchDrugStoresAdapter searchDrugStoresAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.nationalDrugstore1);

        RequestParams requestParams=new RequestParams("http://www.yiliaode.com/api/v1/app_drugreminds/seach_drug_stores");
        requestParams.addBodyParameter("name",this.getIntent().getExtras().getString("flag"));
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                searchDrugStoresResponseBean=new Gson().fromJson(result,SearchDrugStoresResponseBean.class);
                searchDrugStoresAdapter=new SearchDrugStoresAdapter(SearchDrugStoresActivity.this,searchDrugStoresResponseBean);
                nationalDrugstoreListView.setAdapter(searchDrugStoresAdapter);
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
    public Handler MyHendler =new  Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    searchDrugStoresAdapter.notifyDataSetChanged();
                    Log.e("position","加载图片完成");
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };
}