package com.hlx.webserver.dao;

import com.hlx.webserver.model.po.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 角色DAO
 * @author: hlx 2018-08-21
 **/
public interface RoleDao {

    List<Role> getByUserId(Integer userId);
}
