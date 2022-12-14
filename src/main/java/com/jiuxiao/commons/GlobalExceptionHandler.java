package com.jiuxiao.commons;

import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
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
            String resMsg = s[2].substring(1, s[2].length() - 1) + " 已存在!";
            return RespBean.error(resMsg);
        }
        return RespBean.error(SysConstant.UNKNOWN_ERROR);
    }

    /**
     * @param e
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 自定义异常处理
     * @date 2022/8/5 20:30
     */
    @ExceptionHandler(CustomException.class)
    public RespBean<String> exceptionHandler(CustomException e) {
        return RespBean.error(e.getMessage());
    }

    /**
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 找不菜品图片异常处理
     * @date 2022/8/7 10:30
     */
    @ExceptionHandler(FileNotFoundException.class)
    public RespBean<String> exceptionHandler(FileNotFoundException e) {
        String message = e.getMessage();

        //若找不到菜品图片，显示找不到相关图片
        if (message.contains("FileNotFoundException") && message.contains(".jpeg")){
            return RespBean.error(SysConstant.NOT_FOUND_IMAGE);
        }

        return RespBean.error(SysConstant.UNKNOWN_ERROR);
    }
}