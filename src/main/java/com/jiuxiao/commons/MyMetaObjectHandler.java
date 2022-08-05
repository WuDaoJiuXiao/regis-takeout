package com.jiuxiao.commons;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jiuxiao.tools.BaseContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元数据对象处理器
 * @Author: 悟道九霄
 * @Date: 2022年08月03日 15:44
 * @Version: 1.0.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * @param metaObject
     * @return: void
     * @decription 新增操作时添加公共字段
     * @date 2022/8/3 15:57
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }

    /**
     * @param metaObject
     * @return: void
     * @decription 更新操作时添加公共字段
     * @date 2022/8/3 15:57
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser",BaseContext.getCurrentId());
    }
}