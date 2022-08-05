package com.jiuxiao.exception;

/**
 * 自定义业务异常类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 20:25
 * @Version: 1.0.0
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}