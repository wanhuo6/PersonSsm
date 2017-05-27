package com.ahuo.spring.util;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ahuo on 17-5-27.
 */
public class ResponseUtils {

    public static void setResponse(HttpServletResponse response){
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

}
