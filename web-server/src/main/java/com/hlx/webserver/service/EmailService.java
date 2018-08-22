package com.hlx.webserver.service;

/**
 * @description: 电子邮件服务
 * @author: hlx 2018-08-22
 **/
public interface EmailService {

    // 简单纯文字
    void sendSimpleMessage(String to, String subject, String text);

    // 富文本(html)
    void sendMessage(String to, String subject, String text);
}
