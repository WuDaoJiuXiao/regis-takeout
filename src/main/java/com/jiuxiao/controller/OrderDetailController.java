package com.jiuxiao.controller;

import com.jiuxiao.service.OrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单明细控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 15:23
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Resource
    private OrderDetailService orderDetailService;
}