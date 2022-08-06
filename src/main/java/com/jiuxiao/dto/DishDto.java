package com.jiuxiao.dto;

import com.jiuxiao.pojo.Dish;
import com.jiuxiao.pojo.DishFlavor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜品传输对象
 * @Author: 悟道九霄
 * @Date: 2022年08月06日 15:42
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DishDto extends Dish {

    /** 风格口味列表 */
    private List<DishFlavor> flavors = new ArrayList<>();

    /** 分类名称 */
    private String categoryName;

    /**  */
    private Integer copies;
}