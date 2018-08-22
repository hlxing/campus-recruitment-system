package com.hlx.webserver.service;

import com.hlx.webserver.constant.UserValidation;
import com.hlx.webserver.model.User;

/**
 * @description: 用户服务接口
 * @author: hlx 2018-08-14
 **/
public interface UserService {

    // 用户登录
    boolean login(User user);

    // 用户注册
    UserValidation register(User user, String captcha);

    // 获取邮箱验证码
    UserValidation getEmailCaptcha(String email);


}
