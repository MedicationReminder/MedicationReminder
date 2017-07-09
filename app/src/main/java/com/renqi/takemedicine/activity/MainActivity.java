package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.utils.TipDialog;

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
    private PromptDialog promptDialog;
    private static final String TAG = "MainActivity";
    PromptButton promptButton,promptButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setToolBarTitle(AppConstants.ToolBarTitle.medicationReminder);
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
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

            }
        });
        promptButton1.setTextColor(Color.parseColor("#FF4081"));
    }
    @Event(R.id.button2)
    private void button2(View view){
      /* TipDialog tipDialog=new TipDialog(MainActivity.this);
        tipDialog.show();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = tipDialog.getWindow().getAttributes();
        lp.width = (int)(display.getWidth()); //设置宽度
        lp.height=(int)(display.getHeight());//设置高度
        tipDialog.getWindow().setAttributes(lp);
        tipDialog.setCancelable(false);
         //点击dialog以外的地方dialog不消失*/
        //可创建android效果的底部Sheet选择，默认IOS效果，sheetCellPad=0为Android效果的Sheet
//      promptDialog.getAlertDefaultBuilder().sheetCellPad(0).round(0);
        //设置按钮的特点，颜色大小什么的，具体看PromptButton的成员变量
        PromptButton cancle = new PromptButton("取消", null);
        cancle.setTextColor(Color.parseColor("#59acdf"));

        //设置显示的文字大小及颜色
        promptDialog.getAlertDefaultBuilder().textSize(12).textColor(Color.GRAY);
        //默认两个按钮为Alert对话框，大于三个按钮的为底部SHeet形式展现
        promptDialog.showAlertSheet("", true, cancle, promptButton,promptButton1
        );
    }
    @Event(R.id.addContact)
    private void addContact(View view)
    {
        startActivity(new Intent(MainActivity.this,AddContactActivity.class));
    }

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
