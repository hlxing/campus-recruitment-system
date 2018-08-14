package com.hlx.webserver.service;

import com.hlx.webserver.model.User;

/**
 * @description: 用户服务接口
 * @author: hlx 2018-08-14
 **/
public interface UserService {

    boolean login(User user);
}
