package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.dto.DishDto;
import com.jiuxiao.mapper.DishMapper;
import com.jiuxiao.pojo.Dish;
import com.jiuxiao.pojo.DishFlavor;
import com.jiuxiao.service.DishFlavorService;
import com.jiuxiao.service.DishService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 20:01
 * @Version: 1.0.0
 */
@Service("DishService")
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Resource
    private DishFlavorService dishFlavorService;

    /**
     * @param dishDto
     * @return: void
     * @decription 新增菜品，同时添加风味信息
     * @date 2022/8/6 15:51
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品信息到 dish 表
        this.save(dishDto);

        //设置菜品口味
        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().peek((item) -> item.setDishId(dishId)).collect(Collectors.toList());

        //存储数据到 dish_flavor 表中
        dishFlavorService.saveBatch(flavors);
    }
}