package com.hlx.webserver.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 注册传输对象
 * @author: hlx 2018-08-23
 **/
@ApiModel(description = "注册业务传输对象")
@Data
public class RegisterDTO implements Serializable{

    @ApiModelProperty(required = true, value = "用户名", example = "hlx")
    private String name;

    @ApiModelProperty(required = true, value = "邮箱", example = "603773962@qq.com")
    private String email;

    @ApiModelProperty(required = true, value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(required = true, value = "邮箱验证码", example = "1Y2S")
    private String emailCaptcha;

    public RegisterDTO(String name, String email, String password, String emailCaptcha) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.emailCaptcha = emailCaptcha;
    }

    public RegisterDTO() {
    }
}
