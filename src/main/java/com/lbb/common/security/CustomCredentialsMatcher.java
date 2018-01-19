package com.lbb.common.security;

import com.lbb.common.utils.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/17.
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        try {
            UsernamePasswordToken userToken = (UsernamePasswordToken) token;
            String password = String.valueOf(userToken.getPassword());
            Object tokenCredentials = SecurityUtils.encryptPassword(password);
            Object accountCredentials = this.getCredentials(info);
            return this.equals(tokenCredentials,accountCredentials);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
