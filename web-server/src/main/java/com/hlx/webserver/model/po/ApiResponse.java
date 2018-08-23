package com.hlx.webserver.model.po;

import lombok.Data;

/**
 * @description: 通用响应实体
 * @author: hlx 2018-08-14
 **/
@Data
public class ApiResponse<T> {

	// 响应状态
	private int status;

	// 提示信息
	private String text;

	// 响应体
	private T data;
	
	public ApiResponse(int status, String text, T data) {
		super();
		this.status = status;
		this.text = text;
		this.data = data;
	}
	public ApiResponse(){
		
	}

}
