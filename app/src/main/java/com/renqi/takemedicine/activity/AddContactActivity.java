package com.renqi.takemedicine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_add_contact)
public class AddContactActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_add_contact);
        setIption(AppConstants.iption.complete);
        setToolBarTitle(AppConstants.ToolBarTitle.addContacts);
    }
}
