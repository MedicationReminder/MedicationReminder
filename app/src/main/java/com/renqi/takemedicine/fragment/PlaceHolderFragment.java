package com.renqi.takemedicine.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.activity.CreateQuestionAanswerActivity;
import com.renqi.takemedicine.activity.OnlineConsultingActivity;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.bean.InfoEntity;
import com.renqi.takemedicine.bean.TopicsData;
import com.renqi.takemedicine.event.BaseEvents;
import com.renqi.takemedicine.utils.MedicationHelper;
import com.renqi.takemedicine.utils.ToastUtil;
import com.renqi.takemedicine.view.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.content;
import static android.R.id.edit;


/**

 */
public class PlaceHolderFragment extends BaseFragment {

    CallBack callback;
    Handler handler = new Handler();
    ProgressBar progressBar;
    //   TextView textView;
    InfoEntity info;
    private CommonAdapter commonAdapter;
    RecyclerView onlineConusltingRecyclerView;
    private List<TopicsData> listTopicsData = new ArrayList<>();
    private String httpParam;
    private static final String ARG_INFO_ENTITY = "arg_info_entity";
    private static final int DELAY_TIME = 1500;
    LinearLayout createQAView, demoView;
    EditText title,add_content;

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
        httpParam = info.getTitle();
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_fragment_online_conuslting, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        createQAView = (LinearLayout) rootView.findViewById(R.id.demoView2);
        demoView = (LinearLayout) rootView.findViewById(R.id.demoView);
        title = (EditText) rootView.findViewById(R.id.title);
        add_content= (EditText) rootView.findViewById(R.id.add_content);
        onlineConusltingRecyclerView = (RecyclerView) rootView.findViewById(R.id.onlineConusltingRecyclerView);
        onlineConusltingRecyclerView.setLayoutManager(new LinearLayoutManager(TakeMedicinApplication.getContext()));
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
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

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
    protected void setDefaultFragmentTitle(String title) {
    }

    private void initRecyListView() {
        RequestParams params = new RequestParams("http://101.69.181.251/api/v1/topics");
        params.addBodyParameter("topic_category_name", httpParam);
        Log.e("params", params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
                try {
                    JSONArray data = new JSONObject(result).getJSONArray("topics");
                    String id, title, created_at, topic_category_name, content;
                    int status, replies_count;
                    listTopicsData.clear();
                    if (data.length() == 0)
                        ToastUtils.showShortToast("还没有相关" + httpParam + "的精华问答！");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject o = (JSONObject) data.get(i);
                        id = o.getString("id");
                        if (MedicationHelper.isNullOrEmpty(o.getString("title"))) {
                            title = "";
                        } else {
                            title = o.getString("title");
                        }
                        if (MedicationHelper.isNullOrEmpty(o.getString("created_at"))) {
                            created_at = "";
                        } else {
                            created_at = o.getString("created_at");
                        }
                        if (MedicationHelper.isNullOrEmpty(o.getString("topic_category_name"))) {
                            topic_category_name = "";
                        } else {
                            topic_category_name = o.getString("topic_category_name");
                        }
                        if (MedicationHelper.isNullOrEmpty(o.getString("content"))) {
                            content = "";
                        } else {
                            content = o.getString("content");
                        }

                        status = o.getInt("status");


                        replies_count = o.getInt("replies_count");

                        listTopicsData.add(0,new TopicsData(id, title, created_at, topic_category_name, content, status, replies_count));
                    }
                    onlineConusltingRecyclerView.setLayoutManager(new LinearLayoutManager(TakeMedicinApplication.getContext()));
                    commonAdapter = new CommonAdapter<TopicsData>(
                            TakeMedicinApplication.getContext(), R.layout.online_conuslting_items, listTopicsData) {
                        @Override
                        public void convert(ViewHolder holder, final TopicsData topicsData, int position) {
                            LinearLayout linearLayout = holder.getView(R.id.recycleViewItemLayout);
                            linearLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                   /* ToastUtils.showShortToast(topicsData.id);*/
                                    //T 问答详情跳转
                                    Intent intent=new Intent(getActivity(),CreateQuestionAanswerActivity.class);
                                    intent.putExtra("id",topicsData.id);
                                    startActivity(intent);
                                }
                            });
                            Log.e("zhi", topicsData.content);
                            holder.setText(R.id.created_at, "提问时间" + topicsData.created_at);
                            if (topicsData.status == 0) {
                                holder.setTextViewRightImage(R.id.status, getResources().getDrawable(R.mipmap.yiwen));
                            } else {
                                holder.setTextViewRightImage(R.id.status, getResources().getDrawable(R.mipmap.queding));
                            }
                            holder.setText(R.id.topic_category_name, "#" + topicsData.topic_category_name + "#");
                            holder.setText(R.id.content, topicsData.content);
                            holder.setText(R.id.replies_count, topicsData.replies_count + "");
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvents.sendParamsToOnlineConsultingFragment event) throws JSONException {
        // UI updates must run on MainThread

        if (event == BaseEvents.sendParamsToOnlineConsultingFragment.ShowQA) {

        }
        if (event == BaseEvents.sendParamsToOnlineConsultingFragment.ShowDemo) {

        }
    }


    /*接口*/
    public interface CallBack {
        /*定义一个获取信息的方法*/
        public void getResult(String title,String content);
    }

    /*接口*/
    public interface ShowViewQACallBack {
        /*定义一个获取信息的方法*/
        public void ShowViewQA();
    }
    public interface showViewDemoCallBack{
        public  void showViewDemo();
    }
    /*
    * 返回fragment是showQA还是showDemo的CallBack true显示demo false显示QA
    */
    public interface ShowViewQAOrShowDemoCallBack {
        /*定义一个获取信息的方法*/
        public void ShowViewQAOrShowDemok(int resultV);
    }
    //步骤2:设置监听器
    public void setCallBack(CallBack callBack) {
        this.callback = callBack;
    }
    /*接口回调*/
    public void getData(CallBack callBack) {
    /*获取文本框的信息,当然你也可以传其他类型的参数,看需求咯*/
        String titleText = title.getText().toString();
        callBack.getResult(titleText,add_content.getText().toString().trim());
    }

    public void ShowViewQA(ShowViewQACallBack showViewQACallBack){
        createQAView.setVisibility(View.VISIBLE);
        demoView.setVisibility(View.GONE);
        focus(title);
        title.setText("");
        add_content.setText("");
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(title, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        showViewQACallBack.ShowViewQA();
    }
    public void ShowViewDemo(final showViewDemoCallBack showViewDemoCallBack){
        createQAView.setVisibility(View.GONE);
        demoView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!TextUtils.isEmpty(info.getShowNumber())) {

                }
                initRecyListView();
                showViewDemoCallBack.showViewDemo();
                progressBar.setVisibility(View.GONE);

            }
        }, DELAY_TIME);
    }
    public void ShowViewQAOrShowDemo(ShowViewQAOrShowDemoCallBack showViewQAOrShowDemoCallBack){
        int visibility=demoView.getVisibility();//返回值为0，visible；返回值为4，invisible；返回值为8，gone。
        showViewQAOrShowDemoCallBack.ShowViewQAOrShowDemok(visibility);
    }

    public static void focus(View v) {
        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.requestFocusFromTouch();
    }
}
