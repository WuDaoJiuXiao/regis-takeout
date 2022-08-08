package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.dto.SetmealDto;
import com.jiuxiao.exception.CustomException;
import com.jiuxiao.mapper.SetmealMapper;
import com.jiuxiao.pojo.Setmeal;
import com.jiuxiao.pojo.SetmealDish;
import com.jiuxiao.service.SetmealDishService;
import com.jiuxiao.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 20:03
 * @Version: 1.0.0
 */
@Service("SetmealService")
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Resource
    private SetmealDishService setmealDishService;

    /**
     * @param setmealDto
     * @return: void
     * @decription 新增套餐数据，并且新增对应的菜品数据
     * @date 2022/8/7 16:31
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //存储套餐信息
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().peek((item) -> item.setSetmealId(setmealDto.getId())).collect(Collectors.toList());

        //存储套餐信息对应的菜品信息
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * @param ids
     * @return: void
     * @decription 删除套餐及其关联的菜品信息
     * @date 2022/8/8 10:15
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //现根据传过来的 id 列表，查询是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        //数量大于零，说明要批量删除的套餐中，有的是起售的，不符合删除条件
        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new CustomException(SysConstant.SETMEAL_NOT_ALLOWED_DELETE);
        }

        //如果可以删除，先删除 setmeal 表的
        this.removeByIds(ids);

        //再删除 setmeal_dish 表的
        LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SetmealDish::getSetmealId, ids);

        setmealDishService.remove(wrapper);
    }

    /**
     * @param status
     * @param ids
     * @return: void
     * @decription 修改套餐状态
     * @date 2022/8/8 10:35
     */
    @Override
    @Transactional
    public void updateStatus(Integer status, List<Long> ids) {
        for (Long id : ids) {
            Setmeal setmeal = this.getById(id);
            //两者不相等，才可以修改，防止用户误操作
            if (!setmeal.getStatus().equals(status)) {
                setmeal.setStatus(status);
                this.updateById(setmeal);
            }
        }
    }

    /**
     * @param id
     * @return: com.jiuxiao.dto.SetmealDto
     * @decription 根据 ID 获取套餐及关联的菜品信息
     * @date 2022/8/8 10:47
     */
    @Override
    public SetmealDto getByIdWithDish(Long id) {
        //查询套餐信息
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();

        //拷贝一份 setmeal 数据给 setmealDto
        BeanUtils.copyProperties(setmeal, setmealDto);

        //查询与该套餐相关的菜品信息
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, setmeal.getId());
        List<SetmealDish> dishes = setmealDishService.list(queryWrapper);
        setmealDto.setSetmealDishes(dishes);

        return setmealDto;
    }

    /**
     * @param setmealDto
     * @return: void
     * @decription 修改套餐及其关联的菜品信息
     * @date 2022/8/8 10:56
     */
    @Override
    @Transactional
    public void updateWithDish(SetmealDto setmealDto) {
        //更新 setmeal 表
        this.updateById(setmealDto);

        //先删除 setmeal_dish 表对应的信息
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, setmealDto.getId());
        setmealDishService.remove(queryWrapper);

        //再存储一次 setmeal_dish 表对应的信息
        List<SetmealDish> dishes = setmealDto.getSetmealDishes();
        dishes = dishes.stream().peek((item) -> item.setSetmealId(setmealDto.getId())).collect(Collectors.toList());
        setmealDishService.saveBatch(dishes);
    }
}