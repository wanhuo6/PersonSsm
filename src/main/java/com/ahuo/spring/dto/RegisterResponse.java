package com.ahuo.spring.dto;

import com.ahuo.spring.base.BaseResponse;

/**
 * Created by wanhuo on 2017-6-16.
 */
public class RegisterResponse extends BaseResponse {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
}
