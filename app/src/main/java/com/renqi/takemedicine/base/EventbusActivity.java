package com.renqi.takemedicine.base;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Xu Wei on 2017/7/17.
 */

public class EventbusActivity extends BaseActivity {
    @Override
    public void onStart() {
        super.onStart();
        registEventBus();
    }

    @Override
    public void onStop() {
        unRegistEventBus();
        super.onStop();
    }

    protected void registEventBus() {
        //子类如果需要注册eventbus，则重写此方法
        EventBus.getDefault().register(this);
    }

    protected void unRegistEventBus() {
        //子类如果需要注销eventbus，则重写此方法
        EventBus.getDefault().unregister(this);
    }
}
