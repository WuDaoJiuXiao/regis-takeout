package com.jiuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.pojo.User;
import com.jiuxiao.service.UserService;
import com.jiuxiao.tools.CodeTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月10日 10:11
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * @param user
     * @param session
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 发送手机验证码
     * @date 2022/8/10 10:36
     */
    @PostMapping("/sendMsg")
    public RespBean<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取用户手机号
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            String code = CodeTools.generateValidateCode(4).toString();
            log.info("手机号:{}, 验证码:{}", phone, code);

            //调用阿里云的短信发送 API 发送短信，这里不调用，为了方便调试，直接控制台输出验证码即可
            //SMSTools.sendMsg("荒天帝", "", phone, code);

            session.setAttribute(phone, code);
            return RespBean.success(SysConstant.PHONE_CODE_SEND_SUCCESS);
        }

        return RespBean.success(SysConstant.PHONE_CODE_SEND_ERROR);
    }

    /**
     * @param map
     * @param session
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 移动端用户登录
     * @date 2022/8/10 10:52
     */
    @PostMapping("/login")
    public RespBean<User> login(@RequestBody Map map, HttpSession session) {
        //获取前端传回的手机号、验证码
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        Object codeInSession = session.getAttribute(phone);

        if (codeInSession != null && codeInSession.equals(code)) {
            //登陆成功后，如果是新用户就自动注册
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);

            if (user == null) { //是新用户，自动注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            //登录成功后，将用户 ID 存入 Session
            session.setAttribute("user", user.getId());
            return RespBean.success(user);
        }
        return RespBean.error(SysConstant.MOBILE_USER_LOGIN_ERROR);
    }
}