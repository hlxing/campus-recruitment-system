package com.hlx.webserver.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @description:
 * @author: hlx 2018-08-21
 **/
public class QrCodeUtil {

    public static void encodeQrCode(String content) {
        try {
            int width = 50; // 二维码图片宽度
            int height = 50; // 二维码图片高度
            String format = "png";// 二维码的图片格式

            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
            hints.put(EncodeHintType.MARGIN, "2");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 生成二维码
            Path path = Paths.get("", "qrCode.jpg");
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);
            System.out.println("success:-------------------生成二维码成功");
        } catch (Exception e) {
            System.out.println("error:-------------------生成二维码失败" + e.getMessage());
        }
    }

    public static String decodeQrCode(String qrCodeUrl) {
        String retStr = "";
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(qrCodeUrl));
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap bitmap = new BinaryBitmap(binarizer);
            HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
            hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
            retStr = result.getText();
            System.out.println("success:-------------------二维码解析成功");
        } catch (Exception e) {
            System.out.println("error:-------------------二维码解析失败" + e.getMessage());
        }
        return retStr;
    }
}
