package com.hlx.pagesserver.controller;

import com.hlx.pagesserver.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connect")
public class ConnectController {

	private static final Logger logger = LoggerFactory.getLogger(ConnectController.class);

	@RequestMapping(value="/test",method= RequestMethod.GET)
	public ApiResponse<String> test(){
		ApiResponse<String> apiResponse = new ApiResponse<>();
		apiResponse.setText("Connect Success");
		logger.info("测试连接成功!");
		return apiResponse;
	}
}