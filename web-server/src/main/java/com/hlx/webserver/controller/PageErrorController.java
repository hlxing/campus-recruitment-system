package com.hlx.webserver.controller;

import com.hlx.webserver.model.po.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 页面错误控制层
 * @author: hlx 2018-08-20
 **/
@Controller
public class PageErrorController implements ErrorController {

    //页面错误的处理,直接返回json
    @ApiOperation(value = "错误页面", notes = "访问未知接口时返回")
    @GetMapping(value = "/error")
    @ResponseBody
    public ApiResult<String> error() {
        return new ApiResult<>(13, ":boom:", null);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
