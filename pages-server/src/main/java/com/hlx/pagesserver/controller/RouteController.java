package com.hlx.pagesserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RouteController {

    private static final Logger logger = LoggerFactory.getLogger(ConnectController.class);

    @GetMapping(value="/{pageName}")
    public String page(@PathVariable("pageName")String pageName) {
        logger.info("请求进入页面->>"+pageName+".html");
        return pageName;
    }

}
