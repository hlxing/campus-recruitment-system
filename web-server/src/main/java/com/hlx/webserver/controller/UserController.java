package com.hlx.webserver.controller;

import com.hlx.webserver.constant.UserValidation;
import com.hlx.webserver.model.dto.LoginDTO;
import com.hlx.webserver.model.dto.RegisterDTO;
import com.hlx.webserver.model.po.ApiResult;
import com.hlx.webserver.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        ApiResult<String> apiResponse = new ApiResult<>();
        boolean loginSuccess = userService.login(loginDTO,request);
        if (loginSuccess) {
            apiResponse.setText("login success");
            logger.info("用户:" + loginDTO.getName() + "->>登录成功!");
        } else {
            apiResponse.setStatus(404);
            apiResponse.setText("login error");
            logger.info("用户:" + loginDTO.getName() + "->>登录失败!");
        }
        return apiResponse;
    }

    @ApiOperation(value = "用户注册", notes = "用户名3-12位,由大小写字母数字及汉字组合;" +
            "密码6-12位,大小写字母及数字组合")
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功！"),
            @ApiResponse(code = 130, message = "用户名已经存在"),
            @ApiResponse(code = 131, message = "用户名非法"),
            @ApiResponse(code = 132, message = "邮箱已经存在"),
            @ApiResponse(code = 133, message = "邮箱非法"),
            @ApiResponse(code = 134, message = "邮箱验证码错误"),
            @ApiResponse(code = 135, message = "邮箱验证码无效"),
            @ApiResponse(code = 136, message = "密码非法")
    })
    @PostMapping("/register")
    public ApiResult<String> register(@RequestBody RegisterDTO registerDTO) {
        ApiResult<String> apiResponse = new ApiResult<>();
        UserValidation registerResult = userService.register(registerDTO);
        apiResponse.setStatus(registerResult.getCode());
        apiResponse.setText(registerResult.getMsg());
        return apiResponse;
    }

    @ApiOperation(value = "获取邮箱验证码", notes = "绑定邮箱")
    @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String")
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功！"),
            @ApiResponse(code = 132, message = "邮箱已经存在"),
            @ApiResponse(code = 133, message = "邮箱非法"),
    })
    @GetMapping("/emailCaptcha/{email}")
    public ApiResult<String> mailCaptcha(@PathVariable("email") String email) {
        ApiResult<String> apiResponse = new ApiResult<>();
        UserValidation captchaResult = userService.getEmailCaptcha(email);
        apiResponse.setText(captchaResult.getMsg());
        apiResponse.setStatus(captchaResult.getCode());
        return apiResponse;
    }

    @ApiOperation(value = "注销")
    @GetMapping("/logout")
    public ApiResult<String> logout(HttpServletRequest request) {
        ApiResult<String> apiResponse = new ApiResult<>();
        boolean logoutSuccess = userService.logout(request);
        if (!logoutSuccess) {
            apiResponse.setStatus(404);
        }
        return apiResponse;
    }

}
