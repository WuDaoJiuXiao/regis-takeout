package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.mapper.SetmealMapper;
import com.jiuxiao.pojo.Setmeal;
import com.jiuxiao.service.SetmealService;
import org.springframework.stereotype.Service;

/**
 * 套餐业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月05日 20:03
 * @Version: 1.0.0
 */
@Service("SetmealService")
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

}