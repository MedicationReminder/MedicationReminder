package com.renqi.takemedicine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        SharedPreferences sp = getApplicationContext().getSharedPreferences("textRemarks", Context.MODE_PRIVATE);
        String result = sp.getString("textRemarks", "");
        if(!result.equals(""))
        textRemarks.setText(result);
    }

    @Event(R.id.iption)
    private void iption(View view){

        Intent intent=new Intent();
        intent.putExtra("1",textRemarks.getText().toString());

        setResult(2,intent);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("textRemarks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("textRemarks","");
        editor.commit();

        finish();
    }

    @Event(R.id.home)
    private void home(View view){

        SharedPreferences sp = getApplicationContext().getSharedPreferences("textRemarks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("textRemarks",  textRemarks.getText().toString());
        editor.commit();

        finish();
    }
}
