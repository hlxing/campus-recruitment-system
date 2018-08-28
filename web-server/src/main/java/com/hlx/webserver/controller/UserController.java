package com.hlx.webserver.controller;

import com.hlx.webserver.model.dto.UserLoginDTO;
import com.hlx.webserver.model.dto.UserRegisterDTO;
import com.hlx.webserver.model.po.ApiResult;
import com.hlx.webserver.service.UserService;
import com.hlx.webserver.util.IpUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @description: 用户控制层
 * @author: hlx 2018-08-14
 **/
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody UserLoginDTO loginDTO, HttpServletRequest request) {
        ApiResult<String> apiResult = new ApiResult<>();
        userService.login(loginDTO,request);
        apiResult.setText("login success");
        log.info("用户:" + loginDTO.getName() + "->>登录成功!");
        return apiResult;
    }

    @ApiOperation(value = "用户注册", notes = "用户名3-12位,由大小写字母数字及汉字组合;" +
            "密码6-12位,大小写字母及数字组合")
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功！"),
            @ApiResponse(code = 130, message = "用户名已经存在"),
            @ApiResponse(code = 230, message = "邮箱已经存在"),
            @ApiResponse(code = 231, message = "邮箱验证码错误"),
            @ApiResponse(code = 232, message = "邮箱验证码无效")
    })
    @PostMapping("/register")
    public ApiResult<String> register( @Valid @RequestBody UserRegisterDTO registerDTO) {
        ApiResult<String> apiResult = new ApiResult<>();
        userService.register(registerDTO);
        apiResult.setText("register success");
        log.info("用户:" + registerDTO.getName() + "->>注册成功!");
        return apiResult;
    }

    @ApiOperation(value = "获取邮箱验证码", notes = "绑定邮箱")
    @ApiImplicitParam(name = "email", value = "邮箱")
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功！"),
            @ApiResponse(code = 132, message = "邮箱已经存在"),
            @ApiResponse(code = 133, message = "邮箱非法"),
    })
    @GetMapping("/emailCaptcha")
    public ApiResult<String> mailCaptcha(@RequestParam("email") String email) {
        ApiResult<String> apiResult = new ApiResult<>();
        userService.getEmailCaptcha(email);
        apiResult.setText("email captcha success");
        return apiResult;
    }

    @ApiOperation(value = "注销")
    @GetMapping("/logout")
    public ApiResult<String> logout(HttpServletRequest request) {
        ApiResult<String> apiResult = new ApiResult<>();
        userService.logout(request);
        apiResult.setText("logout success");
        return apiResult;
    }


    @ApiOperation(value = "获取验证码")
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response, HttpServletRequest request) {
        // 由于redis冒号作为键的划分标志,需要手动将':'转 '-'
        // 后面将会把ip地址存储在redis
        String ip = IpUtil.getAddress(request)
                .replaceAll(":","-");
        response.setContentType("image/jpeg");
        try {
            userService.getCaptcha(ip, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
