package com.jiuxiao.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分类实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 11:16
 * @Version: 1.0.0
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 分类 id */
    private Long id;

    /* 1：菜品分类   2：套餐分类 */
    private Integer type;

    /* 分类名称，具有唯一约束 */
    private String name;

    /* 分类顺序 */
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
}