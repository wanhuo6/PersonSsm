package com.ahuo.spring.util;

import com.alibaba.fastjson.JSONObject;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

/**
 * Created by ahuo on 17-5-27.
 */
public class JsonUtils {

    public static String toJson(Object object){

        return JSONObject.toJSONString(object, WriteNullStringAsEmpty);
    }

}
