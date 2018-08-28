package com.hlx.webserver.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * @description: 登录请求对象
 * @author: hlx 2018-08-23
 **/
@ApiModel(description = "登录请求对象")
@Data
public class UserLoginDTO implements Serializable{

    @ApiModelProperty(required = true, value = "用户名", example = "hlx")
    private String name;

    @ApiModelProperty(required = true, value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(required = true, value = "验证码", example = "AC")
    private String captcha;

    public UserLoginDTO(String name, String password, String captcha) {
        this.name = name;
        this.password = password;
        this.captcha = captcha;
    }

    public UserLoginDTO() {
    }
}
