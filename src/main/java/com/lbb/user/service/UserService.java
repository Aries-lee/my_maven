package com.lbb.user.service;

import com.lbb.user.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/11.
 */
public interface UserService {

    User findUserByUserName(String username);

    void createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
