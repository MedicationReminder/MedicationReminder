package com.renqi.takemedicine.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.bean.InfoEntity;
import com.renqi.takemedicine.bean.TopicsData;
import com.renqi.takemedicine.utils.MedicationHelper;
import com.renqi.takemedicine.view.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**

 */
public class PlaceHolderFragment extends BaseFragment {
    Handler handler = new Handler();
    ProgressBar progressBar;
 //   TextView textView;
    InfoEntity info;
    private CommonAdapter commonAdapter;
    RecyclerView onlineConusltingRecyclerView;
    private List<TopicsData> listTopicsData=new ArrayList<>();
    private  String httpParam;
    private static final String ARG_INFO_ENTITY = "arg_info_entity";
    private static final int DELAY_TIME = 1000;

    public PlaceHolderFragment() {
    }

    public static PlaceHolderFragment newInstance(InfoEntity infoEntity) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INFO_ENTITY, infoEntity);
        fragment.setArguments(args);
        if (infoEntity != null) {
            fragment.setTitle(infoEntity.getTitle());
        }
        return fragment;
    }

    @Override
    public void initVariables(Bundle bundle) {
        info = bundle.getParcelable(ARG_INFO_ENTITY);
        httpParam=info.getTitle();
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_fragment_online_conuslting, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        onlineConusltingRecyclerView= (RecyclerView) rootView.findViewById(R.id.onlineConusltingRecyclerView);
        onlineConusltingRecyclerView .setLayoutManager(new LinearLayoutManager(TakeMedicinApplication.getContext()));
     //   textView = (TextView) rootView.findViewById(R.id.section_label);
       /* onlineConusltingRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                    listTopicsData.clear();
                commonAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.VISIBLE);
                initRecyListView();
            }
        });*/


        return rootView;
    }

    @Override
    protected void initData() {
        // 模拟耗时操作 比如网络请求
        if (!isPrepared()) {
            Log.w("initData", "目标已被回收");
            return;
        }
        initRecyListView();

       /* handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!TextUtils.isEmpty(info.getShowNumber())) {
                 //   textView.setVisibility(View.VISIBLE);
                //    textView.setText(info.getTitle() + "\n" + getString(R.string.section_format, info.getShowNumber
                 //           ()));
                }
                progressBar.setVisibility(View.GONE);
            }
        }, DELAY_TIME);*/

    }

    public void refreshData(InfoEntity infoEntity) {
        if (infoEntity != null) {
            info = infoEntity;

            //如果被回收的Fragment会重新从Bundle里获取数据,所以也要更新一下
            Bundle args = getArguments();
            if (args != null) {
                args.putParcelable(ARG_INFO_ENTITY, info);
            }

            /*if (textView != null) {
                textView.setVisibility(View.GONE);
            }*/
            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
            }

            if (isFragmentVisible()) {
                initData();
            } else {
                setForceLoad(true);
            }
        }
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {}
    private void initRecyListView() {
        RequestParams params=new RequestParams("http://101.69.181.251/api/v1/topics");
        params.addBodyParameter("topic_category_name",httpParam);
        Log.e("params",params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result",result);
                try {
                    JSONArray data= new JSONObject(result).getJSONArray("topics");
                    String title,created_at,topic_category_name,content;int status,replies_count;
                    listTopicsData.clear();
                    if(data.length()==0)
                        ToastUtils.showShortToast("还没有相关"+httpParam+"的精华问答！");
                    for (int i = 0; i <data.length() ; i++) {
                        JSONObject o= (JSONObject) data.get(i);
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

                        listTopicsData.add(new TopicsData(title,created_at,topic_category_name,content,status,replies_count));
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
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
