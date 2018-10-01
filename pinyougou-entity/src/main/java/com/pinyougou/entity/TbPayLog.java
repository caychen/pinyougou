package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbPayLog implements Serializable {
    private String outTradeNo;

    private Date createTime;

    private Date payTime;

    private Long totalFee;

    private String userId;

    private String transactionId;

    private String tradeState;

    private String orderList;

    private String payType;


}