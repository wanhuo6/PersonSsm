package com.ahuo.spring.dto;

import com.ahuo.spring.base.BaseResponse;

/**
 * Created by wanhuo on 2017-6-4.
 */
public class SubmitResponse extends BaseResponse {
    boolean submit;

    public boolean isSubmit() {
        return submit;
    }

    public void setSubmit(boolean submit) {
        this.submit = submit;
    }
}
