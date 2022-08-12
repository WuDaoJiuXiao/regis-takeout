package com.jiuxiao.dto;

import com.jiuxiao.pojo.Orders;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * 订单数据传输类
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 20:51
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends Orders {

    /** 菜品/套餐的键值对(名称:数量) */
    private HashMap<String, Integer> orderDetails;

    /** 总共的数量(菜品 + 套餐) */
    private Integer sumNum;
}