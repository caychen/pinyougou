package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 */
@Data
public class TbItem implements Serializable {
    //商品id，同时也是商品编号
    private Long id;

    //商品标题
    private String title;

    //商品卖点
    private String sellPoint;

    //商品价格，单位为：元
    private BigDecimal price;

    //
    private Integer stockCount;

    //库存数量
    private Integer num;

    //商品条形码
    private String barcode;

    //商品图片
    private String image;

    //所属类目，叶子类目
    private Long categoryid;

    //商品状态，1-正常，2-下架，3-删除
    private String status;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //
    private String itemSn;

    //成本价
    private BigDecimal costPirce;

    //市场价
    private BigDecimal marketPrice;

    //是否是默认
    private String isDefault;

    //商品ID
    private Long goodsId;

    //商家ID
    private String sellerId;

    //购物车缩略图
    private String cartThumbnail;

    //分类名
    private String category;

    //品牌名
    private String brand;

    //规格名
    private String spec;

    //商家名
    private String seller;

}