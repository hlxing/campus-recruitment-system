package com.hlx.webserver.dao;

import com.hlx.webserver.model.po.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 权限DAO
 * @author: hlx 2018-08-21
 **/
public interface PermissionDao {

    List<Permission> getByRoleId(Integer roleId);

}
