package com.jiuxiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuxiao.pojo.Orders;
import org.springframework.stereotype.Service;

/**
 * 订单与业务层接口
 * @Author: 悟道九霄
 * @Date: 2022/08/11 15:17
 * @Version: 1.0.0
 */
@Service
public interface OrdersService extends IService<Orders> {

    /**
     * @param orders
     * @return: void
     * @decription 用户下单
     * @date 2022/8/11 15:31
     */
    void submit(Orders orders);
}
