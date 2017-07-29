package com.renqi.takemedicine.bean;

/**
 * Created by zsj on 2017/7/25.
 */

public class MedIntroductionAdapterBean {
    String name;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MedIntroductionAdapterBean(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
