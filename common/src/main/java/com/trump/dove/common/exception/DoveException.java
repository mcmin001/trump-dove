package com.trump.dove.common.exception;

public class DoveException extends RuntimeException{
    public DoveException(){
        super();
    }
    public DoveException(String message, Throwable cause){
        super(message, cause);
    }

    public DoveException(String message){
        super(message);
    }
}
