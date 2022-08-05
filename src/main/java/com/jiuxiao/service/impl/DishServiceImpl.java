package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.mapper.DishMapper;
import com.jiuxiao.pojo.Dish;
import com.jiuxiao.service.DishService;
import org.springframework.stereotype.Service;

/**
 * 菜品业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 20:01
 * @Version: 1.0.0
 */
@Service("DishService")
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

}