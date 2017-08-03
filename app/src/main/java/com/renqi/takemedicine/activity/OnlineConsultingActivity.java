package com.renqi.takemedicine.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.FragmentAdapter;
import com.renqi.takemedicine.adapter.SectionsPagerAdapter;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.InfoEntity;
import com.renqi.takemedicine.fragment.FragmentTabOnLineConuslting;
import com.renqi.takemedicine.fragment.FragmentTest;
import com.renqi.takemedicine.fragment.FragmentTest2;
import com.renqi.takemedicine.fragment.FragmentTest3;

import org.json.JSONArray;
import org.json.JSONException;
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
public class OnlineConsultingActivity extends BaseActivity {
    @ViewInject(R.id.container)
    private ViewPager mViewPager;
    @ViewInject(R.id.tabs)
    private TabLayout tabs;
    private List<InfoEntity> tabTitleName = new ArrayList<>();
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ArrayList<String> titleList = new ArrayList<String>();
   // private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>() ;
    private ArrayList<Fragment> fragmentListReady = new ArrayList<Fragment>() {{
        add(new FragmentTabOnLineConuslting());
        add(new FragmentTest());
        add(new FragmentTest2());
        add(new FragmentTest3());
    }};

    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("在线医师");

        httpInitTabData();
    }

    private void httpInitTabData() {

        RequestParams params=new RequestParams("http://101.69.181.251/api/v1/topics/topic_categories");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray datalist=new JSONArray(result);
                    for (int i = 0; i <datalist.length() ; i++) {
                        titleList.add(datalist.getString(i));
                      //  fragmentList.add(fragmentListReady.get(i));
                    }
                    titleList.add("TestFragment"); titleList.add("TestFragment2"); titleList.add("TestFragment3");
                    fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titleList, fragmentListReady);
            /*       tabTitleName.add(new InfoEntity("Test",""));
                    tabTitleName.add(new InfoEntity("Test2",""));
                    tabTitleName.add(new InfoEntity("Test3",""));
                    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                    mSectionsPagerAdapter.init(tabTitleName);*/

                    mViewPager.setAdapter(fragmentAdapter);
                    tabs.setupWithViewPager(mViewPager,true);
                    tabs.setTabsFromPagerAdapter(fragmentAdapter);
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
    @Event(R.id.phone)
    private void phone(View view)
    {

        if (Build.VERSION.SDK_INT >= 23) {
            //获取权限

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:0510-81816227")));
            } else {
                ToastUtils.showLongToast("您已拒绝拨打权限，请前往设置开通或者重新安装！");
            }
        } else {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:0510-81816227")));
        }


    }

}
