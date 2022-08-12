package com.jiuxiao.tools;

import java.time.LocalDateTime;

/**
 * 生成订单号工具类
 * @Author: 悟道九霄
 * @Date: 2022年08月12日 11:08
 * @Version: 1.0.0
 */
public class StringTools {

    /**
     * @return: java.lang.String
     * @decription 随机成功订单号
     * @date 2022/8/12 11:15
     */
    public static String getOrderNumber() {
        String res = "PTG" + LocalDateTime.now().toString();
        res = res.replace("-", "").replace(":", "").replace(".", "");
        res = res.substring(0, 11) + res.substring(12, res.length() - 3) + (int)(((Math.random()*9)+1)*100);
        return res;
    }
}