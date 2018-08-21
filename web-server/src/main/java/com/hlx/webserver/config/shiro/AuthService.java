package com.hlx.webserver.config.shiro;

import com.hlx.webserver.model.Permission;
import com.hlx.webserver.model.Role;
import com.hlx.webserver.service.PermissionService;
import com.hlx.webserver.service.RoleService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @description: 认证服务实现
 * @author: hlx 2018-08-19
 **/
@Service
public class AuthService extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private PermissionService permissionService;

    private RoleService roleService;

    @Autowired
    public AuthService(PermissionService permissionService, RoleService roleService) {
        this.permissionService = permissionService;
        this.roleService = roleService;
    }

    //判断支持的token类型
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }

    //根据身份(用户名)获取角色和权限,即权限判断
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer userId = Integer.valueOf(principalCollection.toString());
        logger.info("用户:" + userId + "权限判断服务");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取角色和权限,循环添加
        List<Role> roles = roleService.getByUserId(userId);
        for (Role role : roles) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            // 添加角色对应的权限
            List<Permission> permissions = permissionService.getByRoleId(role.getId());
            for (Permission permission : permissions) {
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        logger.info("Role->>" + simpleAuthorizationInfo.getRoles());
        logger.info("permission->>" + simpleAuthorizationInfo.getStringPermissions());
        return simpleAuthorizationInfo;
    }

    /**
     * 定义获取用户信息的逻辑,即登录判断(token合法性),错误直接抛出异常(原意)
     *
     * 由于Spring-Session在Restful下的特殊性, {@link (AuthFilter)}在executeLogin方法中
     * 通过检测session合法性来判断是否登录,此函数仅用来包装SimpleAuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = authenticationToken.getPrincipal()
                 .toString();
        String token = authenticationToken.getCredentials()
                .toString();
        // SimpleAuthenticationInfo用于后面权限判断,第三个参数为Realm Name
        return new SimpleAuthenticationInfo(name,token,"auService");
    }
}
