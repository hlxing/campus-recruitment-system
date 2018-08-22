package com.hlx.webserver.constant;


/**
 * @description: 用户合法性结果,检测用户的通用响应类
 * @author: hlx 2018-08-22
 **/
public enum UserValidation {

    // 成功
    SUCCESS(0,""),

    // 用户名已经存在
    NAME_EXIST(130,"NAME_EXIST"),

    // 用户名非法
    NAME_INVALID(131,"NAME_INVALID"),

    // 邮箱已经存在
    EMAIL_EXIST(132,"EMAIL_EXIST"),

    // 邮箱非法
    EMAIL_INVALID(133,"EMAIL_INVALID"),

    // 邮箱验证码错误
    EMAIL_CAPTCHA_INVALID(134,"EMAIL_CAPTCHA_INVALID"),

    // 邮箱验证码无效(过期/尝试次数过多)
    EMAIL_CAPTCHA_EXPIRE(135,"EMAIL_CAPTCHA_EXPIRE"),

    // 密码非法
    PASSWORD_INVALID(136,"PASSWORD_INVALID");

    // 响应码
    private final int code;

    // 提示信息
    private final String msg;

    UserValidation(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
