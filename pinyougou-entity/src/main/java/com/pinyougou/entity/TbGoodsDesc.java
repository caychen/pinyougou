package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * SPU描述
 *
 */
@Data
public class TbGoodsDesc implements Serializable {
    //SPU_ID
    private Long goodsId;

    //描述
    private String introduction;

    //规格结果集，所有规格，包含isSelected
    private String specificationItems;

    //自定义属性（参数结果）
    private String customAttributeItems;

    //图片集合
    private String itemImages;

    //包装列表
    private String packageList;

    //售后服务
    private String saleService;

}