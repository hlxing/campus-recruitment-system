package com.hlx.webserver;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @description: Digest加密以及解密测试
 * @author: hlx 2018-08-19
 **/
public class DigestTests {

    //SHA1 安全哈希算法加密测试
    @Test
    public void encrypt() {
        String str = "123456";
        System.out.println("EncryptedStr->>" + DigestUtils.sha1Hex(str));
    }

}
