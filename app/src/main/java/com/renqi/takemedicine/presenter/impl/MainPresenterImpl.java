package com.renqi.takemedicine.presenter.impl;

import com.renqi.takemedicine.model.IMainModel;
import com.renqi.takemedicine.model.impl.MainModelImpl;
import com.renqi.takemedicine.presenter.IMainPresenter;
import com.renqi.takemedicine.view.IMainView;

/**
 * Created by zsj on 2017/7/4.
 */

public class MainPresenterImpl implements IMainPresenter {
    private IMainView iMainView;
private IMainModel iMainModel;

    public MainPresenterImpl(IMainView iMainView) {
        this.iMainView = iMainView;
        iMainModel=new MainModelImpl();
    }

    @Override
    public void showDialog() {
       boolean flag= iMainModel.doShow();
        if(flag){
        iMainView.showDialog();}
    }
}
