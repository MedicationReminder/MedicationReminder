package com.renqi.takemedicine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.utils.TipDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

@ContentView(R.layout.activity_remarks)
public class RemarksActivity extends BaseActivity {

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
}