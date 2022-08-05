package com.jiuxiao.tools;

/**
 * 获取当前登录用户的 Id 工具类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 10:49
 * @Version: 1.0.0
 */
public class BaseContext {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * @param id
     * @return: void
     * @decription 设置ID
     * @date 2022/8/5 10:53
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * @return: java.lang.Long
     * @decription 获取ID
     * @date 2022/8/5 10:53
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}