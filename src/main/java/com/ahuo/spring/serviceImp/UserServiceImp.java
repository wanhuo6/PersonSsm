package com.ahuo.spring.serviceImp;

import com.ahuo.spring.dao.UserMapper;
import com.ahuo.spring.entity.User;
import com.ahuo.spring.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ahuo on 17-5-26.
 */
@Service("userService")
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userDao;

    @Override
    public List<User> findAll() {
        return userDao.selectAll();
    }

    @Override
    public void insertUser(User user) {
        userDao.insert(user);
    }

    @Override
    public User findUserByAccount(String account) {
       return userDao.selectByAccount(account);
    }


}
