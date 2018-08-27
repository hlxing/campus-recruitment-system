package com.hlx.webserver.controller;

import com.hlx.webserver.model.po.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 连接测试控制层
 * @author: hlx 2018-08-14
 **/
@RestController
@RequestMapping("/connect")
public class ConnectController {

    private static final Logger logger = LoggerFactory.getLogger(ConnectController.class);

    @ApiOperation(value = "连接测试", notes = "直接GET!")
    @GetMapping("/test")
    public ApiResult<String> test() {
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setText("Connect Success");
        logger.info("测试连接成功!");
        return apiResult;
    }

    //登录判断
    @ApiOperation(value = "登录拦截测试")
    @GetMapping("/test2")
    public ApiResult<String> test2() {
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setText("Connect Success");
        logger.info("测试连接2成功!");
        return apiResult;
    }

    //admin权限判断
    @ApiOperation(value = "admin权限拦截测试")
    @RequiresRoles("admin")
    @GetMapping("/test3")
    public ApiResult<String> test3() {
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setText("Connect Success");
        logger.info("测试连接3成功!");
        return apiResult;
    }

    //admin2权限判断
    @ApiOperation(value = "admin2权限拦截测试")
    @RequiresRoles("admin2")
    @GetMapping("/test4")
    public ApiResult<String> test4() {
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setText("Connect Success");
        logger.info("测试连接4成功!");
        return apiResult;
    }

}