package com.hlx.webserver.controller;

import com.hlx.webserver.model.ApiResponse;
import com.hlx.webserver.model.User;
import com.hlx.webserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户控制
 * @author: hlx 2018-08-14
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody User user) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        boolean loginSuccess = userService.login(user);
        if (loginSuccess) {
            apiResponse.setText("login success");
            logger.info("用户:"+user.getName()+"->>登录成功!");
        }else{
            apiResponse.setStatus(404);
            apiResponse.setText("login error");
            logger.info("用户:"+user.getName()+"->>登录失败!");
        }
        return apiResponse;
    }

}
