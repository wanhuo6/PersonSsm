package com.ahuo.spring.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by wanhuo on 2017-10-13.
 */
public class Util {

    public static String decode(String str, String charCode) {
        try {
            return new String(new BASE64Decoder().decodeBuffer(str), charCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode(byte[] bstr) {
        return new BASE64Encoder().encode(bstr);
    }

}
