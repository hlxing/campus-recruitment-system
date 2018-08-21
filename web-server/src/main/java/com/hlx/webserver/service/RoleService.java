package com.hlx.webserver.service;

import com.hlx.webserver.model.Role;

import java.util.List;

/**
 * @description: 角色服务接口
 * @author: hlx 2018-08-21
 **/
public interface RoleService {

    // 根据用户Id获取所有角色
    List<Role> getByUserId(Integer userId);


}
