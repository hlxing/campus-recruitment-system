package com.hlx.webserver.dao;

import com.hlx.webserver.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 角色DAO
 * @author: hlx 2018-08-21
 **/
@Repository
public interface RoleDao {

    List<Role> getByUserId(Integer userId);
}
