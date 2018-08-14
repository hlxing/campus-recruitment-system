package com.hlx.webserver.service.impl;

import com.hlx.webserver.dao.UserDao;
import com.hlx.webserver.model.User;
import com.hlx.webserver.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户服务实现
 * @author: hlx 2018-08-14
 **/
@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean login(User user) {
        User rightUser = userDao.getByName(user.getName());
        String encryptedPass = DigestUtils.sha1Hex(user.getPassword());
        return rightUser != null && rightUser.getPassword().equals(encryptedPass);
    }
}
