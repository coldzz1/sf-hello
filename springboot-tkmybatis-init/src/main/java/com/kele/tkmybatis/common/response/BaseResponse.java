package com.kele.tkmybatis.common.response;

public class BaseResponse {
    String code;
    String msg;
    Object data;


    public static BaseResponse succ(){
        BaseResponse response=new BaseResponse();
        response.setCode("0000");
        response.setMsg("操作成功");
        return response;
    }

    public static BaseResponse fail(String msg,String code){
        BaseResponse response=new BaseResponse();
        response.setCode("0002");
        response.setMsg(msg);
        return response;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
