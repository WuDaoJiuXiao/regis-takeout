package com.jiuxiao.filter;

import com.alibaba.fastjson.JSON;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.tools.BaseContext;
import com.jiuxiao.tools.RequestTools;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录过滤器
 * @Author: 悟道九霄
 * @Date: 2022年08月01日 14:40
 * @Version: 1.0.0
 */
@Slf4j
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        log.info("拦截到请求 -> " + req.getRequestURI());

        //获取请求的 URI
        String requestURI = req.getRequestURI();
        //这些请求都不需要拦截
        String[] notFilterUri = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login"
        };

        //判断本次请求是否需要被拦截
        boolean check = RequestTools.checkUriFilter(notFilterUri, requestURI);

        //本次去请求不需要处理，直接放行
        if (check){
            chain.doFilter(req, resp);
            return;
        }

        //后台用户已经登录，直接放行
        Long empId = (Long) req.getSession().getAttribute("employee");
        if (empId != null){
            BaseContext.setCurrentId(empId);
            chain.doFilter(req, resp);
            return;
        }

        //移动端用户已经登录，直接放行
        Long userId = (Long) req.getSession().getAttribute("user");
        if (userId != null){
            BaseContext.setCurrentId(userId);
            chain.doFilter(req, resp);
            return;
        }

        //未登录，返回未登录，通过输出流的方式向客户端页面响应数据
        resp.getWriter().write(JSON.toJSONString(RespBean.error("NOTLOGIN")));
    }
}