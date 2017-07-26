package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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

        RequestParams requestParams=new RequestParams(AppConstants.BASE_ACTION+AppConstants.SEARCHDRUGSNAME);

        Bundle bundle = this.getIntent().getExtras();

        if(bundle==null){
            setToolBarTitle(AppConstants.ToolBarTitle.relatedDrugs);
        }else {
            setToolBarTitle(AppConstants.ToolBarTitle.relatedDrugs1);
            requestParams.addBodyParameter("name",bundle.getString("flag"));
            requestParams.addBodyParameter("page","1");
            requestParams.addBodyParameter("per","1000");

        }

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
    public Handler MyHendler =new  Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    relDrugsAdapter.notifyDataSetChanged();
                    Log.e("position","加载图片完成");
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };
}