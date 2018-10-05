package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 类型模版
 *
 */
@Data
public class TbTypeTemplate implements Serializable {
    private Long id;

    //模板名称
    private String name;

    //关联规格ID
    private String specIds;

    //关联品牌ID
    private String brandIds;

    //自定义属性
    private String customAttributeItems;


}