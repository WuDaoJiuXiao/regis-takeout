package com.jiuxiao.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 15:00
 * @Version: 1.0.0
 */
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 明细 ID */
    private Long id;

    /** 菜品/套餐名称 */
    private String name;

    /** 菜品/套餐配图 */
    private String image;

    /** 订单 ID */
    private Long orderId;

    /** 菜品 ID */
    private Long dishId;

    /** 套餐 ID */
    private Long setmealId;

    /** 菜品口味风格 */
    private String dishFlavor;

    /** 菜品/套餐数量 */
    private Integer number;

    /** 总金额 */
    private BigDecimal amount;
}