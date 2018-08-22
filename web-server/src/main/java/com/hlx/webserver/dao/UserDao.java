package com.hlx.webserver.dao;

import com.hlx.webserver.model.User;
import org.springframework.stereotype.Repository;

/**
 * @description: 用户DAO
 * @author: hlx 2018-08-14
 **/
@Repository
public interface UserDao {

    User getByName(String name);

    User getByEmail(String email);
}
