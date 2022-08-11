package com.jiuxiao.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 15:00
 * @Version: 1.0.0
 */
@Data
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 订单 ID */
    private Long id;

    /** 订单号 */
    private String number;

    /** 订单状态 1:待付款 2:待派送 3:已派送 4:已完成 5:已取消 */
    private Integer status;

    /** 下单用户 ID */
    private Long userId;

    /** 下单人地址 ID */
    private Long addressBookId;

    /** 下单时间 */
    private LocalDateTime orderTime;

    /** 支付时间 */
    private LocalDateTime checkoutTime;

    /** 支付方式 */
    private Integer payMethod;

    /** 总金额 */
    private BigDecimal amount;

    /** 订单备注 */
    private String remark;

    /** 下单人手机号 */
    private String phone;

    /** 下单人地址 */
    private String address;

    /** 下单人用户名 */
    private String userName;

    /** 收货人姓名 */
    private String consignee;
}