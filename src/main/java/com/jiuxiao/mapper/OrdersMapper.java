package com.jiuxiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuxiao.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单数据层接口
 * @Author: 悟道九霄
 * @Date: 2022/08/11 15:16
 * @Version: 1.0.0
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
