package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.dto.DishDto;
import com.jiuxiao.mapper.DishMapper;
import com.jiuxiao.pojo.Dish;
import com.jiuxiao.pojo.DishFlavor;
import com.jiuxiao.service.DishFlavorService;
import com.jiuxiao.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
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

    /**
     * @param id
     * @return: com.jiuxiao.dto.DishDto
     * @decription 根据 ID 获取菜品及口味信息
     * @date 2022/8/7 9:59
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品信息
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();

        //拷贝一份 dish 的数据给 dishDto
        BeanUtils.copyProperties(dish, dishDto);

        //查询风味信息
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    /**
     * @param dishDto
     * @return: void
     * @decription 修改菜品信息及其口味信息
     * @date 2022/8/7 10:15
     */
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新 dish 表的相关信息
        this.updateById(dishDto);

        //先删除 dish_flavor 表的对应信息
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        //再存储一次 dish_flavor 表对应的信息
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().peek((item) -> item.setDishId(dishDto.getId())).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * @param ids
     * @return: void
     * @decription 删除菜品信息
     * @date 2022/8/7 10:45
     */
    @Override
    @Transactional
    public void removeWithFlavor(String ids) {
        //不管是单个删除还是批量删除，都要从列表中取 id
        List<String> idList = Arrays.asList(ids.split(","));

        //先删除 dish 表的信息
        this.removeByIds(idList);

        //再去删除对应的 dish_flavor 的信息
        for (String id : idList) {
            LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DishFlavor::getDishId, id);
            dishFlavorService.remove(queryWrapper);
        }
    }

    /**
     * @param status
     * @param ids
     * @return: void
     * @decription 修改菜品状态信息
     * @date 2022/8/7 11:50
     */
    @Override
    @Transactional
    public void updateStatus(Integer status, String ids) {
        String[] idList = ids.split(",");

        //防止用户误操作，要进行判断（比如菜品已经停售，用户要菜品再次停售）
        for (String id : idList) {
            Dish dish = this.getById(id);
            //两者不相等的情况下，说明是正常操作，否则就是非法操作(停售时停售/启用时启用)
            if (!dish.getStatus().equals(status)){
                dish.setStatus(status);
                this.updateById(dish);
            }
        }
    }
}