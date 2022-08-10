package com.jiuxiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuxiao.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据层接口
 * @Author: 悟道九霄
 * @Date: 2022/08/10 09:51
 * @Version: 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
