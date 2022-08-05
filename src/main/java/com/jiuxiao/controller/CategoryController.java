package com.jiuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.pojo.Category;
import com.jiuxiao.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 分类管理控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 14:40
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * @param category
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 新增分类信息
     * @date 2022/8/5 15:02
     */
    @PostMapping
    public RespBean<String> save(@RequestBody Category category) {
        categoryService.save(category);
        return RespBean.success(SysConstant.CATEGORY_SAVE_SUCCESS);
    }

    /**
     * @param page
     * @param pageSize
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.pojo.Category>
     * @decription 分类信息分页查询
     * @date 2022/8/5 15:13
     */
    @GetMapping("/page")
    public RespBean<Page> page(int page, int pageSize) {
        Page<Category> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //根据 sort 值进行升序排列
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo, queryWrapper);
        return RespBean.success(pageInfo);
    }

    /**
     * @param id
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 根据 ID 删除分类
     * @date 2022/8/5 20:09
     */
    @DeleteMapping
    public RespBean<String> delete(Long id) {
        categoryService.remove(id);
        return RespBean.success(SysConstant.CATEGORY_DELETE_SUCCESS);
    }

    /**
     * @param category
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 根据 ID 修改分类信息
     * @date 2022/8/5 21:06
     */
    @PutMapping
    public RespBean<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return RespBean.success(SysConstant.CATEGORY_UPDATE_SUCCESS);
    }
}