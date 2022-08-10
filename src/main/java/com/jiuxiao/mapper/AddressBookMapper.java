package com.jiuxiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuxiao.pojo.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地址簿数据层接口
 * @Author: 悟道九霄
 * @Date: 2022/08/10 11:29
 * @Version: 1.0.0
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
