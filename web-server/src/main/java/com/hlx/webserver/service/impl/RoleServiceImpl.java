package com.hlx.webserver.service.impl;

import com.hlx.webserver.dao.RoleDao;
import com.hlx.webserver.model.Role;
import com.hlx.webserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 角色服务实现
 * @author: hlx 2018-08-21
 **/
@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getByUserId(Integer userId) {
        if (userId != null && userId > 0) {
            return roleDao.getByUserId(userId);
        }
        return null;
    }

}
