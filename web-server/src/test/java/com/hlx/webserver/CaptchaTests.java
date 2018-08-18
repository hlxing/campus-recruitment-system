package com.hlx.webserver;

import com.hlx.webserver.model.Captcha;
import com.hlx.webserver.util.CaptchaUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @description: 验证码生成测试
 * @author: hlx 2018-08-15
 **/
public class CaptchaTests {

    //垂直随机测试(打开垂直随机)
    @Test
    public void verticalRandomTest() {
        CaptchaUtil.verticalRandom = true;
        Captcha captcha = CaptchaUtil.newInstance();
        System.out.println("result->>" + captcha.getResult());
        File out = new File("captcha.jpg");
        try {
            ImageIO.write(captcha.getBufferedImage(), "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //垂直随机测试2(关闭垂直随机)
    @Test
    public void verticalRandomTest2() {
        CaptchaUtil.verticalRandom = false;
        Captcha captcha = CaptchaUtil.newInstance();
        System.out.println("result->>" + captcha.getResult());
        File out = new File("captcha.jpg");
        try {
            ImageIO.write(captcha.getBufferedImage(), "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //水平随机测试(打开水平随机)
    @Test
    public void horizontalRandomTest() {
        CaptchaUtil.horizontalRandom = true;
        Captcha captcha = CaptchaUtil.newInstance();
        System.out.println("result->>" + captcha.getResult());
        File out = new File("captcha.jpg");
        try {
            ImageIO.write(captcha.getBufferedImage(), "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //水平随机测试2(水平垂直随机)
    @Test
    public void horizontalRandomTest2() {
        CaptchaUtil.horizontalRandom = false;
        Captcha captcha = CaptchaUtil.newInstance();
        System.out.println("result->>" + captcha.getResult());
        File out = new File("captcha.jpg");
        try {
            ImageIO.write(captcha.getBufferedImage(), "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //黑白测试
    @Test
    public void blackWhiteTest() {
        Captcha captcha = CaptchaUtil.newInstance();
        System.out.println("result->>" + captcha.getResult());
        BufferedImage sourceImage = captcha.getBufferedImage();
        BufferedImage blackWhiteImage = new BufferedImage(340, 100, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = blackWhiteImage.getGraphics();
        g.drawImage(sourceImage, 0, 0, null);
        File out = new File("blackWhiteCaptcha.jpg");
        try {
            ImageIO.write(blackWhiteImage, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
