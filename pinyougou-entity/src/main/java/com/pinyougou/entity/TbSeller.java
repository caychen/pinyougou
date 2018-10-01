package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbSeller implements Serializable {
    private String sellerId;

    private String name;

    private String nickName;

    private String password;

    private String email;

    private String mobile;

    private String telephone;

    private String status;

    private String addressDetail;

    private String linkmanName;

    private String linkmanQq;

    private String linkmanMobile;

    private String linkmanEmail;

    private String licenseNumber;

    private String taxNumber;

    private String orgNumber;

    private Long address;

    private String logoPic;

    private String brief;

    private Date createTime;

    private String legalPerson;

    private String legalPersonCardId;

    private String bankUser;

    private String bankName;

}