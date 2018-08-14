package com.hlx.webserver.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Jasypt配置类,加密配置文件
 * @author: hlx 2018-08-14
 **/
@Configuration
public class JasyptConfig {

    @Bean(name="encryptablePropertyResolver")
    EncryptablePropertyResolver encryptablePropertyResolver() {
        return new MyEncryptablePropertyResolver();
    }

    class MyEncryptablePropertyResolver implements EncryptablePropertyResolver {

        private final PooledPBEStringEncryptor encryptor;

        private final String password = "e10adc3949ba59abbe56e057f20f883e";

        MyEncryptablePropertyResolver() {
            this.encryptor = new PooledPBEStringEncryptor();
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

        //根据属性值选择是否解密
        @Override
        public String resolvePropertyValue(String value) {
            if (value != null && value.startsWith("JAS@")) {
                return encryptor.decrypt(value.substring("JAS@".length()));
            }
            return value;
        }

    }

}
