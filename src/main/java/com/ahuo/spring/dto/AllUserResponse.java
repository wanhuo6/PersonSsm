package com.ahuo.spring.dto;

import com.ahuo.spring.base.BaseResponse;
import com.ahuo.spring.entity.User;

import java.util.List;

/**
 * Created by ahuo on 17-5-27.
 */
public class AllUserResponse extends BaseResponse {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
