package com.hlx.webserver.service;

import com.hlx.webserver.model.dto.UserLoginDTO;
import com.hlx.webserver.model.dto.UserRegisterDTO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用户服务接口
 * @author: hlx 2018-08-14
 **/
public interface UserService {

    // 用户登录
    void login(UserLoginDTO loginDTO, HttpServletRequest request);

    // 用户注册
    void register(UserRegisterDTO registerDTO);

    // 获取邮箱验证码
    void getEmailCaptcha(String email);

    // 用户注销
    void logout(HttpServletRequest request);

    // 获取验证码
    void getCaptcha(String ip, ServletOutputStream outputStream);
}
