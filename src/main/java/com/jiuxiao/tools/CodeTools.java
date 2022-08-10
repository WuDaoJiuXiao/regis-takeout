package com.jiuxiao.tools;

import com.jiuxiao.constants.SysConstant;

import java.util.Random;

/**
 * 验证码工具类
 * @Author: 悟道九霄
 * @Date: 2022年08月10日 10:12
 * @Version: 1.0.0
 */
public class CodeTools {

    /**
     * @param length
     * @return: java.lang.Integer
     * @decription 随机生成 4 位或者 6 位验证码
     * @date 2022/8/10 10:14
     */
    public static Integer generateValidateCode(int length) {
        Integer code = null;

        if (length == 4) {
            code = new Random().nextInt(9999);
            if (code < 1000) {
                code += 1000;
            }
        } else if (length == 6) {
            code = new Random().nextInt(999999);
            if (code < 100000) {
                code += 100000;
            }
        } else {
            throw new RuntimeException(SysConstant.ONLY_FOUR_OR_SIX_CODE);
        }
        return code;
    }

    /**
     * @param length
     * @return: java.lang.String
     * @decription 随机生成指定长度的字符串验证码
     * @date 2022/8/10 10:19
     */
    public static String generateValidateCode4String(int length) {
        Random random = new Random();
        String hexString = Integer.toHexString(random.nextInt());
        return hexString.substring(0, length);
    }
}