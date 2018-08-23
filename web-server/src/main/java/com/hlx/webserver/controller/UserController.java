package com.hlx.webserver.controller;

import com.hlx.webserver.constant.UserValidation;
import com.hlx.webserver.model.dto.LoginDTO;
import com.hlx.webserver.model.dto.RegisterDTO;
import com.hlx.webserver.model.po.ApiResponse;
import com.hlx.webserver.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.crypto.KeyGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description: 用户控制
 * @author: hlx 2018-08-14
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    private RedisOperationsSessionRepository sessionRepository;

    @Autowired
    public UserController(UserService userService,RedisOperationsSessionRepository sessionRepository) {
        this.userService = userService;
        this.sessionRepository = sessionRepository;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Integer userId = userService.login(loginDTO);
        if (userId != null) {
            apiResponse.setText("login success");
            logger.info("用户:" + loginDTO.getName() + "->>登录成功!");
            // 清除已有的sessionID,保证同一时间一处登录
            String sessionId = userService.getSessionIdByUserId(userId);
            if (sessionId != null) {
                sessionRepository.deleteById(sessionId);
            }
            // 自动创建一个新的session,并重新加密保存
            HttpSession session = request.getSession(true);
            userService.updateSessionIdByUserId(userId, session.getId());
        } else {
            apiResponse.setStatus(404);
            apiResponse.setText("login error");
            logger.info("用户:" + loginDTO.getName() + "->>登录失败!");
        }
        return apiResponse;
    }

    @ApiOperation(value = "用户注册", notes = "用户名3-12位,由大小写字母数字及汉字组合;" +
            "密码6-12位,大小写字母及数字组合")
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegisterDTO registerDTO) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        UserValidation registerResult = userService.register(registerDTO);
        apiResponse.setStatus(registerResult.getCode());
        apiResponse.setText(registerResult.getMsg());
        return apiResponse;
    }

    @ApiOperation(value = "获取邮箱验证码", notes = "绑定邮箱")
    @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String")
    @GetMapping("/emailCaptcha/{email}")
    public ApiResponse<String> mailCaptcha(@PathVariable("email") String email) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        UserValidation captchaResult = userService.getEmailCaptcha(email);
        apiResponse.setText(captchaResult.getMsg());
        apiResponse.setStatus(captchaResult.getCode());
        return apiResponse;
    }


}
