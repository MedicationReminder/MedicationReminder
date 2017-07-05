package com.renqi.takemedicine.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.xutils.x;

/**
 * Created by Xu Wei on 2017/7/4.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
