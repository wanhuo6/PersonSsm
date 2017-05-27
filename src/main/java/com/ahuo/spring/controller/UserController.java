package com.ahuo.spring.controller;

import com.ahuo.spring.dto.AllUserResponse;
import com.ahuo.spring.entity.User;
import com.ahuo.spring.service.UserService;
import com.ahuo.spring.util.JsonUtils;
import com.ahuo.spring.util.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by ahuo on 17-5-25.
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseUtils.setResponse(response);
        List<User> users = this.userService.findAll();
        PrintWriter writer = response.getWriter();
        AllUserResponse allUserResponse=new AllUserResponse();
        allUserResponse.setUsers(users);
        allUserResponse.setCode(200);
        allUserResponse.setMsg("请求成功！");
        writer.print(JsonUtils.toJson(allUserResponse));
        return;
    }


    @RequestMapping("/test")
    public String test() {
        System.out.println("hello");
        return "index";

    }

}
