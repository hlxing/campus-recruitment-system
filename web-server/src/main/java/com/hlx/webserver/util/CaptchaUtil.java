package com.hlx.webserver.util;

import com.hlx.webserver.model.Captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @description: 验证码生成工具
 * @author: hlx 2018-08-15
 **/
public class CaptchaUtil {

    //噪声序列
    private static char[] noiseSeq = {'A','B','C','D','E', '1','2','3','4','5','ψ', 'η', 'λ',
            'た','ά','気','か','い','は','す'};
    //验证字符序列
    private static char[] targetSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};

    private static Font noiseFont = new Font("微软雅黑", Font.BOLD, 20);

    private static Font targetFont = new Font("微软雅黑", Font.BOLD, 70);

    //验证码生成
    public static Captcha newInstance() {
        BufferedImage image = new BufferedImage(340,100,BufferedImage.TYPE_INT_BGR);
        Random random = new Random();
        //获取画笔
        Graphics2D g = image.createGraphics();
        //白色背景渲染->>
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 340, 100);
        //噪音渲染->>
        g.setFont(noiseFont);
        //顺时针旋转XY轴45度
        g.rotate(Math.toRadians(45));
        Color randomColor = new Color(
                random.nextInt(255),random.nextInt(255),random.nextInt(255));
        g.setColor(randomColor);
        int x = 18,y = 5,cnt = 0;
        for(int i=0;i<54;i++) {
            String randomStr = String.valueOf(
                    noiseSeq[random.nextInt(noiseSeq.length)]);
            g.drawString(randomStr,x+20*cnt,y);
            cnt ++;
            //颜色变亮
            randomColor = randomColor.brighter();
            g.setColor(randomColor);
            if ((i + 1) % 6 == 0) {
                x = x + 21;
                y = y - 20;
                cnt = 0;
                randomColor = new Color(
                        random.nextInt(255),random.nextInt(255),random.nextInt(255));
                g.setColor(randomColor);
            }
        }
        //验证字符渲染->>
        StringBuilder builder = new StringBuilder("");
        g.setFont(targetFont);
        g.rotate(Math.toRadians(-45));
        //随机X坐标
        int randomX = random.nextInt(340 - 90);
        int randomX2 = randomX + 45 + random.nextInt(340 - randomX - 45 - 45);
        int[] randomXSeq = {randomX, randomX2};
        for (int i=0;i<2;i++) {
            String randomStr = String.valueOf(
                    targetSeq[random.nextInt(targetSeq.length)]);
            builder.append(randomStr);
            randomColor = new Color(
                    random.nextInt(255),random.nextInt(255),random.nextInt(255));
            g.setColor(randomColor);
            g.drawString(randomStr, randomXSeq[i], 75);
        }
        return new Captcha(image, builder.toString());
    }

}
