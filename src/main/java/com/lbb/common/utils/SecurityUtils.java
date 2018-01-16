package com.lbb.common.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/11.
 */
public class SecurityUtils {
    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/11 9:34
     *@Description 加密算法
    */
    public static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        return result;
    }

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/11 9:35
     *@Description 校验密码
    */
    public static boolean checkPassword(String inputPwd,String dbPwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String result = encryptPassword(inputPwd);
        if(result.equals(dbPwd)){
            return true;
        }else {
            return false;
        }
    }
}
