package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品类目
 */
@Data
public class TbItemCat implements Serializable {
    //类目ID
    private Long id;

    //父类目ID=0时，代表的是一级的类目
    private Long parentId;

    //类目名称
    private String name;

    //类型id
    private Long typeId;

}