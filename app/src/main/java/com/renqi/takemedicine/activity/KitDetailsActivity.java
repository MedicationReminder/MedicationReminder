package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.KitDetialsAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.response.KitDetialsResponseBean;
import com.renqi.takemedicine.http.HttpDeleteWithBody;
import com.renqi.takemedicine.swipemenulistview.SwipeMenu;
import com.renqi.takemedicine.swipemenulistview.SwipeMenuCreator;
import com.renqi.takemedicine.swipemenulistview.SwipeMenuItem;
import com.renqi.takemedicine.swipemenulistview.SwipeMenuListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
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
    private SwipeMenuListView lvKitDetials;
    private KitDetialsResponseBean kitDetialsResponseBean;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.kitDetials);
        setIption(AppConstants.ToolBarTitle.kitDetialsRemind);

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
        lvKitDetials.setMenuCreator(creator);


        RequestParams params = new RequestParams(AppConstants.BASE_ACTION + AppConstants.app_drugreminds);
        params.addBodyParameter("token", "426426426");
        Log.e("params", params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
                kitDetialsResponseBean = new Gson().fromJson(result, KitDetialsResponseBean.class);

                kitDetialsResponseBean.getApp_drugreminds().size();

                KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, true);
                lvKitDetials.setAdapter(kitDetialsAdapter);
                if (kitDetialsResponseBean.getApp_drugreminds().size() == 0) {
                    ToastUtils.showLongToast("今日暂无用药提醒！");
                }

                lvKitDetials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent =new Intent(KitDetailsActivity.this, MedicationReminderActivity2.class);
                        intent.putExtra("1",kitDetialsResponseBean.getApp_drugreminds().get(position).toString());
                        startActivityForResult(intent, 1);
                    }
                });
                lvKitDetials.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                        switch (index) {
                            case 0:
                                new myThread("app_drugreminds", kitDetialsResponseBean.getApp_drugreminds().get(position).getId()).start();
                                kitDetialsResponseBean.getApp_drugreminds().remove(position);
                                KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, true);
                                lvKitDetials.setAdapter(kitDetialsAdapter);

                            case 1:

                                break;
                        }
                        return false;
                    }
                });
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
            lvKitDetials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent intent =new Intent(KitDetailsActivity.this, RegisteredReminderActivity2.class);
                    intent.putExtra("2",kitDetialsResponseBean.getApp_register_reminds().get(position).toString());
                    startActivityForResult(intent, 2);
                }
            });
            lvKitDetials.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                    switch (index) {
                        case 0:
                            new myThread("app_registerreminds", kitDetialsResponseBean.getApp_register_reminds().get(position).getId()).start();
                            kitDetialsResponseBean.getApp_register_reminds().remove(position);
                            KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, false);
                            lvKitDetials.setAdapter(kitDetialsAdapter);

                        case 1:

                            break;
                    }
                    return false;
                }
            });

        } else {
            flag = true;
            setToolBarTitle(AppConstants.ToolBarTitle.kitDetials);
            KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, flag);
            lvKitDetials.setAdapter(kitDetialsAdapter);
            if (kitDetialsResponseBean.getApp_drugreminds().size() == 0) {
                ToastUtils.showLongToast("今日暂无用药提醒！");
            }
            lvKitDetials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent =new Intent(KitDetailsActivity.this, MedicationReminderActivity2.class);
                    intent.putExtra("1",kitDetialsResponseBean.getApp_drugreminds().get(position).toString());
                    startActivityForResult(intent, 1);
                }
            });
            lvKitDetials.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                    switch (index) {
                        case 0:
                            new myThread("app_drugreminds", kitDetialsResponseBean.getApp_drugreminds().get(position).getId()).start();
                            kitDetialsResponseBean.getApp_drugreminds().remove(position);
                            KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, true);
                            lvKitDetials.setAdapter(kitDetialsAdapter);

                        case 1:

                            break;
                    }
                    return false;
                }
            });

        }

    }

    class myThread extends Thread {
        private String id;
        private String type;

        public myThread(String type, String id) {
            this.id = id;
            this.type = type;
        }

        @Override
        public void run() {
            HttpClient httpClient = new DefaultHttpClient();
            // 创建HttpGet对象。
            HttpDeleteWithBody delete = new HttpDeleteWithBody("http://101.69.181.251/api/v1/" + type + "/" + id);

            // 发送GET请求
            HttpResponse httpResponse = null;
            try {
                httpResponse = httpClient.execute(delete);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 如果服务器成功地返回响应
            if (httpResponse.getStatusLine()
                    .getStatusCode() == 204) {
                // 获取服务器响应字符串
                Log.e("result1", "成功");

                JSONObject resultObj = null;
                try {
                    resultObj = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("result1", httpResponse.getStatusLine()
                        .getStatusCode() + "");

                Log.e("result1", resultObj.toString());
            }

        }

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                setToolBarTitle(AppConstants.ToolBarTitle.kitDetials);
                RequestParams params = new RequestParams(AppConstants.BASE_ACTION + AppConstants.app_drugreminds);
                params.addBodyParameter("token", "426426426");
                Log.e("params", params.toString());
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("result", result);
                        kitDetialsResponseBean = new Gson().fromJson(result, KitDetialsResponseBean.class);

                        kitDetialsResponseBean.getApp_drugreminds().size();

                        KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, true);
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

                break;

            case 2:
                setToolBarTitle(AppConstants.ToolBarTitle.kitDetials2);
                RequestParams params2 = new RequestParams(AppConstants.BASE_ACTION + AppConstants.app_drugreminds);
                params2.addBodyParameter("token", "426426426");
                Log.e("params", params2.toString());
                x.http().get(params2, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("result", result);
                        kitDetialsResponseBean = new Gson().fromJson(result, KitDetialsResponseBean.class);

                        kitDetialsResponseBean.getApp_register_reminds().size();

                        KitDetialsAdapter kitDetialsAdapter = new KitDetialsAdapter(getApplicationContext(), kitDetialsResponseBean, false);
                        lvKitDetials.setAdapter(kitDetialsAdapter);
                        if (kitDetialsResponseBean.getApp_register_reminds().size() == 0) {
                            ToastUtils.showLongToast("今日暂无挂号提醒！");
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
                break;
        }


    }
}
