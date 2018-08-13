package com.hlx.pagesserver.model;

import lombok.Data;

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
