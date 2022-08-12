package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.exception.CustomException;
import com.jiuxiao.mapper.OrdersMapper;
import com.jiuxiao.pojo.*;
import com.jiuxiao.service.*;
import com.jiuxiao.tools.BaseContext;
import com.jiuxiao.tools.StringTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 订单业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 15:18
 * @Version: 1.0.0
 */
@Service("OrdersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Resource
    private ShoppingCartService shoppingCartService;

    @Resource
    private UserService userService;

    @Resource
    private AddressBookService addressBookService;

    @Resource
    private OrderDetailService orderDetailService;

    /**
     * @param orders
     * @return: void
     * @decription 用户下单
     * @date 2022/8/11 15:31
     */
    @Override
    @Transactional
    public void submit(Orders orders) {
        //获得当前用户的 ID
        Long userId = BaseContext.getCurrentId();

        //根据用户 ID 查询出对应的购物车数据
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(queryWrapper);

        //购物车为空时不能下单
        if (shoppingCarts == null || shoppingCarts.size() == 0) {
            throw new CustomException(SysConstant.SHOPPING_CART_EMPTY_DO_NOT_ORDERS);
        }

        //处理购物车的数据，计算总金额、设置订单明细数据
        long orderId = IdWorker.getId();
        AtomicInteger amount = new AtomicInteger(0);
        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        //查询用户信息、用户对应的地址信息
        User user = userService.getById(userId);
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);

        //地址为空时，不能下单
        if (addressBook == null) {
            throw new CustomException(SysConstant.ADDRESS_BOOK_EMPTY_DO_NOT_ORDERS);
        }

        //向 orders 表设置相关数据
        orders.setId(orderId);
        orders.setNumber(StringTools.getOrderNumber());
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserId(userId);
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress(
                (addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                        + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                        + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                        + (addressBook.getDetail() == null ? "" : addressBook.getDetail())
        );
        //向 orders 表插入数据（一条）
        this.save(orders);

        //向 order_detail 表插入数据（可能多条）
        orderDetailService.saveBatch(orderDetails);

        //清空购车数据
        shoppingCartService.remove(queryWrapper);
    }
}