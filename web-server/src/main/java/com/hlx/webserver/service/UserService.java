package com.hlx.webserver.service;


import com.hlx.webserver.constant.UserValidation;
import com.hlx.webserver.model.dto.LoginDTO;
import com.hlx.webserver.model.dto.RegisterDTO;

/**
 * @description: 用户服务接口
 * @author: hlx 2018-08-14
 **/
public interface UserService {

    // 用户登录
    Integer login(LoginDTO loginDTO);

    // 用户注册
    UserValidation register(RegisterDTO registerDTO);

    // 获取邮箱验证码
    UserValidation getEmailCaptcha(String email);

    // 获取sessionId
    String getSessionIdByUserId(Integer userId);

    // 更新sessionId
    void updateSessionIdByUserId(Integer userId, String sessionId);
}
