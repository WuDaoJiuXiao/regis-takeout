package com.jiuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.dto.SetmealDto;
import com.jiuxiao.pojo.Category;
import com.jiuxiao.pojo.Setmeal;
import com.jiuxiao.service.CategoryService;
import com.jiuxiao.service.SetmealDishService;
import com.jiuxiao.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月07日 15:29
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Resource
    private SetmealService setmealService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private SetmealDishService setmealDishService;

    /**
     * @param setmealDto
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 新增套餐
     * @date 2022/8/7 17:07
     */
    @PostMapping
    public RespBean<String> save(@RequestBody SetmealDto setmealDto) {
        setmealService.saveWithDish(setmealDto);
        return RespBean.success(SysConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * @param page
     * @param pageSize
     * @param name
     * @return: com.jiuxiao.commons.RespBean<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     * @decription 套餐信息分页
     * @date 2022/8/8 10:12
     */
    @GetMapping("/page")
    public RespBean<Page> page(int page, int pageSize, String name) {
        Page<Setmeal> setmealPage = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        //根据条件查询 Setmeal 对象
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        //此处的 page 对象已经被有了数据
        setmealService.page(setmealPage, queryWrapper);

        //此时 page 对象中的 records 任然是 Setmeal 对象
        //我们需要的是 setmealDto,所以需要拷贝除过 records 的其他数据
        BeanUtils.copyProperties(setmealPage, dtoPage, "records");

        //将 records 对象中的 setmeal 依次转变为 setmealDto
        List<Setmeal> records = setmealPage.getRecords();
        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            //将 setmeal 属性全部拷贝到 setmealDto 中
            BeanUtils.copyProperties(item, setmealDto);
            //根据 setmeal 的 ID 获取对应的分类实体
            Category category = categoryService.getById(item.getCategoryId());
            if (category != null) {
                //为每个 setmealDto 实体设置 categoryName
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);
        return RespBean.success(dtoPage);
    }

    /**
     * @param ids
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 删除套餐
     * @date 2022/8/8 10:13
     */
    @DeleteMapping
    public RespBean<String> remove(@RequestParam List<Long> ids) {
        setmealService.removeWithDish(ids);
        return RespBean.success(SysConstant.SETMEAL_DELETE_SUCCESS);
    }

    /**
     * @param status
     * @param ids
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 修改套餐状态
     * @date 2022/8/8 10:34
     */
    @PostMapping("/status/{status}")
    public RespBean<String> statue(@PathVariable Integer status, @RequestParam List<Long> ids) {
        setmealService.updateStatus(status, ids);
        return RespBean.success(SysConstant.UPDATE_SETMEAL_STATUS_SUCCESS);
    }

    /**
     * @param id
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.dto.SetmealDto>
     * @decription 根据 ID 获取套餐及关联的菜品信息
     * @date 2022/8/8 10:47
     */
    @GetMapping("/{id}")
    public RespBean<SetmealDto> get(@PathVariable Long id) {
        SetmealDto setmealDto = setmealService.getByIdWithDish(id);
        return RespBean.success(setmealDto);
    }

    /**
     * @param setmealDto
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 修改套餐及其关联的菜品信息
     * @date 2022/8/8 10:55
     */
    @PutMapping
    public RespBean<String> update(@RequestBody SetmealDto setmealDto) {
        setmealService.updateWithDish(setmealDto);
        return RespBean.success(SysConstant.UPDATE_SETMEAL_SUCCESS);
    }
}