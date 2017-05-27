package com.ahuo.entity;

import com.ahuo.spring.base.BaseResponse;
import com.ahuo.spring.entity.User;

import java.util.List;

/**
 * Created by ahuo on 17-5-27.
 */
public class AllUserResponse2 extends BaseResponse {

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    private List<User> users;



}
