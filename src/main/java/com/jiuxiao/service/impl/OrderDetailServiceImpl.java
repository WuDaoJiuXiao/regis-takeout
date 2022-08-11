package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.mapper.OrderDetailMapper;
import com.jiuxiao.pojo.OrderDetail;
import com.jiuxiao.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * 订单明细业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 15:20
 * @Version: 1.0.0
 */
@Service("OrderDetailService")
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}