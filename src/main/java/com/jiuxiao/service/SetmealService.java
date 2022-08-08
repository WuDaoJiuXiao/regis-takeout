package com.jiuxiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuxiao.dto.SetmealDto;
import com.jiuxiao.pojo.Setmeal;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 套餐业务层接口
 * @Author: 悟道九霄
 * @Date: 2022/08/05 20:01
 * @Version: 1.0.0
 */
@Service
public interface SetmealService extends IService<Setmeal> {

    /**
     * @param setmealDto
     * @return: void
     * @decription 新增套餐数据，并且新增对应的菜品数据
     * @date 2022/8/7 16:31
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * @param ids
     * @return: void
     * @decription 删除套餐及其关联的菜品信息
     * @date 2022/8/8 10:15
     */
    void removeWithDish(List<Long> ids);

    /**
     * @param status
     * @param ids
     * @return: void
     * @decription 修改套餐状态
     * @date 2022/8/8 10:35
     */
    void updateStatus(Integer status, List<Long> ids);

    /**
     * @param id
     * @return: com.jiuxiao.dto.SetmealDto
     * @decription 根据 ID 获取套餐及关联的菜品信息
     * @date 2022/8/8 10:47
     */
    SetmealDto getByIdWithDish(Long id);

    /**
     * @param setmealDto
     * @return: void
     * @decription 修改套餐及其关联的菜品信息
     * @date 2022/8/8 10:56
     */
    void updateWithDish(SetmealDto setmealDto);
}
