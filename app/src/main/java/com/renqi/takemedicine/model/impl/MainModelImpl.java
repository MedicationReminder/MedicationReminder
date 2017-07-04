package com.renqi.takemedicine.model.impl;

import com.renqi.takemedicine.model.IMainModel;

/**
 * Created by zsj on 2017/7/4.
 */

public class MainModelImpl implements IMainModel {

    @Override
    public boolean doShow() {
        int x = (int) (Math.random() * 100);
        if (x%2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
