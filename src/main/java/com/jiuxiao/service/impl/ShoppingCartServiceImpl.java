package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.mapper.ShoppingCartMapper;
import com.jiuxiao.pojo.ShoppingCart;
import com.jiuxiao.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * 购物车业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 10:14
 * @Version: 1.0.0
 */
@Service("ShoppingCartService")
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}