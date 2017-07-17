package com.renqi.takemedicine.activity;

import android.os.Bundle;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;
@ContentView(R.layout.activity_registered_reminder)
public class RegisteredReminderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_registered_reminder);
        x.view().inject(this);
        setToolBarTitle(AppConstants.ToolBarTitle.registrationReminder);
    }
}
