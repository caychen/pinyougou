package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收货地址
 *
 */
@Data
public class TbAddress implements Serializable {
    //主键
    private Long id;

    //用户ID
    private String userId;

    //省ID
    private String provinceId;

    //市ID
    private String cityId;

    //县区ID
    private String townId;

    //手机号
    private String mobile;

    //详细地址
    private String address;

    //联系人
    private String contact;

    //是否是默认 1默认 0否
    private String isDefault;

    //备注
    private String notes;

    //创建日期
    private Date createDate;

    //别名
    private String alias;

}