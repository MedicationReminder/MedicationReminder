package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.base.EventbusActivity;
import com.renqi.takemedicine.bean.Remarks;
import com.renqi.takemedicine.bean.response.App_drugbestsResponseBean;
import com.renqi.takemedicine.bean.response.KitDetialsResponseBean;
import com.renqi.takemedicine.event.BaseEvents;
import com.renqi.takemedicine.view.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xu Wei on 2017/7/17.
 */
@ContentView(R.layout.recyclerview_dialog)
public class RemakesRecyclerViewActivity extends EventbusActivity {
    @ViewInject(R.id.recyclerViewDialog)
    private RecyclerView recyclerViewDialog;
    private CommonAdapter commonAdapter;
  //  private List<Remarks> remarksList = new ArrayList<>(), remarksListResult = new ArrayList<>();
    private List<Remarks> remarksSpecialList=new ArrayList<>();
    private List<Remarks> remarkseFoodList=new ArrayList<>();
    private List<Remarks> remarksTimeList=new ArrayList<>();
    private List<Remarks> remarksWaterList=new ArrayList<>();
    private List<Remarks> remarksDrugbestsList=new ArrayList<>();
    String param;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        param=  getIntent().getStringExtra("type");
        if ( (TakeMedicinApplication.isFirstTiem == 0&&param.equals("time")) ||
           (TakeMedicinApplication.isFirstSpecial == 0&&param.equals("special")) ||
           (TakeMedicinApplication.isFirstWater == 0&&param.equals("water"))   ||
           ( TakeMedicinApplication.isFisterFood == 0&&param.equals("food"))
         )
        {

            final RequestParams params = new RequestParams(AppConstants.BASE_ACTION + AppConstants.app_drugreminds + AppConstants.APP_CHOICE_NAME);
            params.addBodyParameter("name", param);
            Log.e("params", params.toString());
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    // ToastUtils.showShortToast(result);
                    try {

                        JSONArray names = new JSONObject(result).getJSONArray("names");
                        if(param.equals("food")){
                        for (int i = 0; i < names.length(); i++) {
                            remarkseFoodList.add(new Remarks(
                                    ((JSONObject) names.get(i)).getString("id"),//params1
                                    ((JSONObject) names.get(i)).getString("name"),//params2
                                    false//params3
                            ));
                        }

                        TakeMedicinApplication.isFisterFood=1;
                            setMyAdapter(remarkseFoodList);
                        }
                      else  if(param.equals("water")){
                            for (int i = 0; i < names.length(); i++) {
                                remarksWaterList.add(new Remarks(
                                        ((JSONObject) names.get(i)).getString("id"),//params1
                                        ((JSONObject) names.get(i)).getString("name"),//params2
                                        false//params3
                                ));
                            }
                            TakeMedicinApplication.isFirstWater=1;
                            setMyAdapter(remarksWaterList);
                      }
                        else  if(param.equals("special")){
                            for (int i = 0; i < names.length(); i++) {
                                remarksSpecialList.add(new Remarks(
                                        ((JSONObject) names.get(i)).getString("id"),//params1
                                        ((JSONObject) names.get(i)).getString("name"),//params2
                                        false//params3
                                ));
                            }
                            TakeMedicinApplication.isFirstSpecial=1;
                            setMyAdapter(remarksSpecialList);
                        }
                        else  if(param.equals("time")){
                            for (int i = 0; i < names.length(); i++) {
                                remarksTimeList.add(new Remarks(
                                        ((JSONObject) names.get(i)).getString("id"),//params1
                                        ((JSONObject) names.get(i)).getString("name"),//params2
                                        false//params3
                                ));
                            }
                            TakeMedicinApplication.isFirstTiem=1;
                            setMyAdapter(remarksTimeList);
                        }


                    } catch (JSONException e) {
                        ToastUtils.showShortToast("json数据解析异常了");
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
        else if((TakeMedicinApplication.isFirstApp_drugbests==0&&param.equals("drugbests")))
        {
            final RequestParams params = new RequestParams(AppConstants.BASE_ACTION +
                    AppConstants.app_drugreminds + AppConstants.APP_DRUGBEST);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                //    App_drugbestsResponseBean drugbestsResponseBean=new Gson().fromJson(result, App_drugbestsResponseBean.class);
                    try {
                        JSONArray app_drugbests= new JSONObject(result).getJSONArray("app_drugbests");
                        for (int i = 0; i <app_drugbests.length() ; i++) {
                            remarksDrugbestsList.add(new Remarks(
                                    ((JSONObject)app_drugbests.get(i)).getString("id"),
                                    ((JSONObject)app_drugbests.get(i)).getString("name")+"\t"+((JSONObject)app_drugbests.get(i)).getString("time_code"),
                                    false
                                    ));
                        }
                        TakeMedicinApplication.isFirstApp_drugbests=1;
                        setMyAdapter(remarksDrugbestsList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    ToastUtils.showShortToast("json数据解析异常了");
                    ex.printStackTrace();
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

    private void setMyAdapter(final List<Remarks> remarksList) {
        if(param.equals("time"))
            setToolBarTitle("服药时间提醒"); if(param.equals("food"))
            setToolBarTitle("服药饮食提醒"); if(param.equals("water"))
            setToolBarTitle("服药用水提醒"); if(param.equals("special"))
            setToolBarTitle("特殊服药提醒");if(param.equals("drugbests"))
                setToolBarTitle("最佳用药时间提醒");
        recyclerViewDialog.setLayoutManager(new LinearLayoutManager(this));
        commonAdapter = new CommonAdapter<Remarks>(RemakesRecyclerViewActivity.this,
                R.layout.recyclerview_dialog_item, remarksList) {
            @Override
            public void convert(ViewHolder holder, final Remarks remarks, final int position) {
                if(param.equals("drugbests"))
                {
                    LinearLayout contentView=holder.getView(R.id.contentView);
                    if(position==0) contentView.setBackgroundColor(Color.parseColor("#f7c959"));
                    if(position==1) contentView.setBackgroundColor(Color.parseColor("#eeb935"));
                    if(position==2) contentView.setBackgroundColor(Color.parseColor("#e6a235"));
                    if(position==3) contentView.setBackgroundColor(Color.parseColor("#e28037"));
                    if(position==4) contentView.setBackgroundColor(Color.parseColor("#ce6536"));
                    if(position==5) contentView.setBackgroundColor(Color.parseColor("#f7c959"));
                    if(position==6) contentView.setBackgroundColor(Color.parseColor("#9187dd"));
                    if(position==7) contentView.setBackgroundColor(Color.parseColor("#f7c959"));
                    if(position==8) contentView.setBackgroundColor(Color.parseColor("#6f64c0"));
                    if(position==9) contentView.setBackgroundColor(Color.parseColor("#413d92"));
                    if(position==10) contentView.setBackgroundColor(Color.parseColor("#363378"));
                    if(position==11) contentView.setBackgroundColor(Color.parseColor("#312e65"));


                }
                final CheckBox checkBox = holder.getView(R.id.select_checkbox);
                       if(remarks.isSelect) checkBox.setChecked(true);else checkBox.setChecked(false);
                holder.setOnClickListener(R.id.contentView, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (remarks.isSelect) //如果是选中的取消选中
                        {
                            checkBox.setChecked(false);
                            remarksList.get(position).isSelect = false;

                        } else //如果是没选中的 点击是选中
                        {
                            checkBox.setChecked(true);
                            remarksList.get(position).isSelect = true;

                        }
                        //   ToastUtils.showShortToast(remarks.id+"");
                    }

                });
                holder.setText(R.id.text, remarks.data);

            }
        };
        recyclerViewDialog.setAdapter(commonAdapter);
        commonAdapter.notifyDataSetChanged();
    }

    @Event(R.id.query)
    private void query(View view) {

        if(param.equals("food"))
        {
        BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSFOODLIST;
        event.setObject(remarkseFoodList);
            EventBus.getDefault().postSticky(event);
        }
        if(param.equals("time")){
            BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSTIMELIST;
            event.setObject(remarksTimeList);
            EventBus.getDefault().postSticky(event);
        }
        if(param.equals("water")){
            BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSWATERLIST;
            event.setObject(remarksWaterList);
            EventBus.getDefault().postSticky(event);
           }
        if(param.equals("special")){
            BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSPEICALLIST;
            event.setObject(remarksSpecialList);
            EventBus.getDefault().postSticky(event);

        }
        if(param.equals("drugbests")){

            BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSDRUGBESTS;
            event.setObject(remarksDrugbestsList);
            EventBus.getDefault().postSticky(event);

        }

        finish();
    }//确认按钮事件
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvents.myEvent event) {
        // UI updates must run on MainThread
        if (event == BaseEvents.myEvent.SENDREMARKSDRUGBESTS) {

            ToastUtils.showShortToast(param);
             if(param.equals("drugbests")&&TakeMedicinApplication.isFirstApp_drugbests==1){
                remarksDrugbestsList = (List<Remarks>) event.getObject();

                setMyAdapter(remarksDrugbestsList);
                Log.e("remarksDrugbestsList",remarksDrugbestsList.get(0).data);
            }
        }
        if (event == BaseEvents.myEvent.SENDREMARKSFOODLIST) {

            ToastUtils.showShortToast(param);
            if(param.equals("food")&&TakeMedicinApplication.isFisterFood==1){
                remarkseFoodList = (List<Remarks>) event.getObject();

                setMyAdapter(remarkseFoodList);
                Log.e("remarkseFoodList",remarkseFoodList.get(0).data);
            }
        }
        if(event==BaseEvents.myEvent.SENDREMARKSWATERLIST)
        {
            if(param.equals("water")&&TakeMedicinApplication.isFirstWater==1){
                remarksWaterList = (List<Remarks>) event.getObject();
                setMyAdapter(remarksWaterList);
            }


        }
        if(event==BaseEvents.myEvent.SENDREMARKSTIMELIST) {
            if (param.equals("time") && TakeMedicinApplication.isFirstTiem == 1) {
                remarksTimeList = (List<Remarks>) event.getObject();
                setMyAdapter(remarksTimeList);
            }
        }
        if(event== BaseEvents.myEvent.SENDREMARKSPEICALLIST)
        {
            if (param.equals("special") && TakeMedicinApplication.isFirstSpecial == 1) {
                remarksSpecialList = (List<Remarks>) event.getObject();
                setMyAdapter(remarksSpecialList);
            }
        }
    }
    @Event(R.id.home)
    private void home(View view){
        if(param.equals("food"))
        {
            BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSFOODLIST;
            event.setObject(remarkseFoodList);
            EventBus.getDefault().postSticky(event);
        }
        if(param.equals("time")){
            BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSTIMELIST;
            event.setObject(remarksTimeList);
            EventBus.getDefault().postSticky(event);

        }
        if(param.equals("water")){
            BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSWATERLIST;
            event.setObject(remarksWaterList);
            EventBus.getDefault().postSticky(event);
        }
        if(param.equals("special")){
            BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSPEICALLIST;
            event.setObject(remarksSpecialList);
            EventBus.getDefault().postSticky(event);

        }
        if(param.equals("drugbests")){
            BaseEvents.CommonEvent event = BaseEvents.CommonEvent.SENDREMARKSDRUGBESTS;
            event.setObject(remarksDrugbestsList);
            EventBus.getDefault().postSticky(event);

        }

        finish();
    }
}