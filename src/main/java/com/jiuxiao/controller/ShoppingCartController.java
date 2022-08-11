package com.jiuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.pojo.ShoppingCart;
import com.jiuxiao.service.ShoppingCartService;
import com.jiuxiao.tools.BaseContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 购物车控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月11日 10:15
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * @param shoppingCart
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.pojo.ShoppingCart>
     * @decription 添加购物车
     * @date 2022/8/11 10:34
     */
    @PostMapping("/add")
    public RespBean<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        //设置当前用户的 ID
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId);

        //判断用户点的是菜品还是套餐
        if (dishId != null) { //用户点的是菜品
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else { //用户点的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        //根据 用户ID + 菜品ID/套餐ID 查询购物车信息
        ShoppingCart cartOne = shoppingCartService.getOne(queryWrapper);

        //信息为空，说明该用户第一次点该菜品/套餐，数量设置为 1
        if (cartOne == null) {
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartOne = shoppingCart;
        } else { //不为空，说明该用户不是第一次点该菜品/套餐，数量加一
            cartOne.setNumber(cartOne.getNumber() + 1);
            cartOne.setCreateTime(LocalDateTime.now());
            shoppingCartService.updateById(cartOne);
        }

        return RespBean.success(cartOne);
    }

    /**
     * @return: com.jiuxiao.commons.RespBean<java.util.List < com.jiuxiao.pojo.ShoppingCart>>
     * @decription 查询购物车信息
     * @date 2022/8/11 11:07
     */
    @GetMapping("/list")
    public RespBean<List<ShoppingCart>> list() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);

        List<ShoppingCart> cartList = shoppingCartService.list(queryWrapper);

        return RespBean.success(cartList);
    }

    /**
     * @param shoppingCart
     * @return: javax.xml.ws.Response<com.jiuxiao.pojo.ShoppingCart>
     * @decription 删减购物车
     * @date 2022/8/11 11:09
     */
    @PostMapping("/sub")
    public RespBean<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){
        //获取当前登录用户的 ID
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId);

        Long dishId = shoppingCart.getDishId();
        Long setmealId = shoppingCart.getSetmealId();

        if (dishId != null) {
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else {
            queryWrapper.eq(ShoppingCart::getSetmealId, setmealId);
        }

        //获取到唯一的一条信息
        ShoppingCart cartOne = shoppingCartService.getOne(queryWrapper);

        Integer currentNumber = cartOne.getNumber();
        //当前套餐/菜品数量大于一，数量减一
        if (currentNumber > 1) {
            cartOne.setNumber(currentNumber - 1);
            shoppingCartService.updateById(cartOne);
        } else { //当前套餐/菜品数量等于一，直接删除该记录
            cartOne.setNumber(0);   //这里需要设置为 0 后返回到前端，不然页面无法刷新
            shoppingCartService.removeById(cartOne);
        }

        return RespBean.success(cartOne);
    }

    /**
     * @return: javax.xml.ws.Response<java.lang.String>
     * @decription 清空购物车
     * @date 2022/8/11 11:13
     */
    @DeleteMapping("/clean")
    public RespBean<String> clean() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        shoppingCartService.remove(queryWrapper);
        return RespBean.success(SysConstant.DELETE_SHOPPING_CART_SUCCESS);
    }
}