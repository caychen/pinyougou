package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbFreightTemplate implements Serializable {
    private Long id;

    private String sellerId;

    private String isDefault;

    private String name;

    private String sendTimeType;

    private Long price;

    private Date createTime;


}