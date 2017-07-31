package com.renqi.takemedicine.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.bean.TopicsData;
import com.renqi.takemedicine.utils.MedicationHelper;
import com.renqi.takemedicine.view.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptButton;


/**
 * Created by Xu Wei on 2017/7/30.
 */

public class FragmentTabOnLineConuslting extends Fragment {
    private CommonAdapter commonAdapter;
    RecyclerView onlineConusltingRecyclerView;
    private List<TopicsData> listTopicsData=new  ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     View view=   inflater.inflate(R.layout.fragment_tab_fragment_online_conuslting,container,false);
        onlineConusltingRecyclerView= (RecyclerView) view.findViewById(R.id.onlineConusltingRecyclerView);


        return view;
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
                    String title,created_at,topic_category_name,content;int status,replies_count;
                    for (int i = 0; i <data.length() ; i++) {
                        JSONObject o= (JSONObject) data.get(i);
                        if(MedicationHelper.isNullOrEmpty(o.getString("title")))
                        {
                                title="1";
                        }else {
                            title =o.getString("title");
                        }
                        if(MedicationHelper.isNullOrEmpty(o.getString("created_at")))
                        {
                            created_at="1";
                        }else {
                            created_at =o.getString("created_at");
                        }
                        if(MedicationHelper.isNullOrEmpty(o.getString("topic_category_name")))
                        {
                            topic_category_name="1";
                        }else {
                            topic_category_name =o.getString("topic_category_name");
                        }
                        if(MedicationHelper.isNullOrEmpty(o.getString("content")))
                        {
                            content="1";
                        }else {
                            content =o.getString("content");
                        }

                            status = o.getInt("status");


                            replies_count =o.getInt("replies_count");

                        listTopicsData.add(new TopicsData(title,created_at,topic_category_name,content,status,replies_count));
                    }
                    onlineConusltingRecyclerView .setLayoutManager(new LinearLayoutManager(TakeMedicinApplication.getContext()));
                    commonAdapter =new CommonAdapter<TopicsData>(
                            TakeMedicinApplication.getContext(),R.layout.online_conuslting_items,listTopicsData) {
                        @Override
                        public void convert(ViewHolder holder, TopicsData topicsData, int position) {
                            Log.e("zhi",topicsData.content);
                            holder.setText(R.id.created_at,"提问时间+"+topicsData.created_at);
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
}
