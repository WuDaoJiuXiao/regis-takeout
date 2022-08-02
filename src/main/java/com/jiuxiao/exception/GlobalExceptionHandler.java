package com.jiuxiao.exception;

import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常捕获器
 * @Author: 悟道九霄
 * @Date: 2022年08月02日 10:24
 * @Version: 1.0.0
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * @param e
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription SQL 异常处理
     * @date 2022/8/2 10:47
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public RespBean<String> exceptionHandler(SQLIntegrityConstraintViolationException e) {
        String message = e.getMessage();

        if (message.contains("Duplicate entry")) {
            String[] s = message.split(" ");
            String resMsg = "账号 " + s[2].substring(1, s[2].length() - 1) + " 已存在!";
            return RespBean.error(resMsg);
        }
        return RespBean.error(SysConstant.UNKNOWN_ERROR);
    }
}