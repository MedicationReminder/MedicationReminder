package com.renqi.takemedicine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.bean.TopicsData;
import com.renqi.takemedicine.event.BaseEvents;
import com.renqi.takemedicine.utils.MedicationHelper;
import com.renqi.takemedicine.view.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xu Wei on 2017/8/5.
 */

public class TabLayoutFragment extends Fragment {
    public static String TABLAYOUT_FRAGMENT = "tab_fragment";
    private TextView txt;
    private int type;
    private String httpParam;
    private CommonAdapter commonAdapter;
    RecyclerView onlineConusltingRecyclerView;
    private List<TopicsData> listTopicsData=new ArrayList<>();

    public static TabLayoutFragment newInstance(int type ,String httpParam) {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TABLAYOUT_FRAGMENT, type);
        bundle.putString("param",httpParam);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENT);
            httpParam=getArguments().getString("param");
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment_online_conuslting, container, false);
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        ToastUtils.showShortToast(httpParam);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onlineConusltingRecyclerView .setLayoutManager(new LinearLayoutManager(TakeMedicinApplication.getContext()));
        RequestParams params=new RequestParams("http://101.69.181.251/api/v1/topics");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result",result);
                try {
                    JSONArray data= new JSONObject(result).getJSONArray("topics");
                    String id, title,created_at,topic_category_name,content;int status,replies_count;
                    for (int i = 0; i <data.length() ; i++) {
                        JSONObject o= (JSONObject) data.get(i);
                        id=o.getString("id");
                        if(MedicationHelper.isNullOrEmpty(o.getString("title")))
                        {
                            title="";
                        }else {
                            title =o.getString("title");
                        }
                        if(MedicationHelper.isNullOrEmpty(o.getString("created_at")))
                        {
                            created_at="";
                        }else {
                            created_at =o.getString("created_at");
                        }
                        if(MedicationHelper.isNullOrEmpty(o.getString("topic_category_name")))
                        {
                            topic_category_name="";
                        }else {
                            topic_category_name =o.getString("topic_category_name");
                        }
                        if(MedicationHelper.isNullOrEmpty(o.getString("content")))
                        {
                            content="";
                        }else {
                            content =o.getString("content");
                        }

                        status = o.getInt("status");


                        replies_count =o.getInt("replies_count");

                        listTopicsData.add(new TopicsData(id,title,created_at,topic_category_name,content,status,replies_count));
                    }
                    onlineConusltingRecyclerView .setLayoutManager(new LinearLayoutManager(TakeMedicinApplication.getContext()));
                    commonAdapter =new CommonAdapter<TopicsData>(
                            TakeMedicinApplication.getContext(),R.layout.online_conuslting_items,listTopicsData) {
                        @Override
                        public void convert(ViewHolder holder, TopicsData topicsData, int position) {
                            Log.e("zhi",topicsData.content);
                            holder.setText(R.id.created_at,"提问时间"+topicsData.created_at);
                            if(topicsData.status==0){
                                holder.setTextViewRightImage(R.id.status,getResources().getDrawable(R.mipmap.yiwen));
                            }else
                            {
                                holder.setTextViewRightImage(R.id.status,getResources().getDrawable(R.mipmap.queding));
                            }
                            holder.setText(R.id.topic_category_name,"#"+topicsData.topic_category_name+"#");
                            holder.setText(R.id.content,topicsData.content);
                            holder.setText(R.id.replies_count,topicsData.replies_count+"");
                        }


                    };

                    onlineConusltingRecyclerView.setAdapter(commonAdapter);
                    commonAdapter.notifyDataSetChanged();

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
/*    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvents.sendParamsToOnlineConsultingFragment event) {
        // UI updates must run on MainThread

        if (event == BaseEvents.sendParamsToOnlineConsultingFragment.SEND_Param) {

            ToastUtils.showShortToast(event.getObject().toString());
        }

    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }



}
