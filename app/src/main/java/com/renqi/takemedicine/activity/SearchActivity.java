package com.renqi.takemedicine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity {
    @ViewInject(R.id.KeyWord1)
    private TextView KeyWord1;
    @ViewInject(R.id.KeyWord2)
    private TextView KeyWord2;
    @ViewInject(R.id.KeyWord3)
    private TextView KeyWord3;
    @ViewInject(R.id.KeyWord4)
    private TextView KeyWord4;
    @ViewInject(R.id.KeyWord5)
    private TextView KeyWord5;
    @ViewInject(R.id.KeyWord6)
    private TextView KeyWord6;
    @ViewInject(R.id.KeyWord7)
    private TextView KeyWord7;
    @ViewInject(R.id.KeyWord8)
    private TextView KeyWord8;
    List<TextView> textviweList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textviweList.add(KeyWord1);  textviweList.add(KeyWord2);  textviweList.add(KeyWord3);  textviweList.add(KeyWord4);
        textviweList.add(KeyWord5);  textviweList.add(KeyWord6);  textviweList.add(KeyWord7);  textviweList.add(KeyWord8);
        RequestParams params=new RequestParams("http://101.69.181.251/api/v1/app_drugreminds/search_words");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray list=new JSONArray(result);
                    for (int i = 0; i <list.length() ; i++) {
                        if(i==8)
                            return;
                        textviweList.get(i).setText(list.get(i)+"");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
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
    }
    @Event(R.id.home)
    private void home(View view){
        finish();
    }
}
