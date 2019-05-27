package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.ContactAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.response.ContactResponseBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_contact)
public class ContactActivity extends BaseActivity {

    @ViewInject(R.id.lv_contact)
    private ListView lvContact;
    private ContactAdapter contactAdapter;
    private ContactResponseBean contactResponseBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarTitle(AppConstants.ToolBarTitle.Contacts);

        RequestParams params = new RequestParams(AppConstants.BASE_ACTION + AppConstants.ALL_CONTACTS);
        params.addBodyParameter("token", "426426426");
        Log.e("params", params.toString());

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("ALL_CONTACTS", "onSuccess");

                contactResponseBean = new Gson().fromJson(result.toString(), ContactResponseBean.class);
                contactAdapter = new ContactAdapter(ContactActivity.this, contactResponseBean);
                lvContact.setAdapter(contactAdapter);
                lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        intent.putExtra("1", contactResponseBean.getApp_contact().get(position).getName());

                        intent.putExtra("2", contactResponseBean.getApp_contact().get(position).getId());
                        setResult(1, intent);
                        finish();
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("ALL_CONTACTS", ex.toString());
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
    Intent intent=new Intent();
    intent.putExtra("1","");
    intent.putExtra("2","");
    setResult(1,intent);
    finish();
}
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
