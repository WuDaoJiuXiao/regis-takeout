package com.jiuxiao.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 19:53
 * @Version: 1.0.0
 */
@Data
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 套餐 ID */
    private Long id;

    /** 套餐所属的分类 ID */
    private Long categoryId;

    /** 套餐价格 */
    private BigDecimal price;

    /** 套餐状态 0:禁用 1:启用 */
    private Integer status;

    /** 商品码 */
    private String code;

    /** 套餐描述 */
    private String description;

    /** 套餐配图 */
    private String image;

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

    private Integer isDeleted;
}