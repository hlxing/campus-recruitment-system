package com.hlx.webserver.util;

import java.util.Random;

/**
 * @description: 随机工具类
 * @author: hlx 2018-08-22
 **/
public class RandomUtil {
	
	private static final String  VALIDCHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	// 产生随机指定长度的随机字符串
	public static String build(int len){
		StringBuilder str = new StringBuilder();
		Random random = new Random();
		for(int i=0;i<len;i++)
			str.append(VALIDCHAR.charAt(random.nextInt(VALIDCHAR.length())));
		return str.toString();
	}

}
