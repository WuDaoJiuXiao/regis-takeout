package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.mapper.CategoryMapper;
import com.jiuxiao.pojo.Category;
import com.jiuxiao.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * 分类业务层实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 11:44
 * @Version: 1.0.0
 */
@Service("CategoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}