package com.renqi.takemedicine.base.response;

/**
 * Created by Xu Wei on 2017/7/15.
 */

public class LoginResponseBean {
    /**
     * status : 200
     * device_token : qqwwrwet009
     */

    private int status;
    private String device_token;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }
}
