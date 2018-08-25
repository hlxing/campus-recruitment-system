package com.hlx.webserver.model.dto.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 登录请求对象
 * @author: hlx 2018-08-23
 **/
@Api(description = "登录请求对象")
@Data
public class LoginReqDTO implements Serializable{

    @ApiModelProperty(required = true, value = "用户名", example = "hlx")
    private String name;

    @ApiModelProperty(required = true, value = "密码", example = "123456")
    private String password;

    public LoginReqDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginReqDTO() {
    }
}
