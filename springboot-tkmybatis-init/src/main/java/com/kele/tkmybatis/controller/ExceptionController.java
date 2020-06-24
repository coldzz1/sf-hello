package com.kele.tkmybatis.controller;


import com.kele.tkmybatis.NotAuthException;
import com.kele.tkmybatis.common.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {


    @GetMapping("testException")
    public Object testException(String id){
        if(id.equals("1"))
            return new NotAuthException("认证失败");
        return BaseResponse.succ();
    }
}
