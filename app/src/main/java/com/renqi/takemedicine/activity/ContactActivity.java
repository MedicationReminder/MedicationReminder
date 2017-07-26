package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.ContactAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.response.ContactResponseBean;
import com.renqi.takemedicine.swipemenulistview.SwipeMenu;
import com.renqi.takemedicine.swipemenulistview.SwipeMenuCreator;
import com.renqi.takemedicine.swipemenulistview.SwipeMenuItem;
import com.renqi.takemedicine.swipemenulistview.SwipeMenuListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_contact)
public class ContactActivity extends BaseActivity {

    @ViewInject(R.id.lv_contact)
    private SwipeMenuListView lvContact;
    private ContactAdapter contactAdapter;
    private ContactResponseBean contactResponseBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarTitle(AppConstants.ToolBarTitle.Contacts);
        initMenu();
        RequestParams params=new RequestParams(AppConstants.BASE_ACTION+AppConstants.ALL_CONTACTS);
        params.addBodyParameter("token","426426426");
        Log.e("params",params.toString());

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("ALL_CONTACTS","onSuccess");

                contactResponseBean=new Gson().fromJson(result.toString(),ContactResponseBean.class);
                contactAdapter = new ContactAdapter(ContactActivity.this,contactResponseBean);
                lvContact.setAdapter(contactAdapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("ALL_CONTACTS",ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    /**
     * 侧滑删除历史消息
     */
    private void initMenu() {
        // TODO Auto-generated method stub
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                // openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,0xCE)));
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                //标题
                deleteItem.setTitle("删除");
                // 标题大小
                deleteItem.setTitleSize(18);
                // 标题的颜色
                deleteItem.setTitleColor(Color.WHITE);
                // set a icon
                // deleteItem.setIcon(R.mipmap.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        lvContact.setMenuCreator(creator);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("1",contactResponseBean.getApp_contact().get(position).getName());

                intent.putExtra("2", contactResponseBean.getApp_contact().get(position).getId());
                setResult(1,intent);
                finish();
            }
        });

        lvContact.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                // ApplicationInfo item = mAppList.get(position);
                switch (index) {
                    case 0:

                        RequestParams params=new RequestParams(AppConstants.BASE_ACTION+AppConstants.DELETE_CONTACTS);
                        params.addBodyParameter("token","426426426");
                        params.addBodyParameter("id",contactResponseBean.getApp_contact().get(position).getId());
                        Log.e("params",params.toString());

                        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Log.e("DELETE_CONTACTS","onSuccess");

                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                Log.e("DELETE_CONTACTS",ex.toString());
                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });


                        contactResponseBean.getApp_contact().remove(position);
                        contactAdapter = new ContactAdapter(ContactActivity.this,contactResponseBean);
                        lvContact.setAdapter(contactAdapter);

                    case 1:

                        break;
                }
                return false;
            }
        });
    }
@Event(R.id.home)
private void home(View view){
    Intent intent=new Intent();
    intent.putExtra("1","");
    setResult(1,intent);
    finish();
}
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
