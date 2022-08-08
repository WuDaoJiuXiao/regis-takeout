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
            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);

        return RespBean.success(dishDtoPage);
    }

    /**
     * @param id
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.dto.DishDto>
     * @decription 根据 ID 获取菜品及口味信息
     * @date 2022/8/7 9:56
     */
    @GetMapping("/{id}")
    public RespBean<DishDto> get(@PathVariable Long id) {
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return RespBean.success(dishDto);
    }

    /**
     * @param dishDto
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 修改菜品信息及其口味信息
     * @date 2022/8/7 10:11
     */
    @PutMapping
    public RespBean<String> update(@RequestBody DishDto dishDto) {
        dishService.updateWithFlavor(dishDto);
        return RespBean.success(SysConstant.UPDATE_DISH_SUCCESS);
    }

    /**
     * @param ids
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 根据 ID 删除菜品
     * @date 2022/8/7 10:42
     */
    @DeleteMapping
    public RespBean<String> delete(String ids) {
        dishService.removeWithFlavor(ids);
        return RespBean.success(SysConstant.DELETE_DISH_SUCCESS);
    }

    /**
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 修改菜品状态信息
     * @date 2022/8/7 11:48
     */
    @PostMapping("/status/{status}")
    public RespBean<String> status(@PathVariable Integer status, String ids) {
        dishService.updateStatus(status, ids);
        return RespBean.success(SysConstant.UPDATE_DISH_STATUS_SUCCESS);
    }

    /**
     * @param dish
     * @return: com.jiuxiao.commons.RespBean<java.util.List < com.jiuxiao.pojo.Dish>>
     * @decription 根据套餐 ID 查询当前套餐下的菜品
     * @date 2022/8/7 15:40
     */
    @GetMapping("/list")
    public RespBean<List<Dish>> list(Dish dish) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        queryWrapper.eq(Dish::getStatus, 1);
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> dishes = dishService.list(queryWrapper);

        return RespBean.success(dishes);
    }
}