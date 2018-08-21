package com.hlx.webserver.service;

import com.hlx.webserver.model.Permission;

import java.util.List;

/**
 * @description: 权限服务接口
 * @author: hlx 2018-08-21
 **/
public interface PermissionService {

    // 根据角色Id获取所有权限
    List<Permission> getByRoleId(Integer roleId);

}
