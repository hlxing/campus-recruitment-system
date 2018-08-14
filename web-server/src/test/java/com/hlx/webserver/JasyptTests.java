package com.hlx.webserver;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.Test;

/**
 * @description: Jasypt加密以及解密测试
 * @author: hlx 2018-08-14
 **/
public class JasyptTests {

    private static final String password = "e10adc3949ba59abbe56e057f20f883e";

    private static final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

    static{
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
    }

    @Test
    public void encrypt() {
        String str = "123456";
        System.out.println("EncryptedStr->>"+encryptor.encrypt(str));
    }

    @Test
    public void decrypt(){
        String str = "hJpoKU+BKJqxwfQuEuy0Yg==";
        System.out.println("DecryptedStr->>"+encryptor.decrypt(str));
    }

}
