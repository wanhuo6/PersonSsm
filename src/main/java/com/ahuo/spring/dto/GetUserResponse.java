package com.ahuo.spring.dto;

import com.ahuo.spring.base.BaseResponse;
import com.ahuo.spring.entity.User;

/**
 * Created by wanhuo on 2017-6-4.
 */
public class GetUserResponse extends BaseResponse {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
