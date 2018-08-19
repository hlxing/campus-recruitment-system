package com.hlx.webserver.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

import javax.servlet.http.HttpSession;

/**
 * @description: shiro认证实体,定义token,以及解析token方法
 * @author: hlx 2018-08-19
 **/
public class AuthToken implements AuthenticationToken{

    //容器中Session/Spring-session
    private HttpSession session;

    public AuthToken(HttpSession session) {
        this.session = session;
    }

    //获取身份(用户名)
    @Override
    public Object getPrincipal() {
        System.out.println("Principal->>" + session.getAttribute("name"));
        return session.getAttribute("name");
    }

    //获取凭证
    @Override
    public Object getCredentials() {
        System.out.println("Credentials->>" + session.getId());
        return session.getId();
    }

    @Override
    public String toString() {
        return session.getId() + ":" + session.getId();
    }
}
