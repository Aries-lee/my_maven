package com.lbb.user.controller;

import com.lbb.user.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/11.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("/home")
    public String user() {
        return "home";
    }

    @RequestMapping("/userinfo")
    @ResponseBody
    public User getUser(HttpSession session) {
        User user = (User) session.getAttribute("userinfo");
        return user;
    }

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/13 14:33
     *@Description 退出系统
    */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
