package com.jiuxiao.commons;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果类
 * @Author: 悟道九霄
 * @Date: 2022年08月01日 9:51
 * @Version: 1.0.0
 */
@Data
public class RespBean<T> {

    private Integer code;

    private String msg;

    private T data;

    private Map map = new HashMap<>();

    public static <T> RespBean<T> success(T object) {
        RespBean<T> respBean = new RespBean<>();
        respBean.data = object;
        respBean.code = 1;
        return respBean;
    }

    public static <T> RespBean<T> error(String msg){
        RespBean respBean = new RespBean();
        respBean.msg = msg;
        respBean.code = 0;
        return respBean;
    }

    public RespBean<T> add(String key, Object value){
        this.map.put(key, value);
        return this;
    }
}