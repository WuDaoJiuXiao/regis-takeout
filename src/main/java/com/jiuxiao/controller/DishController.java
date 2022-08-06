package com.jiuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.dto.DishDto;
import com.jiuxiao.pojo.Category;
import com.jiuxiao.pojo.Dish;
import com.jiuxiao.service.CategoryService;
import com.jiuxiao.service.DishFlavorService;
import com.jiuxiao.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 20:06
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishService dishService;

    @Resource
    private DishFlavorService dishFlavorService;

    @Resource
    private CategoryService categoryService;

    /**
     * @param dishDto
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 新增菜品
     * @date 2022/8/6 15:46
     */
    @PostMapping
    public RespBean<String> save(@RequestBody DishDto dishDto) {
        dishService.saveWithFlavor(dishDto);
        return RespBean.success(SysConstant.ADD_DISH_SUCCESS);
    }

    /**
     * @param page
     * @param pageSize
     * @param name
     * @return: com.jiuxiao.commons.RespBean<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     * @decription 菜品信息分页查询
     * @date 2022/8/6 16:06
     */
    @GetMapping("/page")
    public RespBean<Page> page(int page, int pageSize, String name) {
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //分页查询
        dishService.page(pageInfo, queryWrapper);

        //对象拷贝，将 pageInfo 属性全部拷贝到 dishDtoPage 中，忽略 records 属性
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");

        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            //根据 id 查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category != null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);

        return RespBean.success(dishDtoPage);
    }
}