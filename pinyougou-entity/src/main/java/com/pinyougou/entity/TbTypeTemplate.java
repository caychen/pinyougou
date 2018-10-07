package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品类型模版
 *
 */
@Data
public class TbTypeTemplate implements Serializable {
    private Long id;

    //模板名称
    private String name;

    //关联规格(json格式)
    private String specIds;

    //关联品牌(json格式)
    private String brandIds;

    //自定义属性
    private String customAttributeItems;


}