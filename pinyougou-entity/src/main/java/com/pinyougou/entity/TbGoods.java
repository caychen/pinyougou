package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SPU
 *
 */
@Data
public class TbGoods implements Serializable {
    //主键
    private Long id;

    //商家ID
    private String sellerId;

    //SPU名
    private String goodsName;

    //默认SKU
    private Long defaultItemId;

    //状态
    private String auditStatus;

    //是否上架
    private String isMarketable;

    //品牌ID
    private Long brandId;

    //副标题
    private String caption;

    //一级类目
    private Long category1Id;

    //二级类目
    private Long category2Id;

    //三级类目
    private Long category3Id;

    //小图
    private String smallPic;

    //商城价
    private BigDecimal price;

    //分类模板ID
    private Long typeTemplateId;

    //是否启用规格
    private String isEnableSpec;

    //是否删除
    private String isDelete;


}