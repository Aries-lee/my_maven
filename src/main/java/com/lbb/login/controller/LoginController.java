package com.lbb.login.controller;

import com.lbb.common.utils.SecurityUtils;
import com.lbb.user.entity.User;
import com.lbb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/11.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/11 12:41
     *@Description 登陆页面
    */
    @RequestMapping
    public String login() {
        return "login";
    }

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/11 12:41
     *@Description 校验密码
    */
    @RequestMapping("/check")
    @ResponseBody
    public String checkLogin(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

//      查数据库 调用MD5 对比密码
        User user = userService.findUserByUserName(username);
        if(user !=null) {
            if(SecurityUtils.checkPassword(pwd,user.getPassword())) {
//              校验成功 设置session 返回成功signal
                request.getSession().setAttribute("userinfo",user);
                return "login_success";
            }else {
//              校验失败 返回失败signal
                return "login_fail";
            }
        } else {
//      校验失败 返回失败signal
            return "login_fail";
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.createUser(user);
        return "suc";
    }

}
