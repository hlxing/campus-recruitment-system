package com.hlx.webserver.exception;

import com.hlx.webserver.model.po.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @description: 全局异常处理器
 * @author: hlx 2018-08-20
 **/
@ControllerAdvice
public class ApiExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionResolver.class);

    // Exception异常处理
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<String> resolveException(Exception ex) {
        ApiResponse<String> apiResponse = new ApiResponse<>(404, "未知错误", null);
        apiResponse.setText(ex.toString());
        logger.error("捕获异常:"+ex.getMessage());
        ex.printStackTrace();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        // 获取较为详尽的异常信息,异常类:异常信息
        String[] exception = baos.toString().split("\n");
        logger.error("ex[0]->>"+exception[0]);
        // 紧急事件,邮件通知
        //mailService.sendMail("603773962@qq.com",exception[1]);
        return apiResponse;
    }



}
