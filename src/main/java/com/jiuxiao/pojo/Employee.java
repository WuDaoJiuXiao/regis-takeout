package com.jiuxiao.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月01日 9:28
 * @Version: 1.0.0
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 员工 ID */
    private Long id;

    /** 员工账户名称，具有唯一性约束 */
    private String username;

    /** 员工姓名 */
    private String name;

    /** 员工登录密码 */
    private String password;

    /** 员工电话号码 */
    private String phone;

    /** 员工性别 */
    private String sex;

    /** 员工工号 */
    private String idNumber;

    /** 员工账号状态 1：正常 2：封禁 */
    private Integer status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /** 修改人 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}