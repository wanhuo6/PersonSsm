package com.ahuo.spring.controller;

import com.ahuo.spring.dao.UserMapper;
import com.ahuo.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ahuo on 17-5-25.
 */
@Controller
public class UserController {
  //  @Autowired
  //  private UserMapper userDAO;

    @RequestMapping(value = "/add")
    public String HelloWorld(){
     //   User user=new User();
     //   user.setAccount("spring");
     //   user.setPassword("好的");
      //  userDAO.insert(new User());
        return "index" ;
    }
}
