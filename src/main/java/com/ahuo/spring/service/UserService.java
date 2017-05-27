package com.ahuo.spring.service;

import com.ahuo.spring.entity.User;

import java.util.List;

/**
 * Created by ahuo on 17-5-26.
 */
public interface UserService {

    List<User> findAll();

}
