package com.hlx.webserver.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @description: 认证服务实现
 * @author: hlx 2018-08-19
 **/
@Service
public class AuthService extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    //判断支持的token类型
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }

    //根据身份(用户名)获取角色和权限,即权限判断
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = principalCollection.toString();
        logger.info("用户:" + name + "权限判断服务");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addStringPermission("read");
        return simpleAuthorizationInfo;
    }

    //定义获取用户信息的逻辑,即登录判断
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = authenticationToken.getPrincipal()
                .toString();
        String token = authenticationToken.getCredentials()
                .toString();
        System.out.println(name + ":" + token + "->>登陆判断服务");
        return new SimpleAuthenticationInfo(token,token,name);
    }
}
