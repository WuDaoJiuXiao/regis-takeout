package com.jiuxiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuxiao.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车数据层接口
 * @Author: 悟道九霄
 * @Date: 2022/08/11 10:13
 * @Version: 1.0.0
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
