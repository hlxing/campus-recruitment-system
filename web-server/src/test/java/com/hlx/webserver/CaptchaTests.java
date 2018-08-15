package com.hlx.webserver;

import com.hlx.webserver.model.Captcha;
import com.hlx.webserver.util.CaptchaUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @description: 验证码生成测试
 * @author: hlx 2018-08-15
 **/
public class CaptchaTests {

    @Test
    public void buildImage() {
        Captcha captcha = CaptchaUtil.newInstance();
        System.out.println("result->>" + captcha.getResult());
        File out = new File("captcha.jpg");
        try {
            ImageIO.write(captcha.getBufferedImage(), "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
