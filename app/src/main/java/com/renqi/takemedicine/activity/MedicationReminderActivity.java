package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

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
}
