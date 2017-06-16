package com.ahuo.spring.util;

import java.util.UUID;

/**
 * Created by wanhuo on 2017-6-16.
 */
public class CommonUtils {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
