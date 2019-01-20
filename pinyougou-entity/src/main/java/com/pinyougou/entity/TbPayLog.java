package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易记录
 */
@Data
public class TbPayLog implements Serializable {
    //支付订单号
    private String outTradeNo;

    //创建日期
    private Date createTime;

    //支付完成时间
    private Date payTime;

    //支付金额（分）
    private Long totalFee;

    //用户ID
    private String userId;

    //交易号码
    private String transactionId;

    //交易状态
    private String tradeState;

    //订单编号列表
    private String orderList;

    //支付类型
    private String payType;


}