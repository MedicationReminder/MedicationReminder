package com.renqi.takemedicine.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.utils.MedicationHelper;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;


/**
 *吃药提醒
 */


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.text) private TextView textView;
    @ViewInject(R.id.dateText)private TextView dateText;
    private PromptDialog promptDialog;
    private static final String TAG = "MainActivity";
    PromptButton promptButton,promptButton1;
    private long firstTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setToolBarTitle(AppConstants.ToolBarTitle.takemedicationReminder);
        dateText.setText(MedicationHelper.getTime());
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);


        promptButton = new PromptButton("挂号提醒", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton promptButton) {
                startActivity(new Intent(MainActivity.this, RegisteredReminderActivity.class));
            }


        });
        promptButton.setTextColor(Color.parseColor("#59acdf"));
        promptButton1 = new PromptButton("用药提醒", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton promptButton) {
                startActivity(new Intent(MainActivity.this,MedicationReminderActivity.class));
            }
        });
        promptButton1.setTextColor(Color.parseColor("#FF4081"));
    }

    @Event(R.id.button2)
    private void button2(View view){

//      promptDialog.getAlertDefaultBuilder().sheetCellPad(0).round(0);
        //设置按钮的特点，颜色大小什么的，具体看PromptButton的成员变量
        PromptButton cancle = new PromptButton("取消", null);
        cancle.setTextColor(Color.parseColor("#59acdf"));

        //设置显示的文字大小及颜色
        promptDialog.getAlertDefaultBuilder().textSize(12).textColor(Color.GRAY);
        //默认两个按钮为Alert对话框，大于三个按钮的为底部SHeet形式展现
        promptDialog.showAlertSheet("", true, cancle, promptButton, promptButton1);
    }
    @Event(R.id.addContact)
    private void addContact(View view)
    {
        startActivity(new Intent(MainActivity.this,AddContactActivity.class));
    }
    @Event(R.id.logistics)
    private void logistics(View view){
       startActivity(new Intent(MainActivity.this,LogisticsWebActivity.class));
    }

    @Event(R.id.national_drugstore)
    private void nationalDrugstore(View view){
        startActivity(new Intent(MainActivity.this,NationalDrugstoreWebActivity.class));
    }
    @Event(R.id.medication_introduction)
    private void medicationIntroduction(View view){
        startActivity(new Intent(MainActivity.this,MedicationIntroductionWebActivity.class));
    }

    @Event(R.id.related_drugs)
    private void relatedDrugs(View view){
        startActivity(new Intent(MainActivity.this,RelatedDrugsWebActivity.class));
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
            long secondTime=System.currentTimeMillis();
            if(secondTime-firstTime>2000){
                Toast.makeText(MainActivity.this,"再按一次退出APP",Toast.LENGTH_SHORT).show();
                firstTime=secondTime;
                return ;
            }else{
                System.exit(0);
            }

        }

    }

}
