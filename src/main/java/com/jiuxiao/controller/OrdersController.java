package com.jiuxiao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.pojo.Orders;
import com.jiuxiao.service.OrdersService;
import com.jiuxiao.tools.BaseContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 15:22
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    /**
     * @param orders
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 用户下单
     * @date 2022/8/11 15:30
     */
    @PostMapping("/submit")
    public RespBean<String> submit(@RequestBody Orders orders) {
        ordersService.submit(orders);
        return RespBean.success(SysConstant.ORDER_SUBMIT_SUCCESS);
    }

    /**
     * @param page
     * @param pageSize
     * @return: com.jiuxiao.commons.RespBean<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     * @decription 移动端订单分页查询
     * @date 2022/8/11 17:01
     */
    @GetMapping("/userPage")
    public RespBean<Page> page(int page, int pageSize) {
        Page<Orders> ordersPage = new Page<>(page, pageSize);
        Long userId = BaseContext.getCurrentId();

        return null;
    }
}