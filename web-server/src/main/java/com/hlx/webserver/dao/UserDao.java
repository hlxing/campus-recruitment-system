package com.hlx.webserver.dao;

import com.hlx.webserver.model.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description: 用户DAO
 * @author: hlx 2018-08-14
 **/
public interface UserDao {

    User getByName(String name);

    User getByEmail(String email);

    String getSessionIdByUserId(Integer userId);

    void updateSessionIdByUserId(@Param("userId") Integer userId, @Param("sessionId") String sessionId);

    void save(User user);
}
