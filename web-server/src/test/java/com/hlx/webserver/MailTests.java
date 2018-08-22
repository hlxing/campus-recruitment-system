package com.hlx.webserver;

import com.hlx.webserver.constant.EmailTemplate;
import com.hlx.webserver.service.impl.EmailServiceImpl;
import com.hlx.webserver.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: 邮箱测试
 * @author: hlx 2018-08-22
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTests {

    @Autowired
    private EmailServiceImpl emailService;

    @Test
    public void send() {
        String key = RandomUtil.build(4);
        String text = EmailTemplate.SIMPLE.replaceAll("#emailCaptcha",key);
        emailService.sendMessage("603773962@qq.com", "欢迎来到【校招系统】，请验证您的邮箱", text);
    }

}
