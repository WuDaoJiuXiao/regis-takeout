package com.jiuxiao.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月10日 9:48
 * @Version: 1.0.0
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户 ID */
    private Long id;

    /** 用户姓名 */
    private String name;

    /** 用户手机号 */
    private String phone;

    /** 用户性别 0：女 1：男 */
    private String sex;

    /** 用户 ID 序号 */
    private String idNumber;

    /** 用户头像 */
    private String avatar;

    /** 用户状态 0：封禁 1：正常 */
    private Integer status;
}