package com.hlx.webserver.constant;


/**
 * @description: 用户错误枚举
 * @author: hlx 2018-08-28
 **/
public enum UserErrorEnum implements ApiError {

    // 参数错误
    PARAM_INVALID(13,"PARAM_INVALID"),

    // 用户名已经存在
    NAME_EXIST(130,"NAME_EXIST"),

    // 密码错误
    PASSWORD_INVALID(131, "PASSWORD_INVALID"),

    // 邮箱已经存在
    EMAIL_EXIST(230,"EMAIL_EXIST"),

    // 邮箱验证码错误
    EMAIL_CAPTCHA_INVALID(231,"EMAIL_CAPTCHA_INVALID"),

    // 邮箱验证码无效(过期/尝试次数过多)
    EMAIL_CAPTCHA_EXPIRE(232,"EMAIL_CAPTCHA_EXPIRE"),

    // 获取邮箱验证码频繁
    EMAIL_CAPTCHA_BUSY(233,"EMAIL_CAPTCHA_BUSY"),

    // 验证码非法
    CAPTCHA_INVALID(330,"CAPTCHA_INVALID");

    // 响应码
    private final int code;

    // 提示信息
    private final String message;

    UserErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMsg() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
