package com.hlx.webserver.model.po;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @description: 验证码实体
 * @author: hlx 2018-08-15
 **/
@Data
public class Captcha {

    private BufferedImage bufferedImage;

    private String result;

    public Captcha(BufferedImage bufferedImage, String result) {
        this.bufferedImage = bufferedImage;
        this.result = result;
    }

    public Captcha() {
    }
}
