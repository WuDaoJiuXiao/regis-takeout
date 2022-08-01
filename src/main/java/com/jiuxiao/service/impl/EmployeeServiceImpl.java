package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.mapper.EmployeeMapper;
import com.jiuxiao.pojo.Employee;
import com.jiuxiao.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * 员工业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月01日 9:44
 * @Version: 1.0.0
 */
@Service("EmployeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}