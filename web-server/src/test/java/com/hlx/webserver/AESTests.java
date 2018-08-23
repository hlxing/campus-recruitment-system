package com.hlx.webserver;

import com.hlx.webserver.util.AESUtil;
import org.junit.Test;

/**
 * @description: AES对称加密测试
 * @author: hlx 2018-08-23
 **/
public class AESTests {

    private static final String password = "031VG0DIH75CZ3TS";

    @Test
    public void encrypt() {
        String str = "123456";
        System.out.println("text->>" + str);
        System.out.println("password->>" + password);
        System.out.println(AESUtil.encrypt(str,password));
    }

    @Test
    public void decrypt() {
        String str = "DZPuhAiCzgA8A/m2BaYGGQ==";
        System.out.println("text->>" + str);
        System.out.println("password->>" + password);
        System.out.println(AESUtil.decrypt(str,password));
    }
}
