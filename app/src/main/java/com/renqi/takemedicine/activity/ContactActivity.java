package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.ContactAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_contact)

public class ContactActivity extends BaseActivity {
    @ViewInject(R.id.lv_contact)
    private ListView lvContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.Contacts);
        lvContact.setAdapter(new ContactAdapter(this));
    }
}
