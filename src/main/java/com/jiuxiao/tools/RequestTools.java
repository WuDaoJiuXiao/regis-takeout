package com.jiuxiao.tools;

import org.springframework.util.AntPathMatcher;

/**
 * 请求路径匹配工具类
 * @Author: 悟道九霄
 * @Date: 2022年08月01日 15:04
 * @Version: 1.0.0
 */
public class RequestTools {

    //路径匹配器，支持通配符
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * @param notChecks
     * @param requestUri
     * @return: boolean
     * @decription 检查请求的路径是否需要拦截
     * @date 2022/8/1 15:08
     */
    public static boolean checkUriFilter(String[] notChecks, String requestUri) {
        for (String uri : notChecks) {
            if (ANT_PATH_MATCHER.match(uri, requestUri)) {
                return true;
            }
        }
        return false;
    }
}