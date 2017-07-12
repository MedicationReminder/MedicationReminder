package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

@ContentView(R.layout.activity_medication_reminder)
public class MedicationReminderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.medicationReminder);
      //  setContentView(R.layout.activity_medication_reminder);
    }
    @Event(R.id.Remarks)
    private void  Remarks(View view)
    {
        startActivity(new Intent(MedicationReminderActivity.this,RemarksActivity.class));
    }
    @Event(R.id.selectTime)
    private void selectTime(View view){
        showDatePickDialog(DateType.TYPE_YMDHMS);
    }
    private void showDatePickDialog(DateType type) {
        DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(30);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm:ss");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String str=sdf.format(date);
            Toast.makeText(MedicationReminderActivity.this,str,Toast.LENGTH_SHORT).show();

            }
        });
        dialog.show();
    }
}
