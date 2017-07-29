package com.renqi.takemedicine.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.utils.MedicationHelper;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 吃药提醒
 */


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.text)
    private TextView textView;
    @ViewInject(R.id.dateText)
    private TextView dateText;
    private PromptDialog promptDialog;
    private static final String TAG = "MainActivity";
    private long firstTime = 0;
    private String[] permissions = new String[]{
            Manifest.permission.READ_CONTACTS
    };
    @ViewInject(R.id.toolbarF)
   private FrameLayout toolbarF;
    private List<String> mPermissionList = new ArrayList<>();
    /*private List<PromptButton> listpromptButton=new ArrayList<>();*/
    PromptButton promptButton,promptButton1,promptButtonlogist,promptButtonHealthy,promptButtonDoctorOnline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 23) {
            //获取权限
            jurisdiction();
        }

   //  setImmerseLayout(toolbarF);
        setToolBarTitle(AppConstants.ToolBarTitle.takemedicationReminder);
        dateText.setText(MedicationHelper.getWeek(new Date())+MedicationHelper.getTime());
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);

        promptButtonlogist=new PromptButton("物流查询", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton promptButton) {
                startActivity(new Intent(MainActivity.this, LogisticsWebActivity.class));
            }
        });
        promptButtonlogist.setTextColor(Color.parseColor("#59acdf"));
        promptButtonHealthy=new PromptButton("健康头条", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton promptButton) {
                ToastUtils.showShortToast("健康头条");
            }
        });
        promptButtonHealthy.setTextColor(Color.parseColor("#59acdf"));
        promptButtonDoctorOnline=new PromptButton("药师在线咨询", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton promptButton) {
                ToastUtils.showShortToast("药师在线咨询");
            }
        });
        promptButtonDoctorOnline.setTextColor(Color.parseColor("#59acdf"));

        promptButton=new PromptButton("挂号提醒", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton promptButton) {
                startActivity(new Intent(MainActivity.this,RegisteredReminderActivity.class));
            }
        });
        promptButton.setTextColor(Color.parseColor("#59acdf"));
        promptButton1=  new PromptButton("用药提醒", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton promptButton) {
                startActivity(new Intent(MainActivity.this,MedicationReminderActivity.class));
            }
        });
        promptButton1.setTextColor(Color.parseColor("#FF4081"));
    }


    private void jurisdiction() {
        mPermissionList.clear();

        //判断哪些权限未授予

        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }

        // 判断是否为空

        if (mPermissionList.isEmpty()) {
            //未授予的权限为空，表示都授予了
            ToastUtils.showLongToast("权限已全部授予！");
        } else {
            //请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                for (int i = 0; i < grantResults.length; i++) {


                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {


                    }else {
                        //判断是否勾选禁止后不再询问
                        boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);

                        if (showRequestPermission) {

                            jurisdiction();//重新申请权限

                            return;
                        } else {
                            //已经禁止
                        }

                        Log.e("showRequestPermission", "拒绝权限");

                    }
                }

                break;
            default:
                break;
        }

    }


    @Event(R.id.button2)
    private void button2(View view) {

// promptDialog.getAlertDefaultBuilder().sheetCellPad(0).round(0);
        //设置按钮的特点，颜色大小什么的，具体看PromptButton的成员变量
        PromptButton cancle = new PromptButton("取消", null);
        cancle.setTextColor(Color.parseColor("#59acdf"));

        //设置显示的文字大小及颜色
        promptDialog.getAlertDefaultBuilder().textSize(12).textColor(Color.GRAY);

        //默认两个按钮为Alert对话框，大于三个按钮的为底部SHeet形式展现
        promptDialog.showAlertSheet("", true, cancle,promptButtonlogist,
                promptButtonHealthy,promptButtonDoctorOnline, promptButton,promptButton1);
    }
    @Event(R.id.addContact)
    private void addContact(View view)
    {
        startActivity(new Intent(MainActivity.this,AddContactActivity.class));

    }
    @Event(R.id.im_clock)
    private void imClock(View view) {
      startActivity(new Intent(MainActivity.this, KitDetailsActivity.class));

    }


    @Event(R.id.logistics)
    private void logistics(View view) {
         startActivity(new Intent(MainActivity.this,SearchActivity.class));
    }
    @Event(R.id.national_drugstore)
    private void nationalDrugstore(View view) {
        startActivity(new Intent(MainActivity.this, NationalDrugstoreWebActivity.class));
    }

    @Event(R.id.medication_introduction)
    private void medicationIntroduction(View view) {
        startActivity(new Intent(MainActivity.this, MedicationIntroductionWebActivity.class));
    }

    @Event(R.id.related_drugs)
    private void relatedDrugs(View view) {
        startActivity(new Intent(MainActivity.this, RelatedDrugsWebActivity.class));
    }

    /*
    * 此方法是主界面出现promptDialog按返回键dialog dismis 没有promptDialog时 finish
    */
    @Override
    public void onBackPressed() {
        if(!promptDialog.onBackPressed())
        {
            promptDialog.dismiss();
        }else
        {
            finish();}


    }


}
