package com.hlx.webserver.constant;

/**
 * @description: 错误枚举类的抽象,扩展枚举
 * @author: hlx 2018-08-28
 **/
public interface ApiError {

    // 获取异常信息
    String getMsg();

    // 获取异常代码
    Integer getCode();

}
