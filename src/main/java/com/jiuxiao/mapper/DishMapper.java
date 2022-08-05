package com.jiuxiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuxiao.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜品数据层
 * @Author: 悟道九霄
 * @Date: 2022/08/05 19:58
 * @Version: 1.0.0
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
