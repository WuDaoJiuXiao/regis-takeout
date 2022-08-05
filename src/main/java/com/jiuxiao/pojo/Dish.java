package com.jiuxiao.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 19:44
 * @Version: 1.0.0
 */
@Data
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 菜品 ID */
    private Long id;

    /** 菜品名称 */
    private String name;

    /** 菜品所属分类的 ID */
    private Long categoryId;

    /** 菜品价格 */
    private BigDecimal price;

    /** 商品码 */
    private String code;

    /** 菜品配图 */
    private String image;

    /** 菜品描述 */
    private String description;

    /** 菜品状态 0:禁用 1:启用 */
    private Integer status;

    /** 菜品排序 */
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

    /** 菜品是否被删除 */
    private Integer isDeleted;
}