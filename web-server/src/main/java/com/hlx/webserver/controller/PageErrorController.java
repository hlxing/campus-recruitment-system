package com.hlx.webserver.controller;

import com.hlx.webserver.model.ApiResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 页面错误控制类
 * @author: hlx 2018-08-20
 **/
@Controller
public class PageErrorController implements ErrorController {

    //页面错误的处理,直接返回json
    @GetMapping(value="/error")
    @ResponseBody
    public ApiResponse<String> error() {
        return new ApiResponse<>(13, ":boom:", null);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
