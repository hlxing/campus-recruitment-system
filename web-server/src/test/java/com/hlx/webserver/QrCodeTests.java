package com.hlx.webserver;

import com.hlx.webserver.util.QrCodeUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: hlx 2018-08-21
 **/
public class QrCodeTests {

    @Test
    public void buildTest() {
        QrCodeUtil.encodeQrCode("http://qm.qq.com/cgi-bin/qm/qr?k=DLoX9fOjPzoPKy---zTIwNtu9rhHCzOd\n");
    }

    @Test
    public void merge() throws Exception {
        BufferedImage b1 = ImageIO.read(new File("captcha.jpg"));
        BufferedImage b2 = ImageIO.read(new File("qrCode.jpg"));
        BufferedImage newImage = new BufferedImage(340, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(b1, null, 0, 0);
        g.drawImage(b2, null, 0, 50);
        g.dispose();
        File out = new File("xCaptcha.jpg");
        ImageIO.write(newImage, "jpg", out);
    }
}
