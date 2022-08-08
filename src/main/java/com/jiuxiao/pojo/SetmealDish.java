package com.jiuxiao.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐菜品关系实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月07日 14:49
 * @Version: 1.0.0
 */
@Data
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 套餐菜品关系 ID */
    private Long id;

    /** 套餐 ID */
    private Long setmealId;

    /** 菜品 ID */
    private Long dishId;

    /** 套餐名称 */
    private String name;

    /** 套餐价格 */
    private BigDecimal price;

    /** 菜品份数 */
    private Integer copies;

    /** 排序 */
    private Integer sort;

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