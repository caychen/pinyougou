package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbGoodsDesc implements Serializable {
    private Long goodsId;

    private String introduction;

    private String specificationItems;

    private String customAttributeItems;

    private String itemImages;

    private String packageList;

    private String saleService;


}