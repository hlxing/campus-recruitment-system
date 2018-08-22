package com.hlx.webserver.config;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @description: 邮箱配置类
 * @author: hlx 2018-08-22
 **/
@Configuration
public class MailConfig {

    /**
     * 配置腾讯企业邮箱
     */
    @Bean
    public JavaMailSender getJavaMailSender() {
        // 发送服务器设置
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.exmail.qq.com");
        mailSender.setPort(465);
        // 账号密码
        mailSender.setUsername("crs@huanglexing.com");
        mailSender.setPassword("123456Cr");
        // 协议
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        //开启SSL,企业邮箱必备
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        props.setProperty("mail.smtp.ssl.enable", "true");
        assert sf != null;
        props.put("mail.smtp.ssl.socketFactory", sf);
        mailSender.setJavaMailProperties(props);
        mailSender.setDefaultEncoding("utf-8");
        return mailSender;
    }

}
