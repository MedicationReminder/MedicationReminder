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

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.base.EventbusActivity;
import com.renqi.takemedicine.bean.Remarks;
import com.renqi.takemedicine.event.BaseEvents;
import com.renqi.takemedicine.utils.TipDialog;
import com.renqi.takemedicine.utils.ToastUtil;
import com.renqi.takemedicine.view.ViewHolder;

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
    private List<Remarks> remarksList=new ArrayList<>();
    @ViewInject(R.id.textRemarks)
    private EditText textRemarks;
    private String textRemarksString="";
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
          startActivity(new Intent(RemarksActivity.this,RemakesRecyclerViewActivity.class));
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvents.CommonEvent event) {

        // UI updates must run on MainThread
        if(event==BaseEvents.CommonEvent.SENDREMARKS){
         remarksList= (List<Remarks>) event.getObject();
            for (Remarks r:remarksList) {
                if(r.isSelect)
                {
                    Log.e("选中",r.data+"  id是  "+r.id);
                    textRemarksString+=r.data;
                }
            }
           textRemarks.setText(textRemarksString);

        }

    }
}
