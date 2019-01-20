package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌
 */
@Data
public class TbBrand implements Serializable {
    //主键
    private Long id;

    //品牌名称
    private String name;

    //品牌首字母
    private String firstChar;

}