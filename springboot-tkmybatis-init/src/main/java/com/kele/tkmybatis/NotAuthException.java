package com.kele.tkmybatis;

public class NotAuthException extends RuntimeException {
    public NotAuthException(String msg){
        super(msg);
    }
}
