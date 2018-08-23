package com.hlx.webserver.model.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 登录传输对象
 * @author: hlx 2018-08-23
 **/
@Api(description = "用户对象")
@Data
public class LoginDTO implements Serializable{

    @ApiModelProperty(required = true, value = "用户名", example = "hlx")
    private String name;

    @ApiModelProperty(required = true, value = "密码", example = "123456")
    private String password;

    public LoginDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginDTO() {
    }
}
