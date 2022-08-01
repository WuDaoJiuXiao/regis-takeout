package com.jiuxiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuxiao.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工接口
 * @Author: 悟道九霄
 * @Date: 2022/08/01 09:41
 * @Version: 1.0.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
