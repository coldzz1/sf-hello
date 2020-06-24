package com.kele.tkmybatis.common.advice;

import com.kele.tkmybatis.NotAuthException;
import com.kele.tkmybatis.common.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExeceptionHanderAdvice {

    Logger logger=LoggerFactory.getLogger(ExeceptionHanderAdvice.class);

    @ExceptionHandler(NotAuthException.class)
    public BaseResponse apiExceptionHandler(NotAuthException ex) {
        logger.error("ApiException 异常抛出：{}", ex);
        return BaseResponse.fail("未认证","失败");
    }

}
