package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.exception.CustomException;
import com.jiuxiao.mapper.CategoryMapper;
import com.jiuxiao.pojo.Category;
import com.jiuxiao.pojo.Dish;
import com.jiuxiao.pojo.Setmeal;
import com.jiuxiao.service.CategoryService;
import com.jiuxiao.service.DishService;
import com.jiuxiao.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 分类业务层实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 11:44
 * @Version: 1.0.0
 */
@Service("CategoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private DishService dishService;

    @Resource
    private SetmealService setmealService;


    /**
     * @param id
     * @return: void
     * @decription 根据 ID 删除分类，删除之前判断该分类是否与菜品和套餐有关联
     * @date 2022/8/5 20:12
     */
    @Override
    public void remove(Long id) {
        //若当前分类已经关联菜品，抛出业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        long dishCount = dishService.count(dishLambdaQueryWrapper);
        if (dishCount > 0) {
            throw new CustomException(SysConstant.CATEGORY_DELETE_ERROR_WITH_DISH);
        }

        //若当前分类已经关联套餐，抛出业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        long setMealCount = setmealService.count(setmealLambdaQueryWrapper);
        if (setMealCount > 0) {
            throw new CustomException(SysConstant.CATEGORY_DELETE_ERROR_WITH_SETMEAL);
        }

        //其他情况下，正常删除
        super.removeById(id);
    }
}