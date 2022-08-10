package com.jiuxiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxiao.mapper.AddressBookMapper;
import com.jiuxiao.pojo.AddressBook;
import com.jiuxiao.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * 地址簿业务层接口实现类
 * @Author: 悟道九霄
 * @Date: 2022年08月10日 11:31
 * @Version: 1.0.0
 */
@Service("AddressBookService")
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}