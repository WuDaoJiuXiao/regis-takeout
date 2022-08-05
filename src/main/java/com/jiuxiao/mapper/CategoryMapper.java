package com.jiuxiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuxiao.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类数据层
 * @Author: 悟道九霄
 * @Date: 2022/08/05 11:43
 * @Version: 1.0.0
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
