package com.jiuxiao.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 10:06
 * @Version: 1.0.0
 */
@Data
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 购物车 ID */
    private Long id;

    /** 套餐或者菜品名称 */
    private String name;

    /** 套餐或者菜品图片 */
    private String image;

    /** 当前用户 ID */
    private Long userId;

    /** 下单的菜品 ID */
    private Long dishId;

    /** 下单的套餐 ID */
    private Long setmealId;

    /** 下单菜品的口味 */
    private String dishFlavor;

    /** 下单菜品或者套餐的数量 */
    private Integer number;

    /** 下单菜品或者套餐的金额 */
    private BigDecimal amount;

    /** 创建时间 */
    private LocalDateTime createTime;
}