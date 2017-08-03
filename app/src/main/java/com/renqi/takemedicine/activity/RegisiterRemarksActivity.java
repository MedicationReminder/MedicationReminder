package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_regisiter_remarks)
public class RegisiterRemarksActivity extends BaseActivity {
    @ViewInject(R.id.textRemarks)
    private EditText textRemarks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.remarks);
        setIption(AppConstants.iption.complete);

    }
    @Event(R.id.iption)
    private void home(View view){

        Intent intent=new Intent();
        intent.putExtra("1",textRemarks.getText().toString());

        setResult(2,intent);
        finish();
    }
}
