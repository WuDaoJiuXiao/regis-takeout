package com.jiuxiao.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜品风味实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月06日 14:34
 * @Version: 1.0.0
 */
@Data
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 风味 ID */
    private Long id;

    /** 风味对应的菜品 ID */
    private Long dishId;

    /** 风味名称 */
    private String name;

    /** 风味的值 */
    private String value;

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

    /** 是否被删除 */
    private Integer isDeleted;
}