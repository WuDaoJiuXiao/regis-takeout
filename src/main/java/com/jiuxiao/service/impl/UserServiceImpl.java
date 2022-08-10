package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.mapper.UserMapper;
import com.jiuxiao.pojo.User;
import com.jiuxiao.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月10日 9:53
 * @Version: 1.0.0
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}