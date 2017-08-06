package com.renqi.takemedicine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.FragmentAdapter;

import com.renqi.takemedicine.adapter.SectionsPagerAdapter;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.base.EventbusActivity;
import com.renqi.takemedicine.bean.FragmentPrama;
import com.renqi.takemedicine.bean.InfoEntity;
import com.renqi.takemedicine.bean.response.CreateQAParam;
import com.renqi.takemedicine.event.BaseEvents;
import com.renqi.takemedicine.fragment.FragmentTabOnLineConuslting;
import com.renqi.takemedicine.fragment.PlaceHolderFragment;
import com.renqi.takemedicine.utils.MedicationHelper;
import com.renqi.takemedicine.utils.ToastUtil;


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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xu Wei on 2017/7/30.
 */
@ContentView(R.layout.activity_online_conuslting)
public class OnlineConsultingActivity extends EventbusActivity   {

    PlaceHolderFragment placeHolderFragment;

    @ViewInject(R.id.container)
    private ViewPager mViewPager;
    @ViewInject(R.id.tabs)
    private TabLayout tabs;

    static int VIEWPAGER_OFF_SCREEN_PAGE_LIMIT = 6;
    private ArrayList<Fragment> fragmentListReady = new ArrayList<Fragment>() ;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private List<InfoEntity> tabFragmentTitleName = new ArrayList<>();
    JSONArray datalist;
    String modularName;
    int  positionFragment=0;
    PlaceHolderFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarTitle("在线医师");

        httpInitTabData();
        setIption("创建问答");
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                positionFragment=position;
                fragment= (PlaceHolderFragment) mSectionsPagerAdapter.getItem(positionFragment);
                fragment.ShowViewQAOrShowDemo(new PlaceHolderFragment.ShowViewQAOrShowDemoCallBack() {
                    @Override
                    public void ShowViewQAOrShowDemok(int resultV) {//返回值为0，visible；返回值为4，invisible；返回值为8，gone。
                        if(resultV==0)
                        {
                            setIption("创建问答");
                        }else if(resultV==8)
                        {
                            setIption("提问");
                        }
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void httpInitTabData() {

        RequestParams params=new RequestParams("http://101.69.181.251/api/v1/topics/topic_categories");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    datalist = new JSONArray(result);
                    //   FragmentTabOnLineConuslting fragmentTabOnLineConuslting =new FragmentTabOnLineConuslting() ;
                    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                    VIEWPAGER_OFF_SCREEN_PAGE_LIMIT=datalist.length();
                    for (int i = 0; i < datalist.length(); i++) {
                    //    titleList.add(datalist.getString(i));
                 //       fragmentListReady.add(new FragmentTabOnLineConuslting(new FragmentPrama(datalist.getString(i),i ) ) );
                        tabFragmentTitleName.add(new InfoEntity(datalist.getString(i),""));
                    }
                    mSectionsPagerAdapter.init(tabFragmentTitleName);
                  //  fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titleList, fragmentListReady);
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                   /* getSupportFragmentManager().beginTransaction().replace()*/
                    mViewPager.setOffscreenPageLimit(VIEWPAGER_OFF_SCREEN_PAGE_LIMIT);
                    tabs.setupWithViewPager(mViewPager, true);
                    tabs.setTabsFromPagerAdapter(mSectionsPagerAdapter);
                    tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
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
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvents.sendParamsToOnlineConsultingFragment event) {
        // UI updates must run on MainThread


    }
    @Event(R.id.iption)
    private void  iption(View view)
    {
         fragment= (PlaceHolderFragment) mSectionsPagerAdapter.getItem(positionFragment);

        if( getIption().equals("创建问答"))
        {
            fragment.ShowViewQA(new PlaceHolderFragment.ShowViewQACallBack() {
                @Override
                public void ShowViewQA() {
                    setIption("提问");
                }
            });
            //EVENTBUS改为接口回调
           /* BaseEvents.sendParamsToOnlineConsultingFragment event = BaseEvents.sendParamsToOnlineConsultingFragment.ShowQA;
               EventBus.getDefault().postSticky(event);*/
        }
        else{
            fragment.getData(new PlaceHolderFragment.CallBack() {
                @Override
                public void getResult(String titleText,String contentText) {
                    if(MedicationHelper.isNullOrEmpty(titleText))
                    {
                        ToastUtils.showShortToast("问答标题不能为空!");
                    }else if(MedicationHelper.isNullOrEmpty(contentText))
                    {
                        ToastUtils.showShortToast("问答内容不能为空!");
                    }else {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        if(imm != null) {
                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                        }
                        //创建问答接口
                        RequestParams params=new RequestParams("http://101.69.181.251/api/v1/topics");
                        params.setAsJsonContent(true);
                        List<CreateQAParam> createQAParams=new ArrayList<CreateQAParam>();
                        CreateQAParam createQAParam=new CreateQAParam();
                        try {
                            createQAParam.setTopic(new CreateQAParam.TopicBean(titleText,contentText,datalist.getString(positionFragment)));
                            createQAParams.add(createQAParam);
                            params.setBodyContent(new JSONArray(MedicationHelper.gson.toJson(createQAParams)).getString(0));
                            x.http().post(params, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    setIption("创建问答");
                                    try {

                                    ToastUtils.showShortToast(new JSONObject(result).getString("message")+"!");
                                        fragment.ShowViewDemo(new PlaceHolderFragment.showViewDemoCallBack() {
                                            @Override
                                            public void showViewDemo() {

                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {
                                    ToastUtils.showShortToast(ex.getMessage());
                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }
            });

          /*  BaseEvents.sendParamsToOnlineConsultingFragment event2 = BaseEvents.sendParamsToOnlineConsultingFragment.ShowDemo;
            EventBus.getDefault().postSticky(event2);
            setIption("创建问答");*/
        }
        /*   demoView.setVisibility(View.GONE);
        createQAView.setVisibility(View.VISIBLE);*/


    }


}
