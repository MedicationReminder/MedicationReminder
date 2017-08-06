package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.FragmentAdapter;

import com.renqi.takemedicine.adapter.SectionsPagerAdapter;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.base.EventbusActivity;
import com.renqi.takemedicine.bean.FragmentPrama;
import com.renqi.takemedicine.bean.InfoEntity;
import com.renqi.takemedicine.event.BaseEvents;
import com.renqi.takemedicine.fragment.FragmentTabOnLineConuslting;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xu Wei on 2017/7/30.
 */
@ContentView(R.layout.activity_online_conuslting)
public class OnlineConsultingActivity extends EventbusActivity {
    @ViewInject(R.id.container)
    private ViewPager mViewPager;
    @ViewInject(R.id.tabs)
    private TabLayout tabs;
    static int VIEWPAGER_OFF_SCREEN_PAGE_LIMIT = 6;
    private ArrayList<String> titleList = new ArrayList<String>();

    private ArrayList<Fragment> fragmentListReady = new ArrayList<Fragment>() ;

    private FragmentAdapter fragmentAdapter;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private List<InfoEntity> tabFragmentTitleName = new ArrayList<>();
    JSONArray datalist;
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
                RequestParams params=new RequestParams("http://101.69.181.251/api/v1/topics");
                try {
                    params.addBodyParameter("topic_category_name",datalist.getString(position));
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            BaseEvents.sendParamsToOnlineConsultingFragment event = BaseEvents.sendParamsToOnlineConsultingFragment.SEND_Param;
                            event.setObject(result);
                            EventBus.getDefault().postSticky(event);
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }


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

        if (event == BaseEvents.sendParamsToOnlineConsultingFragment.SEND_Param) {


        }

    }
}
