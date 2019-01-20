package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单商品
 */
@Data
public class TbOrderItem implements Serializable {
    //主键
    private Long id;

    //商品id
    private Long itemId;

    //SPU_ID
    private Long goodsId;

    //订单id
    private Long orderId;

    //商品标题
    private String title;

    //商品单价
    private BigDecimal price;

    //商品购买数量
    private Integer num;

    //商品总金额
    private BigDecimal totalFee;

    //商品图片地址
    private String picPath;

    //商家ID
    private String sellerId;
}