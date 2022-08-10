package com.jiuxiao.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 地址簿实体类
 * @Author: 悟道九霄
 * @Date: 2022年08月10日 11:19
 * @Version: 1.0.0
 */
@Data
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 地址 ID */
    private Long id;

    /** 地址对应的用户 ID */
    private Long userId;

    /** 收货人姓名 */
    private String consignee;

    /** 收货人性别 0:女 1:男 */
    private int sex;

    /** 收货人手机号 */
    private String phone;

    /** 地址省份代号 */
    private String provinceCode;

    /** 地址省份名称 */
    private String provinceName;

    /** 地址城市代号 */
    private String cityCode;

    /** 地址城市名称 */
    private String cityName;

    /** 地址区县代码 */
    private String districtCode;

    /** 地址区县名称 */
    private String districtName;

    /** 地址详细信息 */
    private String detail;

    /** 地址标签：家、公司、学校、无 */
    private String label;

    /** 是否为默认地址 */
    private int isDefault;

    /** 地址创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 地址修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /** 地址创建人 */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /** 地址修改人 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /** 地址是否被删除 */
    private Integer isDeleted;
}