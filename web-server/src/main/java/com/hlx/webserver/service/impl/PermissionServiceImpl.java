package com.hlx.webserver.service.impl;

import com.hlx.webserver.dao.PermissionDao;
import com.hlx.webserver.model.po.Permission;
import com.hlx.webserver.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 权限接口实现
 * @author: hlx 2018-08-21
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao;

    @Autowired
    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public List<Permission> getByRoleId(Integer roleId) {
        if (roleId != null && roleId > 0) {
            return permissionDao.getByRoleId(roleId);
        }
        return null;
    }

}
