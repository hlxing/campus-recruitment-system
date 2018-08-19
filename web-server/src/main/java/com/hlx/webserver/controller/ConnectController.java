package com.hlx.webserver.controller;

import com.hlx.webserver.model.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @description: 服务器连接测试
 * @author: hlx 2018-08-14
 **/
@RestController
@RequestMapping("/connect")
public class ConnectController {

    private static final Logger logger = LoggerFactory.getLogger(ConnectController.class);

    @ApiOperation(value="连接测试", notes="直接GET!")
    @GetMapping("/test")
    public ApiResponse<String> test(HttpSession session){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setText("Connect Success");
        System.out.println(session.getId());
        logger.info("测试连接成功!");
        return apiResponse;
    }

    //登录判断
    @RequiresAuthentication
    @GetMapping("/test2")
    public ApiResponse<String> test2(HttpSession session){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setText("Connect Success");
        System.out.println(session.getId());
        logger.info("测试连接2成功!");
        return apiResponse;
    }

    //admin权限判断
    @RequiresRoles("admin")
    @GetMapping("/test3")
    public ApiResponse<String> test3(HttpSession session){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setText("Connect Success");
        System.out.println(session.getId());
        logger.info("测试连接3成功!");
        return apiResponse;
    }

    //admin2权限判断
    @RequiresRoles("admin2")
    @GetMapping("/test4")
    public ApiResponse<String> test4(HttpSession session){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setText("Connect Success");
        System.out.println(session.getId());
        logger.info("测试连接4成功!");
        return apiResponse;
    }

}