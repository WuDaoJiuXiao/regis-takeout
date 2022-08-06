package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.mapper.DishFlavorMapper;
import com.jiuxiao.pojo.DishFlavor;
import com.jiuxiao.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * 风味业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月06日 14:39
 * @Version: 1.0.0
 */
@Service("DishFlavorService")
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

}