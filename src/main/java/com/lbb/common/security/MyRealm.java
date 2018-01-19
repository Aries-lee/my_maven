package com.lbb.common.security;

import com.lbb.user.entity.Permission;
import com.lbb.user.entity.Role;
import com.lbb.user.entity.User;
import com.lbb.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by Administrator on 2018/1/17.
 */

public class MyRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/17 16:26
     *@Description 授权
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findUserByUserName(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Role role: user.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for (Permission permission:role.getPermissionList()) {
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findUserByUserName(username);
        if(null == user){
            return null;
        } else {
            AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
            SecurityUtils.getSubject().getSession().setAttribute("userinfo",user);
            return info;
        }
    }
}
