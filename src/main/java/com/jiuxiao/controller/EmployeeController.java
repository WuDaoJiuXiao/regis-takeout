package com.jiuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.pojo.Employee;
import com.jiuxiao.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 员工控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月01日 9:46
 * @Version: 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    /**
     * @param request
     * @param employee
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.pojo.Employee>
     * @decription 用户登录
     * @date 2022/8/1 10:21
     */
    @PostMapping("/login")
    public RespBean<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        //前端返回的密码进行 md5 加密
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());

        //比对用户名
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //查询的用户为空，返回登录失败
        if (null == emp) {
            return RespBean.error(SysConstant.USER_NOT_EXIST);
        }

        //比对密码是否一致
        if (!emp.getPassword().equals(password)) {
            return RespBean.error(SysConstant.PASSWORD_ERROR);
        }

        //用户是否被封禁
        if (emp.getStatus() == 0) {
            return RespBean.error(SysConstant.USER_IS_BAN);
        }

        //将用户 id 放入 Session
        request.getSession().setAttribute("employee", emp.getId());
        return RespBean.success(emp);
    }

    /**
     * @param request
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 用户注销
     * @date 2022/8/1 12:10
     */
    @PostMapping("/logout")
    public RespBean<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return RespBean.success(SysConstant.USER_LOGOUT_SUCCESS);
    }
}