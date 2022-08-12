package com.jiuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.dto.OrderDto;
import com.jiuxiao.exception.CustomException;
import com.jiuxiao.pojo.OrderDetail;
import com.jiuxiao.pojo.Orders;
import com.jiuxiao.service.OrderDetailService;
import com.jiuxiao.service.OrdersService;
import com.jiuxiao.tools.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private OrderDetailService orderDetailService;

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
        Page<OrderDto> ordersPage = new Page<>(page, pageSize);
        Long userId = BaseContext.getCurrentId();

        //根据用户 ID 查询出该用户的订单信息
        LambdaQueryWrapper<Orders> ordersQueryWrapper = new LambdaQueryWrapper<>();
        ordersQueryWrapper.eq(Orders::getUserId, userId);
        List<Orders> ordersList = ordersService.list(ordersQueryWrapper);

        List<OrderDto> orderDtoList = ordersList.stream().map((order -> {
            LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(OrderDetail::getOrderId, order.getId());
            List<OrderDetail> detailList = orderDetailService.list(queryWrapper);

            if (detailList == null || detailList.size() == 0) {
                throw new CustomException(SysConstant.USER_NOT_ORDER);
            }

            //计算菜品 + 套餐总数，以及各自菜品对应的数量关系
            Integer sumNum = 0;
            HashMap<String, Integer> map = new HashMap<>();
            for (OrderDetail detail : detailList) {
                sumNum += detail.getNumber();
                map.put(detail.getName(), detail.getNumber());
            }

            //将元素换成 orderDto，拷贝并设置信息
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order, orderDto);

            orderDto.setOrderDetails(map);
            orderDto.setSumNum(sumNum);
            return orderDto;
        })).collect(Collectors.toList());

        ordersPage.setRecords(orderDtoList);

        return RespBean.success(ordersPage);
    }

    /**
     * @param response
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.pojo.ShoppingCart>
     * @decription 再来一单
     * @date 2022/8/12 10:15
     */
    @PostMapping("/again")
    public RespBean<String> again(HttpServletResponse response) throws IOException {
        response.sendRedirect("/dish/list");
        return null;
    }
}