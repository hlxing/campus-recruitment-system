package com.hlx.webserver.service;

import com.hlx.webserver.constant.UserValidation;
import com.hlx.webserver.model.dto.req.LoginReqDTO;
import com.hlx.webserver.model.dto.req.RegisterReqDTO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用户服务接口
 * @author: hlx 2018-08-14
 **/
public interface UserService {

    // 用户登录
    boolean login(LoginReqDTO loginDTO, HttpServletRequest request);

    // 用户注册
    UserValidation register(RegisterReqDTO registerDTO);

    // 获取邮箱验证码
    UserValidation getEmailCaptcha(String email);

    // 用户注销
    boolean logout(HttpServletRequest request);

    // 获取验证码
    void getCaptcha(String ip, ServletOutputStream outputStream);
}
