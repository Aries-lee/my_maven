package com.lbb.common.interceptor;

import com.lbb.user.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/11.
 */
public class SessionInteceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri =request.getRequestURI();
        if(uri.indexOf("login") >=0 || uri.indexOf(("error"))>=0||uri.indexOf("attend")>=0||uri.indexOf("reAttend")>=0) {
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("userinfo");
        if (null != user) {
            return true;
        }else {
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
