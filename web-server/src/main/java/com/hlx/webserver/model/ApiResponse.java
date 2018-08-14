package com.hlx.webserver.model;

import lombok.Data;

/**
 * @description: 通用响应实体
 * @author: hlx 2018-08-14
 **/
@Data
public class ApiResponse<T> {

	private int status;

	private String text;

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
