package com.lbb.attend.controller;

import com.lbb.attend.entity.Attend;
import com.lbb.attend.service.AttendService;
import com.lbb.common.page.PageQueryBean;
import com.lbb.common.page.QueryCondition;
import com.lbb.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/13.
 */
@Controller
@RequestMapping("/attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    @RequestMapping
    public String toAttend(){
        return "attend";
    }

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/13 14:45
     *@Description 签到
    */
    @RequestMapping("/sign")
    @ResponseBody
    public String signAttend(@RequestBody Attend attend) {
        attendService.signAttend(attend);
        return "suc";
    }

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/13 19:39
     *@Description 后端分页查询
    */
    @RequestMapping("/attendList")
    @ResponseBody
    public PageQueryBean listAttend(QueryCondition condition, HttpSession session){
        User user = (User) session.getAttribute("userinfo");
        String[] rangeDate = condition.getRangeDate().split("/");
        condition.setStartDate(rangeDate[0]);
        condition.setEndDate(rangeDate[1]);
        condition.setUserId(user.getId());
        PageQueryBean result = attendService.listAttend(condition);

        return result;
    }
}
