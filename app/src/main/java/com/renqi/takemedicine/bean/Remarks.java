package com.renqi.takemedicine.bean;

/**
 * Created by Xu Wei on 2017/7/17.
 */

public class Remarks {
    public String id;
    public String data;
    public boolean isSelect;
    Remarks(){

    }
    public Remarks(String id, String data, boolean isSelect) {
        this.id = id;
        this.data = data;
        this.isSelect = isSelect;
    }
}
