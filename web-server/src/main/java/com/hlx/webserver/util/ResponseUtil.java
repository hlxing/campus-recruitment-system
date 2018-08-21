package com.hlx.webserver.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 响应输出工具
 * @author: hlx 2018-08-15
 **/
public class ResponseUtil {
	
	public static void write(HttpServletResponse response, String str){
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert out != null;
		out.println(str);
		out.flush();
		out.close();
	}

}
