package com.ahuo.spring.controller;

import com.ahuo.spring.config.AppConfig;
import com.ahuo.spring.dto.AllUserResponse;
import com.ahuo.spring.dto.GetUserResponse;
import com.ahuo.spring.dto.RegisterResponse;
import com.ahuo.spring.entity.User;
import com.ahuo.spring.service.UserService;
import com.ahuo.spring.test.MultiThreadServer;
import com.ahuo.spring.util.CommonUtils;
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
    public void getUsers(HttpServletResponse response) throws IOException {
        ResponseUtils.setResponse(response);
        List<User> users = this.userService.findAll();
        PrintWriter writer = response.getWriter();
        AllUserResponse allUserResponse=new AllUserResponse();
        allUserResponse.setUsers(users);
        allUserResponse.setCode(200);
        allUserResponse.setMsg("请求成功！");
        writer.print(JsonUtils.toJson(allUserResponse));
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseUtils.setResponse(response);
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String msgTip;
        boolean isSuccess=false;
        GetUserResponse getUserResponse=new GetUserResponse();
        if(account==null||password==null){
             msgTip="输入为空";

        }else {
            User user = userService.findUserByAccount(account);
            if (user==null){
                msgTip="账户不存在";
            }else{
                if (password.equals(user.getPassword())){
                    msgTip="请求成功";
                    isSuccess=true;
                    getUserResponse.setUrl(AppConfig.API_HOST+"hello");
                    getUserResponse.setUser(user);
                }else{
                    msgTip="密码错误";
                }
            }
        }
        getUserResponse.setSuccess(isSuccess);
        getUserResponse.setCode(200);
        getUserResponse.setMsg(msgTip);
        PrintWriter writer = response.getWriter();
        writer.print(JsonUtils.toJson(getUserResponse));
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseUtils.setResponse(response);
        String name =request.getParameter("name");
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        //String verifyPsw=request.getParameter("verifyPassword");
        String uuid= CommonUtils.getUUID();
        String msgTip;
        boolean isSubmit=false;
        RegisterResponse registerResponse=new RegisterResponse();
        if (name==null||account==null||password==null){
            msgTip="信息不完整";
        }else if (account.length()<6||password.length()<6){
            msgTip="账户或者密码至少六位";
        }/*else if(!password.equals(verifyPsw)){
            msgTip="两次密码不一致";
        }*/else if(userService.findUserByAccount(account)!=null){
            msgTip="账户已经存在";
        }else{
            User user=new User();
            user.setName(name);
            user.setAccount(account);
            user.setPassword(password);
            user.setUuid(uuid);
            userService.insertUser(user);
            isSubmit=true;
            msgTip="注册成功！";
            registerResponse.setUrl(AppConfig.API_HOST+"hello");
        }

        registerResponse.setCode(200);
        registerResponse.setSuccess(isSubmit);
        registerResponse.setMsg(msgTip);
        PrintWriter writer = response.getWriter();
        writer.print(JsonUtils.toJson(registerResponse));
    }
    @RequestMapping("/hello")
    public String test() {
        System.out.println("hello");
        try {
            new MultiThreadServer().service();
        } catch (IOException e) {
            System.out.println(e.getMessage()+"eee");
            e.printStackTrace();
        }
        return "index";

    }

    @RequestMapping("/webRegister")
    public String register() {
        System.out.println("hello");
        return "register";

    }

}
