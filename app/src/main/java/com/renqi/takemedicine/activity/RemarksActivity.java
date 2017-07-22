package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.base.EventbusActivity;
import com.renqi.takemedicine.bean.Remarks;
import com.renqi.takemedicine.event.BaseEvents;
import com.renqi.takemedicine.utils.TipDialog;
import com.renqi.takemedicine.utils.ToastUtil;
import com.renqi.takemedicine.view.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_remarks)
public class RemarksActivity extends EventbusActivity {
    private List<Remarks> remarksSpecialList=new ArrayList<>();
    private List<Remarks> remarkseFoodList=new ArrayList<>();
    private List<Remarks> remarksTimeList=new ArrayList<>();
    private List<Remarks> remarksWaterList=new ArrayList<>();
    @ViewInject(R.id.textRemarks)
    private EditText textRemarks;
    private String textRemarksString="",textRemarkTime="",textRemarkWater="",textRemarkFood="",textRemarkSpecial="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
             setToolBarTitle(AppConstants.ToolBarTitle.remarks);
             setIption(AppConstants.iption.complete);

    }
    @Event(R.id.editText5)
    private void  editText5(View view){
        TipDialog tipDialog=new TipDialog(RemarksActivity.this);
        tipDialog.show();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = tipDialog.getWindow().getAttributes();
        lp.width = (int)(display.getWidth()); //设置宽度
        lp.height=(int)(display.getHeight());//设置高度
        tipDialog.getWindow().setAttributes(lp);
        tipDialog.setCancelable(false);
        //点击dialog以外的地方dialog不消失*/
    }
    @Event(R.id.takeMedRemind)
    private void takeMedRemind(View v)
    {
        Intent intent=new Intent(RemarksActivity.this,RemakesRecyclerViewActivity.class);
        intent.putExtra("type","time");
        startActivityForResult(intent,1);
        if(TakeMedicinApplication.isFirstTiem==1){
        BaseEvents.myEvent event2 = BaseEvents.myEvent.SENDREMARKSTIMELIST;
        event2.setObject(remarksTimeList);
        EventBus.getDefault().postSticky(event2);
        }
    }
    @Event(R.id.WaterMedRemind)
    private void WaterMedRemind(View view)
    {
        Intent intent=new Intent(RemarksActivity.this,RemakesRecyclerViewActivity.class);
        intent.putExtra("type","water");
        startActivityForResult(intent,1);
        if(TakeMedicinApplication.isFirstWater==1)
        {
        BaseEvents.myEvent event2 = BaseEvents.myEvent.SENDREMARKSWATERLIST;
        event2.setObject(remarksWaterList);
        EventBus.getDefault().postSticky(event2);
        }
    }
    @Event(R.id.EatRemind)
    private void EatRemind(View view)
    {
        Intent intent=new Intent(RemarksActivity.this,RemakesRecyclerViewActivity.class);
        intent.putExtra("type","food");
        startActivityForResult(intent,1);
        if(TakeMedicinApplication.isFisterFood==1){
        BaseEvents.myEvent event2 = BaseEvents.myEvent.SENDREMARKSFOODLIST;
        event2.setObject(remarkseFoodList);
        EventBus.getDefault().postSticky(event2);}
    }
    @Event(R.id.SpecialReminder)
    private void SpecialReminder(View view)
    {
        Intent intent=new Intent(RemarksActivity.this,RemakesRecyclerViewActivity.class);
        intent.putExtra("type","special");
        startActivityForResult(intent,1);
        if(TakeMedicinApplication.isFirstSpecial==1)
        {
        BaseEvents.myEvent event2 = BaseEvents.myEvent.SENDREMARKSPEICALLIST;
        event2.setObject(remarksSpecialList);
        EventBus.getDefault().postSticky(event2);
        }
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvents.CommonEvent event) {

        // UI updates must run on MainThread
        if(event==BaseEvents.CommonEvent.SENDREMARKSFOODLIST) {
            if (TakeMedicinApplication.isFisterFood == 1) {
                remarkseFoodList = (List<Remarks>) event.getObject();
                //ToastUtils.showShortToast(remarkseFoodList.get(0).data);

            }
        }
        if(event==BaseEvents.CommonEvent.SENDREMARKSTIMELIST) {
            if(TakeMedicinApplication.isFirstTiem==1) {
                remarksTimeList = (List<Remarks>) event.getObject();
            //    ToastUtils.showShortToast(remarksTimeList.get(0).data);


            }
        }
        if(event==BaseEvents.CommonEvent.SENDREMARKSWATERLIST) {
            if(TakeMedicinApplication.isFirstWater==1) {
                remarksWaterList = (List<Remarks>) event.getObject();


            }
        }
        if(event==BaseEvents.CommonEvent.SENDREMARKSPEICALLIST) {
            if(TakeMedicinApplication.isFirstSpecial==1) {
                remarksSpecialList = (List<Remarks>) event.getObject();

            }
        }
        for (Remarks r : remarkseFoodList) {
            if (r.isSelect) {
                Log.e("选中", r.data + "  id是  " + r.id);
                textRemarkFood = textRemarkFood+r.data;
            }
        }
        for (Remarks r : remarksTimeList) {
            if (r.isSelect) {
                Log.e("选中", r.data + "  id是  " + r.id);
                textRemarkTime = textRemarkTime+r.data;
            }
        }
        for (Remarks r : remarksWaterList) {
            if (r.isSelect) {
                Log.e("选中", r.data + "  id是  " + r.id);
                textRemarkWater = textRemarkWater+r.data;
            }
        }
        for (Remarks r : remarksSpecialList) {
            if (r.isSelect) {
                Log.e("选中", r.data + "  id是  " + r.id);
                textRemarkSpecial =textRemarkSpecial+ r.data;
            }
        }
        textRemarks.setText(textRemarkTime+textRemarkWater+textRemarkFood+textRemarkSpecial);
        textRemarkFood="";textRemarkTime="";textRemarkWater="";textRemarkSpecial="";
    }
    @Event(R.id.iption)
    private void iption(View view)
    {
        BaseEvents.sendRemarks event = BaseEvents.sendRemarks.SEND_REMARKS;
        event.setObject(textRemarks.getText().toString().trim());
        EventBus.getDefault().postSticky(event);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&requestCode==RESULT_OK)
        {

        }
    }

}
