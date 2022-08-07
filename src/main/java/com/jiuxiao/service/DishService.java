package com.jiuxiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuxiao.dto.DishDto;
import com.jiuxiao.pojo.Dish;
import org.springframework.stereotype.Service;

/**
 * 菜品业务层接口
 * @Author: 悟道九霄
 * @Date: 2022/08/05 20:00
 * @Version: 1.0.0
 */
@Service
public interface DishService extends IService<Dish> {

    /**
     * @param dishDto
     * @return: void
     * @decription 新增菜品，同时添加风味信息
     * @date 2022/8/7 9:59
     */
    void saveWithFlavor(DishDto dishDto);

    /**
     * @param id
     * @return: com.jiuxiao.dto.DishDto
     * @decription 根据 ID 查询菜品及对应的口味信息
     * @date 2022/8/7 9:58
     */
    DishDto getByIdWithFlavor(Long id);

    /**
     * @param dishDto
     * @return: void
     * @decription 修改菜品信息及其口味信息
     * @date 2022/8/7 10:14
     */
    void updateWithFlavor(DishDto dishDto);

    /**
     * @param ids
     * @return: void
     * @decription 删除菜品信息
     * @date 2022/8/7 10:48
     */
    void removeWithFlavor(String ids);

    /**
     * @param status
     * @param ids
     * @return: void
     * @decription 修改菜品状态信息
     * @date 2022/8/7 11:48
     */
    void updateStatus(Integer status, String ids);
}
