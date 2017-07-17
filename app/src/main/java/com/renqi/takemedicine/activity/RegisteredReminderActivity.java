package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_registered_reminder)
public class RegisteredReminderActivity extends BaseActivity {

    @ViewInject(R.id.registerd_reminder_reminder)
    private TextView reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setToolBarTitle(AppConstants.ToolBarTitle.registrationReminder);
    }

    @Event(R.id.registerd_reminder_reminder)
    private void  registerd_reminder_reminder(View view)
    {
        startActivity(new Intent(RegisteredReminderActivity.this, ContactActivity.class));
    }
}
