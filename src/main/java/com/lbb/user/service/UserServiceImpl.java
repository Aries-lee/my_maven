package com.lbb.user.service;

import com.lbb.common.utils.SecurityUtils;
import com.lbb.user.dao.UserMapper;
import com.lbb.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/11.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    public User user;

    @Override
    public User findUserByUserName(String username) {
        try {
            user = userMapper.selectByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/11 12:57
     *@Description 注册用户
    */
    @Override
    public void createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        userMapper.insertSelective(user);
    }

}
