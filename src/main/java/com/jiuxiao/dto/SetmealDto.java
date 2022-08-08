package com.jiuxiao.dto;

import com.jiuxiao.pojo.Setmeal;
import com.jiuxiao.pojo.SetmealDish;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 套餐数据传输类
 * @Author: 悟道九霄
 * @Date: 2022年08月07日 14:55
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SetmealDto extends Setmeal {

    /** 套餐菜品列表 */
    private List<SetmealDish> setmealDishes;

    /** 套餐对对应的分类名 */
    private String categoryName;
}