package com.hlx.webserver.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 注册请求对象
 * @author: hlx 2018-08-23
 **/
@ApiModel(description = "注册请求对象")
@Data
public class UserRegisterDTO implements Serializable{

    // 3-12位,大小写字母,数字及汉字组合
    @ApiModelProperty(required = true, value = "用户名", example = "hlx")
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{3,12}$")
    private String name;

    @ApiModelProperty(required = true, value = "邮箱", example = "603773962@qq.com")
    @Email
    private String email;

    // 6-12位,大小写字母及数字组合
    @ApiModelProperty(required = true, value = "密码", example = "123456")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$")
    private String password;

    @Size(min = 4, max = 4)
    @ApiModelProperty(required = true, value = "邮箱验证码", example = "1Y2S")
    private String emailCaptcha;

    public UserRegisterDTO(String name, String email, String password, String emailCaptcha) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.emailCaptcha = emailCaptcha;
    }

    public UserRegisterDTO() {
    }
}
