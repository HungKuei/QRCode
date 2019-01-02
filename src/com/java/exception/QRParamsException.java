package com.java.exception;


public class QRParamsException extends Exception {
	private static final long serialVersionUID = 8837582301762730656L;
	//用来创建无参数对象
	public QRParamsException()  {}
	//用来创建指定参数对象
    public QRParamsException(String message) {
    	//调用超类构造器
        super(message);
    }
}
