package com.hlx.webserver.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlx.webserver.constant.ApiError;
import lombok.Getter;

/**
 * @description: 自定义api异常,参数校验或业务处理时所抛出的异常
 * @author: hlx 2018-08-28
 **/
@Getter
public class ApiException extends RuntimeException {

    private static ObjectMapper mapper = new ObjectMapper();

    private Integer code;

    /**
     * 构造api异常
     * @param apiError 实现api错误接口的错误枚举类
     */
    public ApiException(ApiError apiError) {
        super(apiError.getMsg());
        this.code = apiError.getCode();
    }

}
