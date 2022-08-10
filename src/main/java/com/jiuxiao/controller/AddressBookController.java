package com.jiuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jiuxiao.commons.RespBean;
import com.jiuxiao.constants.SysConstant;
import com.jiuxiao.pojo.AddressBook;
import com.jiuxiao.service.AddressBookService;
import com.jiuxiao.tools.BaseContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户地址簿控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月10日 11:19
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Resource
    private AddressBookService addressBookService;

    /**
     * @param addressBook
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.pojo.AddressBook>
     * @decription 新增地址
     * @date 2022/8/10 11:34
     */
    @PostMapping
    public RespBean<AddressBook> save(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookService.save(addressBook);
        return RespBean.success(addressBook);
    }

    /**
     * @param addressBook
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 修改地址
     * @date 2022/8/10 14:15
     */
    @PutMapping
    public RespBean<AddressBook> update(@RequestBody AddressBook addressBook) {
        addressBookService.updateById(addressBook);
        return RespBean.success(addressBook);
    }

    /**
     * @param addressBook
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.pojo.AddressBook>
     * @decription 设置为默认地址
     * @date 2022/8/10 11:37
     */
    @PutMapping("/default")
    public RespBean<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
        LambdaUpdateWrapper<AddressBook> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
        updateWrapper.set(AddressBook::getIsDefault, 0);
        addressBookService.update(updateWrapper);

        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);

        return RespBean.success(addressBook);
    }

    /**
     * @param id
     * @return: com.jiuxiao.commons.RespBean
     * @decription 根据 ID 查询地址
     * @date 2022/8/10 11:44
     */
    @GetMapping("/{id}")
    public RespBean<AddressBook> get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);

        if (addressBook == null) {
            return RespBean.error(SysConstant.GET_ADDRESS_ERROR);
        } else {
            return RespBean.success(addressBook);
        }
    }

    /**
     * @return: com.jiuxiao.commons.RespBean<com.jiuxiao.pojo.AddressBook>
     * @decription 获取默认地址
     * @date 2022/8/10 11:47
     */
    @GetMapping("/default")
    public RespBean<AddressBook> getDefault() {
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
        queryWrapper.eq(AddressBook::getIsDefault, 1);

        AddressBook addressBook = addressBookService.getOne(queryWrapper);

        if (addressBook == null) {
            return RespBean.error(SysConstant.GET_ADDRESS_ERROR);
        } else {
            return RespBean.success(addressBook);
        }
    }

    /**
     * @param addressBook
     * @return: com.jiuxiao.commons.RespBean<java.util.List < com.jiuxiao.pojo.AddressBook>>
     * @decription 查询指定用户的全部地址
     * @date 2022/8/10 11:50
     */
    @GetMapping("/list")
    public RespBean<List<AddressBook>> list(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());

        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(addressBook.getUserId() != null, AddressBook::getUserId, addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);

        List<AddressBook> addressBookList = addressBookService.list(queryWrapper);
        return RespBean.success(addressBookList);
    }
}