package com.renqi.takemedicine.http;

import org.apache.http.client.methods.HttpPut;

/**
 * Created by zsj on 2017/8/8.
 */

public class HttpPatch extends HttpPut {
    public HttpPatch(String url) {
        super(url);
    }
    @Override
    public String getMethod() {
        return "PATCH";
    }
}