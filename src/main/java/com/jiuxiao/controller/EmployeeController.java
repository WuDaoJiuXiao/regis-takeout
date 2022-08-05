package com.jiuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.pojo.Employee;
import com.jiuxiao.service.EmployeeService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 员工控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月01日 9:46
 * @Version: 1.0.0
 */
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

    /**
     * @param employee
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 新增员工
     * @date 2022/8/2 10:03
     */
    @PostMapping
    public RespBean<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        //初始密码为 123456
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.save(employee);
        return RespBean.success(SysConstant.ADD_USER_SUCCESS);
    }

    /**
     * @param page
     * @param pageSize
     * @param name
     * @return: com.jiuxiao.commons.RespBean<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     * @decription 员工信息分页查询
     * @date 2022/8/2 15:29
     */
    @GetMapping("/page")
    public RespBean<Page> page(int page, int pageSize, String name) {
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //排序条件
        queryWrapper.orderByAsc(Employee::getUpdateTime);
        employeeService.page(pageInfo, queryWrapper);

        return RespBean.success(pageInfo);
    }

    /**
     * @param request
     * @param employee
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 更新员工信息
     * @date 2022/8/3 10:11
     */
    @PutMapping
    public RespBean<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        employeeService.updateById(employee);
        return RespBean.success(SysConstant.USER_INFO_UPDATE_SUCCESS);
    }

    /**
     * @param id
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.pojo.Employee>
     * @decription 根据员工 id 获取员工信息
     * @date 2022/8/3 15:02
     */
    @GetMapping("/{id}")
    public RespBean<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        if (null != employee){
            return RespBean.success(employee);
        }
        return RespBean.error(SysConstant.NOT_QUERY_USER_INFO);
    }
}